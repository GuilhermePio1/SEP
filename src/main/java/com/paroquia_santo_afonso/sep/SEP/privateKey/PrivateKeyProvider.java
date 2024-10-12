package com.paroquia_santo_afonso.sep.SEP.privateKey;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PrivateKeyProvider {
	@Value("${jwt.keystore}")
	private Resource keystoreResource;
	
	@Value("${jwt.keystore-password}")
	private String keystorePassword;
	
	@Value("${jwt.key-alias}")
	private String keyAlias;
	
	@Value("${jwt.key-password}")
	private String keyPassword;
	
	private PrivateKey privateKey;
	
	@PostConstruct
	public void init() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			InputStream keystoreInputStream = keystoreResource.getInputStream();
			keyStore.load(keystoreInputStream, keystorePassword.toCharArray());
			
			privateKey = (PrivateKey) keyStore.getKey(keyAlias, keyPassword.toCharArray());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao carregar a chave secreta", e);
		}
		
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
}
