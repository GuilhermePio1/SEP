package com.paroquia_santo_afonso.sep.SEP.modules.equipe.service;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Pasta;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.repository.PastaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class PastaService {
    private final PastaRepository pastaRepository;

    public PastaService(PastaRepository pastaRepository) {
        this.pastaRepository = pastaRepository;
    }

    public Optional<Pasta> buscarPorId(Long id) {
        return pastaRepository.findById(id);
    }

    @Transactional
    public Pasta salvar(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) return null;

        Pasta pasta = new Pasta(arquivo.getOriginalFilename(), arquivo.getContentType(), arquivo.getBytes());
        return pastaRepository.save(pasta);
    }

    @Transactional
    public Pasta atualizar(Long id, MultipartFile arquivo) throws IOException {
        Pasta pasta = pastaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pasta inexistente"));

        pasta.setNomeArquivo(arquivo.getOriginalFilename());
        pasta.setContentType(arquivo.getContentType());
        pasta.setArquivo(arquivo.getBytes());

        return pastaRepository.save(pasta);
    }

    @Transactional
    public void excluir(Long pastaId) {
        if (pastaRepository.findById(pastaId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pasta inexistente");
        }

        this.pastaRepository.deleteById(pastaId);
    }
}
