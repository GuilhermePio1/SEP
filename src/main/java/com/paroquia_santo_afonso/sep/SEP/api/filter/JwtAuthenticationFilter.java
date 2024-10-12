package com.paroquia_santo_afonso.sep.SEP.api.filter;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.paroquia_santo_afonso.sep.SEP.privateKey.PrivateKeyProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final UserDetailsService userDetailsService;
	
	private final PrivateKeyProvider privateKeyProvider;
	
	public JwtAuthenticationFilter(UserDetailsService userDetailsService, PrivateKeyProvider privateKeyProvider) {
		this.userDetailsService = userDetailsService;
		this.privateKeyProvider = privateKeyProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);
		userEmail = extractUsername(jwt);
		
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			
			if (validateToken(jwt, userDetails, privateKeyProvider.getPrivateKey())) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

	private String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(privateKeyProvider.getPrivateKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Boolean validateToken(String token, UserDetails userDetails, PrivateKey privateKey) {
		final String username = extractUsername(token);
		try {
			Jwts.parserBuilder()
			.setSigningKey(privateKey)
			.build()
			.parseClaimsJws(token);
	
			return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		}catch (Exception e) {
			return false;
		}
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
