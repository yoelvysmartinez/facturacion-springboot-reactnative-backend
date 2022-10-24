package com.gasolinera.api.ptoemision.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasolinera.api.ptoemision.model.PtoEmision;
import com.gasolinera.api.ptoemision.service.PtoEmisionService;
import com.gasolinera.api.util.dto.Mensaje;

@Controller
@RequestMapping("pto-emision")
public class PtoEmisionController {
	
	@Autowired
	PtoEmisionService ptoEmisionService;
	
	@GetMapping
	ResponseEntity<List<PtoEmision>> puntosEmision(){
		return new ResponseEntity<List<PtoEmision>>(ptoEmisionService.buscarTodos(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@PostMapping()
	public ResponseEntity<Mensaje> nuevo(@Valid @RequestBody PtoEmision ptoEmision, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Datos incompletos"), HttpStatus.BAD_REQUEST);
		}
		try {
			ptoEmisionService.guardar(ptoEmision);
			return new ResponseEntity<Mensaje>(new Mensaje("Pto Emision Guardado"), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Mensaje>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
