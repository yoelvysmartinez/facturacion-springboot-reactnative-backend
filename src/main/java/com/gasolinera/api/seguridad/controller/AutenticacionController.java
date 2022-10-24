package com.gasolinera.api.seguridad.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasolinera.api.seguridad.boundary.UsuarioFacade;
import com.gasolinera.api.seguridad.dto.LoginUsuario;
import com.gasolinera.api.seguridad.dto.UsuarioLogueado;
import com.gasolinera.api.seguridad.jwt.JwtProvider;
import com.gasolinera.api.util.dto.Mensaje;

@RestController
@RequestMapping("/autenticacion")
@CrossOrigin
public class AutenticacionController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UsuarioFacade usuarioFacade;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("El usuario y la clave son obligatorios"),
					HttpStatus.BAD_REQUEST);
		}

	       
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);

		String token = jwtProvider.generateToken(auth);

		return new ResponseEntity<UsuarioLogueado>(usuarioFacade.usuarioLogueado(token), HttpStatus.OK);
	}

}
