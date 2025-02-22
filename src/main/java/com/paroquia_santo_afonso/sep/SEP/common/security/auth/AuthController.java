package com.paroquia_santo_afonso.sep.SEP.common.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paroquia_santo_afonso.sep.SEP.modules.usuario.dto.LoginRequest;
import com.paroquia_santo_afonso.sep.SEP.modules.usuario.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		String token = authService.authenticate(loginRequest.email(), loginRequest.senha());
		return ResponseEntity.ok(new LoginResponse(token));
	}
}
