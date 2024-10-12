package com.paroquia_santo_afonso.sep.SEP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByEmail(String email);
}
