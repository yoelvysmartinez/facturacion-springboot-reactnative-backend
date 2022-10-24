package com.gasolinera.api.seguridad.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gasolinera.api.util.Utils;

public class JwtTokenFilter extends OncePerRequestFilter{

	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = Utils.getToken(request);
			if(token != null && jwtProvider.validateToken(token) ) {
				String nombreUsario = jwtProvider.getNombreUsuarioFromToken(token);
				UserDetails userDatail = userDetailsService.loadUserByUsername(nombreUsario);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDatail, null, userDatail.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception e) {
			logger.error("error metodo doFilterInternal");
		}
		
		filterChain.doFilter(request, response);
	}
	
	

}
