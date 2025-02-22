package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.service;

import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.repository.PastoralRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PastoralService {

    private final PastoralRepository pastoralRepository;

    public PastoralService(PastoralRepository pastoralRepository) {
        this.pastoralRepository = pastoralRepository;
    }

    public List<Pastoral> listar() {
        return pastoralRepository.findAll();
    }

    public Optional<Pastoral> buscar(Long pastoralId) {
        return pastoralRepository.findById(pastoralId);
    }

    public Boolean existsById(Long pastoralId) {
        return pastoralRepository.existsById(pastoralId);
    }

    public Pastoral salvar(Pastoral pastoral) {
        return pastoralRepository.save(pastoral);
    }

    public void excluir(Long pastoralId) {
        pastoralRepository.deleteById(pastoralId);
    }
}
