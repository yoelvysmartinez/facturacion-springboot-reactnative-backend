package com.gasolinera.api.seguridad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gasolinera.api.seguridad.model.Usuario;
import com.gasolinera.api.seguridad.model.UsuarioPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
         Usuario usuario = usuarioService.obtenerPorNombreUsuario(nombreUsuario).get();
		
		return UsuarioPrincipal.build(usuario);
	}

}
