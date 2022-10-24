package com.gasolinera.api.seguridad.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gasolinera.api.ptoemision.model.PtoEmision;
import com.gasolinera.api.ptoemision.service.PtoEmisionService;
import com.gasolinera.api.seguridad.dto.NuevoUsuario;
import com.gasolinera.api.seguridad.dto.UsuarioLogueado;
import com.gasolinera.api.seguridad.enums.NombreRol;
import com.gasolinera.api.seguridad.model.Rol;
import com.gasolinera.api.seguridad.model.Usuario;
import com.gasolinera.api.seguridad.service.RolService;
import com.gasolinera.api.seguridad.service.UsuarioService;
import com.gasolinera.api.util.Utils;
import com.gasolinera.api.util.dto.Filtro;

@Component
public class UsuarioFacade {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RolService rolService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	PtoEmisionService ptoEmisionService;

	public void nuevo(NuevoUsuario nuevoUsuario) throws Exception {
		if (usuarioService.existeUsuario(nuevoUsuario.getNombreUsuario())) {
			throw new Exception("El nombre de usuario ya existe");
		}
		
		Rol rol = rolService.findByNombreRol(NombreRol.VENDEDOR).get();
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(),
				nuevoUsuario.getNombreUsuario(), passwordEncoder.encode(nuevoUsuario.getPassword()), rol);
		
		if(nuevoUsuario.getPtoEmisionId() != null) {
			PtoEmision ptoEmision = ptoEmisionService.ptoEmisionPorId(nuevoUsuario.getPtoEmisionId());
			if(ptoEmision == null)
			{
				throw new Exception("El punto de emision no existe");
			}
			usuario.setPtoEmision(ptoEmision);
		}
		

		usuarioService.save(usuario);
	}
	
	public void cambiarPassword(Integer usuarioId, String nuevoPassword) throws Exception {
		Usuario usuario = usuarioService.usuarioPorId(usuarioId);
		
		if(usuario == null) {
			throw new Exception("No existe el usuario con id " + usuarioId);
		}
		
		usuario.setPassword( passwordEncoder.encode(nuevoPassword));
		usuarioService.save(usuario);
	}

	public void asignarPtoEmision(Integer id, Integer ptoEmisionId) throws Exception {
		
		PtoEmision pto = ptoEmisionService.ptoEmisionPorId(ptoEmisionId);
		if(pto == null) {
			throw new Exception("El PtoEmision a asignar no existe");
		}
		
		Usuario usuario = usuarioService.usuarioPorId(id);
		if(usuario == null) {
			throw new Exception("El usuario no existe");
		}
		
		usuario.setPtoEmision(pto);
		usuarioService.save(usuario);
	}
	
	public UsuarioLogueado usuarioLogueado(String token) {
		Usuario usuario = usuarioService.usuarioLogueado();
		return new UsuarioLogueado(token, usuario);
	}
	
	public List<Usuario> buscarTodos(Filtro filtro){
		String busqueda = Utils.obtenerBusqueda(filtro);
		if(busqueda != null)
			return usuarioService.buscarConFiltro(busqueda);
		return usuarioService.buscarTodos();
	}

}
