package com.gasolinera.api.factura.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasolinera.api.factura.boundary.FacturaFacade;
import com.gasolinera.api.factura.dto.entrada.NuevaFactura;
import com.gasolinera.api.factura.dto.respuesta.FacturaRespuesta;
import com.gasolinera.api.util.dto.Filtro;
import com.gasolinera.api.util.dto.Mensaje;

@Controller
@RequestMapping("/factura")
public class FacturaController {
	
	@Autowired
	private FacturaFacade facturaFacade;

	@PostMapping()
	public ResponseEntity<?> crearFactura(@Valid @RequestBody NuevaFactura factura,  BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Datos incompletos o email incorrecto"), HttpStatus.BAD_REQUEST);
		}
		try {
			return new ResponseEntity<FacturaRespuesta>(facturaFacade.crearFactura(factura), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Mensaje>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/buscar-facturas")
	public ResponseEntity<List<FacturaRespuesta>> facturas(@RequestBody(required = false) Filtro filtro){
		return new ResponseEntity<List<FacturaRespuesta>>(facturaFacade.buscarTodas(filtro), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FacturaRespuesta> buscarPorId(@PathVariable Integer id){
		FacturaRespuesta factura = facturaFacade.buscarPorId(id);
		if(factura == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return  new ResponseEntity<FacturaRespuesta>(factura, HttpStatus.OK);
	}
}
