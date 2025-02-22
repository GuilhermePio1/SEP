package com.paroquia_santo_afonso.sep.SEP.modules.equipista.service;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.projection.EquipistaProjection;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Equipista;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository.EquipistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EquipistaService {

    private final EquipistaRepository equipistaRepository;

    public EquipistaService(EquipistaRepository equipistaRepository) {
        this.equipistaRepository = equipistaRepository;
    }

    public Equipista salvar(Equipista equipista) {
        return equipistaRepository.save(equipista);
    }

    public Page<EquipistaProjection> listarEquipistasProjections(Pageable pageable) {
        return equipistaRepository.findAllProjectedBy(pageable);
    }

    public Equipista buscarPorId(Long id) {
        return equipistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipista não encontrado"));
    }
}
