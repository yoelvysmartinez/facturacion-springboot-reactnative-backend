package com.gasolinera.api.cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasolinera.api.cliente.model.Clientes;
import com.gasolinera.api.cliente.repository.ClienteRepository;
import com.gasolinera.api.util.dto.Filtro;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Clientes> clientes(Filtro filtro){
		String busqueda = null;
		if(filtro != null) {
			busqueda = filtro.getBusqueda();
		}
		
		return clienteRepository.buscarTodos(busqueda);
	}
}
