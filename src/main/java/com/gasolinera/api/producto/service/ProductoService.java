package com.gasolinera.api.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasolinera.api.producto.model.Productos;
import com.gasolinera.api.producto.repository.ProductoRepository;
import com.gasolinera.api.util.dto.Filtro;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Productos> productos(Filtro filtro){
		String busqueda = null;
		if(filtro != null) {
			busqueda = filtro.getBusqueda();
		}
		
		return productoRepository.buscarTodos(busqueda);
	}
}
