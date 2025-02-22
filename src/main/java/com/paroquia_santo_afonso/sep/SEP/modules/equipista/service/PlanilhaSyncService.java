package com.paroquia_santo_afonso.sep.SEP.modules.equipista.service;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.repository.EncontroRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.repository.EquipeRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.*;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository.EquipistaRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository.ParticipacaoEncontroRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository.PlanilhaSyncMetadataRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.repository.PastoralRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PlanilhaSyncService {
    private static final Logger logger = LoggerFactory.getLogger(PlanilhaSyncService.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Value("${google.spreadsheet.id}")
    private String spreadsheetId;

    @Value("${google.spreadsheet.range}")
    private String range;

    private final SheetsService sheetsService;
    private final EquipistaRepository equipistaRepository;
    private final EncontroRepository encontroRepository;
    private final EquipeRepository equipeRepository;
    private final PastoralRepository pastoralRepository;
    private final ParticipacaoEncontroRepository participacaoEncontroRepository;
    private final PlanilhaSyncMetadataRepository planilhaSyncMetadataRepository;

    public PlanilhaSyncService(
            SheetsService sheetsService,
            EquipistaRepository equipistaRepository,
            EncontroRepository encontroRepository,
            EquipeRepository equipeRepository,
            PastoralRepository pastoralRepository,
            ParticipacaoEncontroRepository participacaoEncontroRepository,
            PlanilhaSyncMetadataRepository planilhaSyncMetadataRepository) {
        this.sheetsService = sheetsService;
        this.equipistaRepository = equipistaRepository;
        this.encontroRepository = encontroRepository;
        this.equipeRepository = equipeRepository;
        this.pastoralRepository = pastoralRepository;
        this.participacaoEncontroRepository = participacaoEncontroRepository;
        this.planilhaSyncMetadataRepository = planilhaSyncMetadataRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Async
    public void syncSpreadsheet() {
        logger.info("Iniciando sincronização da planilha...");

        try {
            List<List<Object>> rows = sheetsService.getRows(spreadsheetId, range);
            if (rows == null || rows.isEmpty()) {
                logger.info("Nenhum dado encontrado na planilha");
                return;
            }

            String currentChecksum = computeChecksum(rows);
            Optional<PlanilhaSyncMetadata> metaOpt = planilhaSyncMetadataRepository.findBySpreadsheetId(spreadsheetId);
            if (metaOpt.isPresent() && currentChecksum.equals(metaOpt.get().getChecksum())) {
                logger.info("Nenhum dado novo detectado na planilha.");
                return;
            }

            int rowNumber = 1;
            for (List<Object> row : rows) {
                try {
                    processRow(row);
                }catch (Exception ex) {
                    logger.error("Erro ao processar linha {}: {}", rowNumber, ex.getMessage(), ex);
                }

                rowNumber++;
            }

            PlanilhaSyncMetadata metadata = metaOpt.orElse(new PlanilhaSyncMetadata());
            metadata.setSpreadsheetId(spreadsheetId);
            metadata.setChecksum(currentChecksum);
            metadata.setUpdateAt(LocalDateTime.now());
            planilhaSyncMetadataRepository.save(metadata);

        }catch (Exception ex) {
            logger.error("Erro ao sincronizar a planilha: {}", ex.getMessage(), ex);
        }
    }

    @PostConstruct
    public void init() {
        syncSpreadsheet();
    }

    private void processRow(List<Object> row) {
        if (row.size() < 8) {
            logger.warn("Linha com colunas insuficientes. Esperado mínimo 8, mas encontrado {}.", row.size());
            return;
        }

        String nome = getString(row.get(0));
        String numeroTelefone = getString(row.get(1));
        String dataNascimentoStr = getString(row.get(2));
        String bairro = getString(row.get(3));
        String estadoCivilStr = getString(row.get(4));
        String sacramentoStr = getString(row.get(5));
        String encontroStr1 = getString(row.get(6));
        String encontroStr2 = getString(row.get(7));

        List<String> pastoralList = new ArrayList<>();
        for (int i = 8;i < row.size(); i++) {
            String valor = getString(row.get(i));
            if (valor != null && !valor.isEmpty()) {
                pastoralList.add(valor);
            }
        }

        LocalDate dataNascimento = parseDate(dataNascimentoStr);
        EstadoCivil estadoCivil = mapEstadoCivil(estadoCivilStr);
        Sacramento sacramento = mapSacramento(sacramentoStr);

        Equipista equipista = findOrCreateEquipista(nome, numeroTelefone, dataNascimento, bairro, estadoCivil, sacramento);

        processParticipacoes(equipista, encontroStr1, encontroStr2);

        processPastorals(equipista, pastoralList);

        try {
            equipistaRepository.save(equipista);
            participacaoEncontroRepository.saveAll(equipista.getParticipacoesEncontros());
        }catch (DataIntegrityViolationException ex) {
            logger.info("Equipista {} já cadastrado", nome);
        }

        logger.info("Equipista sincronizado: {}", nome);
    }

    private String getString(Object cell) {
        return cell != null ? cell.toString().trim() : null;
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        }catch (Exception ex) {
            logger.error("Erro ao converter data '{}' com o formato dd/MM/yyyy.", dateStr, ex);
            return null;
        }
    }

    private EstadoCivil mapEstadoCivil(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        for (EstadoCivil ec : EstadoCivil.values()) {
            if (ec.getTitle().equalsIgnoreCase(value)) {
                return ec;
            }
        }

        logger.warn("Estado Civil '{}' não foi mapeado.", value);
        return null;
    }

    private Sacramento mapSacramento(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        if ("catequeze".equalsIgnoreCase(value)) {
            return Sacramento.PRIMEIRA_EUCARISTIA;
        }

        for (Sacramento s : Sacramento.values()) {
            if (s.getTitle().equalsIgnoreCase(value)) {
                return s;
            }
        }

        logger.warn("Sacramento '{}' não foi mapeado.", value);
        return null;
    }

    private Equipista findOrCreateEquipista(String nome, String telefone, LocalDate dataNascimento,
                                            String bairro, EstadoCivil estadoCivil, Sacramento sacramento) {
        Optional<Equipista> opt = equipistaRepository.findByNomeAndNumeroTelefoneAndDataNascimento(nome, telefone, dataNascimento);
        if (opt.isPresent()) {
            return opt.get();
        }

        Endereco endereco = Endereco.builder().bairro(bairro).build();

        return Equipista.builder()
                .nome(nome)
                .numeroTelefone(telefone)
                .dataNascimento(dataNascimento)
                .endereco(endereco)
                .estadoCivil(estadoCivil)
                .sacramento(sacramento)
                .build();
    }

    private void processParticipacoes(Equipista equipista, String encontroStr1, String encontroStr2) {
        Set<ParticipacaoEncontro> participacoes = (equipista.getParticipacoesEncontros() != null)
                ? equipista.getParticipacoesEncontros() : new HashSet<>();

        processEquipeEncontro(encontroStr1, "encontrista", equipista, participacoes);
        processEquipeEncontro(encontroStr2, "equipista", equipista, participacoes);

        equipista.setParticipacoesEncontros(participacoes);
    }

    private void processEquipeEncontro(String encontroStr, String equipeStr, Equipista equipista, Set<ParticipacaoEncontro> participacoes) {
        if (encontroStr != null && !encontroStr.trim().isEmpty() && !encontroStr.trim().equalsIgnoreCase("não")) {
            String[] encontrosArray = encontroStr.split(",");
            for (String encontroRaw : encontrosArray) {
                final String nomeEncontro = encontroRaw.trim();
                if (nomeEncontro.isEmpty()) continue;

                Encontro encontro = encontroRepository.findByNome(nomeEncontro)
                        .orElseGet(() -> {
                            Encontro novoEncontro = Encontro.builder().nome(nomeEncontro).build();
                            return encontroRepository.save(novoEncontro);
                        });

                Equipe equipe = equipeRepository.findByEncontroAndNome(encontro, equipeStr)
                        .orElseGet(() -> {
                            Equipe novaEquipe = Equipe.builder()
                                    .nome(equipeStr)
                                    .encontro(encontro)
                                    .build();
                            return equipeRepository.save(novaEquipe);
                        });

                equipe.setEncontro(encontro);

                ParticipacaoEncontro participacao = ParticipacaoEncontro.builder()
                        .equipista(equipista)
                        .equipe(equipe)
                        .tipoParticipacao(equipeStr.equalsIgnoreCase("encontrista") ? TipoParticipacao.ENCONTRISTA : TipoParticipacao.EQUIPISTA)
                        .build();

                participacoes.add(participacao);
            }
        }

    }

    private void processPastorals(Equipista equipista, List<String> pastoralList) {
        List<Pastoral> pastoraisExistentes = (equipista.getPastorais() != null)
                ? new ArrayList<>(equipista.getPastorais()) : new ArrayList<>();

        for (String pastoralNome : pastoralList) {
            Pastoral pastoral = pastoralRepository.findByNome(pastoralNome)
                    .orElseGet(() -> {
                        Pastoral nova = Pastoral.builder().nome(pastoralNome).build();
                        return pastoralRepository.save(nova);
                    });

            if (!pastoraisExistentes.contains(pastoral)) {
                pastoraisExistentes.add(pastoral);
            }
        }

        equipista.setPastorais(pastoraisExistentes);
    }

    private String computeChecksum(List<List<Object>> rows) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        for (List<Object> row : rows) {
            for (Object cell : row) {
                if (cell != null) {
                    md.update(cell.toString().getBytes());
                }
            }
            md.update((byte) 0);
        }
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
