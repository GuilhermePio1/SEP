package com.paroquia_santo_afonso.sep.SEP.common.security.auth;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.paroquia_santo_afonso.sep.SEP.common.utils.PrivateKeyProvider;

import io.jsonwebtoken.Jwts;

@Service
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	
	private final UserDetailsService userDetailsService;
	
	private final PrivateKeyProvider privateKeyProvider;
	
	public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PrivateKeyProvider privateKeyProvider) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.privateKeyProvider = privateKeyProvider;
	}
	
	public String authenticate(String email, String senha) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, senha));
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		
		return generateJwtToken(userDetails);
	}
	
	private String generateJwtToken(UserDetails userDetails) {
		long currentTimeMillis = System.currentTimeMillis();
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(currentTimeMillis))
				.setExpiration(new Date(currentTimeMillis + 86400000))
				.signWith(privateKeyProvider.getPrivateKey())
				.compact();
	}
	
}
