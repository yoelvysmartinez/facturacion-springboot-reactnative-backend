package com.gasolinera.api.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasolinera.api.cliente.model.Clientes;
import com.gasolinera.api.cliente.service.ClienteService;
import com.gasolinera.api.util.dto.Filtro;

@Controller
@RequestMapping("/cliente")
public class ClientesController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/buscar-clientes")
	public ResponseEntity<List<Clientes>> clientes(@RequestBody(required = false) Filtro filtro){
		return new ResponseEntity<List<Clientes>>(clienteService.clientes(filtro), HttpStatus.OK);
	}
}
