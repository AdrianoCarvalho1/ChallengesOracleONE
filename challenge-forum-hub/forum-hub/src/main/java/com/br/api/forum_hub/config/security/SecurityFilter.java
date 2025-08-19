package com.br.api.forum_hub.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.api.forum_hub.model.Usuario;
import com.br.api.forum_hub.repository.IUsuarioRepository;
import com.br.api.forum_hub.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	private TokenService tokenService;
	@Autowired
	private IUsuarioRepository repository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("Acessando endpoint: " + request.getRequestURI());
		
		if(isPublicEndpoint(request.getRequestURI())) {
			System.out.println("Endpoint público - liberando acesso");
			filterChain.doFilter(request, response);
			return;
		}
		
		var tokenJwt = recuperarToken(request);
		System.out.println("Token recebido: " + tokenJwt);
		
		if(tokenJwt == null) {
			System.out.println("Token não fornecido");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token não fornecido");
			return;
		}
		
		try {
			System.out.println("Validando token...");
			var subject = tokenService.getSubject(tokenJwt);
			System.out.println("Subject do token: " + subject);
			
			var usuario = repository.findByEmail(subject)
					.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
				
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		}catch(RuntimeException e) {
			System.out.println("Erro na autenticação: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
		
	}

	private boolean isPublicEndpoint(String requestURI) {
		return requestURI.equals("/forum-hub/login") ||
	           requestURI.equals("/forum-hub/cadastrar") ||
	           requestURI.equals("/topicos/listagem") ||
	           requestURI.startsWith("/topicos/listagem?");
	}

	private String recuperarToken(HttpServletRequest request) {
		
		var authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "").trim();
		}
		return null;
	}

}
