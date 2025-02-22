package com.paroquia_santo_afonso.sep.SEP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.paroquia_santo_afonso.sep.SEP.modules.usuario.model.Usuario;
import com.paroquia_santo_afonso.sep.SEP.modules.usuario.repository.UsuarioRepository;

@Configuration
public class UserDetailsConfig {

	private final UsuarioRepository usuarioRepository;
	
	public UserDetailsConfig(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			Usuario usuario = usuarioRepository.findByEmail(username);
			if (usuario == null) {
				throw new UsernameNotFoundException("Usuário não encontrado");
			}
			
			return User.builder()
					.username(usuario.getEmail())
					.password(usuario.getSenha())
					.roles("USER")
					.build();
		};
	}
}
