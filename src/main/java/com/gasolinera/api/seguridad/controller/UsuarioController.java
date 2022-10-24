package com.gasolinera.api.seguridad.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasolinera.api.seguridad.boundary.UsuarioFacade;
import com.gasolinera.api.seguridad.dto.CambiarPassword;
import com.gasolinera.api.seguridad.dto.NuevoUsuario;
import com.gasolinera.api.seguridad.dto.UsuarioLogueado;
import com.gasolinera.api.seguridad.model.Usuario;
import com.gasolinera.api.util.Utils;
import com.gasolinera.api.util.dto.Filtro;
import com.gasolinera.api.util.dto.Mensaje;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioFacade usuarioFacade;

	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@PostMapping()
	public ResponseEntity<Mensaje> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Datos incompletos"), HttpStatus.BAD_REQUEST);
		}
		try {
			usuarioFacade.nuevo(nuevoUsuario);
			return new ResponseEntity<Mensaje>(new Mensaje("Usuario Guardado"), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Mensaje>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@PostMapping("/{id}/cambiar-password")
	public ResponseEntity<Mensaje> cambiarPassword(@PathVariable Integer id, @Valid @RequestBody CambiarPassword cambiarPassword, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("La nueva contraseña es obligatoria"), HttpStatus.BAD_REQUEST);
		}
		try {
			usuarioFacade.cambiarPassword(id, cambiarPassword.getNuevoPassword());
			return new ResponseEntity<Mensaje>(new Mensaje("Contraseña Actualizada"), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Mensaje>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@PostMapping("/buscar-usuarios")
	ResponseEntity<List<Usuario>> usuarios(@RequestBody(required = false) Filtro filtro){
		return new ResponseEntity<List<Usuario>>(usuarioFacade.buscarTodos(filtro), HttpStatus.OK);
	}
	

	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@PostMapping("/{id}/asignar-pto-emision/{ptoEmisionId}")
	public ResponseEntity<Mensaje> asignarPtoEmision(@PathVariable Integer id, @PathVariable Integer ptoEmisionId) {
		try {
			usuarioFacade.asignarPtoEmision(id, ptoEmisionId);
			return new ResponseEntity<Mensaje>(new Mensaje("Se ha asignado el punto de emision al usuario"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Mensaje>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/datos-usuario-logueado")
	public ResponseEntity<UsuarioLogueado> datosUsuarioLogueado(HttpServletRequest request) {
		String token = Utils.getToken(request);
		return new ResponseEntity<UsuarioLogueado>(usuarioFacade.usuarioLogueado(token), HttpStatus.OK);
	}

}
