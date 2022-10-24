package com.gasolinera.api.producto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasolinera.api.producto.model.Productos;
import com.gasolinera.api.producto.service.ProductoService;
import com.gasolinera.api.util.dto.Filtro;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@PostMapping("/buscar-productos")
	public ResponseEntity<List<Productos>> productos(@RequestBody(required = false) Filtro filtro) {
		return new ResponseEntity<List<Productos>>(productoService.productos(filtro), HttpStatus.OK);
	}
}
