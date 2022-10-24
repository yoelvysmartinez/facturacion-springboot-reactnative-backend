package com.gasolinera.api.seguridad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gasolinera.api.seguridad.model.Usuario;
import com.gasolinera.api.seguridad.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> obtenerPorNombreUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existeUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public Usuario usuarioLogueado() {
		UserDetails usuarioLogueado = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return obtenerPorNombreUsuario(usuarioLogueado.getUsername()).get();
	}
	
	public Usuario usuarioPorId(Integer usuarioId) {
		return usuarioRepository.findById(usuarioId).orElse(null);
	}
	
	public List<Usuario> buscarTodos(){
		return  (List<Usuario>) usuarioRepository.findAll();
	}
	
	public List<Usuario> buscarConFiltro(String busqueda){
		return  usuarioRepository.buscarConFiltro(busqueda);
	}

}
