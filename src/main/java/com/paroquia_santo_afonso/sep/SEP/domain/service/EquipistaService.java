package com.paroquia_santo_afonso.sep.SEP.domain.service;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Equipista;
import com.paroquia_santo_afonso.sep.SEP.domain.repository.EquipistaRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipistaService {

    private final EquipistaRepository equipistaRepository;

    public EquipistaService(EquipistaRepository equipistaRepository) {
        this.equipistaRepository = equipistaRepository;
    }

    public Equipista salvar(Equipista equipista) {
        return equipistaRepository.save(equipista);
    }
}
