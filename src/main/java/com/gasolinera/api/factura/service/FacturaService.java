package com.gasolinera.api.factura.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasolinera.api.factura.dto.respuesta.FacturaRespuesta;
import com.gasolinera.api.factura.model.Factura;
import com.gasolinera.api.factura.repository.FacturaRepository;

@Service
public class FacturaService {

	@Autowired
	FacturaRepository facturaRepository;

	public FacturaRespuesta guardar(Factura factura) {
		return  new FacturaRespuesta(facturaRepository.guardar(factura), true);
	}

	public FacturaRespuesta buscarPorId(Integer id) {
		Optional<Factura> factura = facturaRepository.buscarPorId(id);
		if (factura.isPresent()) {
			return new FacturaRespuesta(factura.get(), true);
		}
		return null;
	}
	
	public List<FacturaRespuesta> facturas(String busqueda, String nombreUsuario){
		return  facturaRepository
				.buscarTodas(busqueda, nombreUsuario)
				.stream()
				.map(f-> new FacturaRespuesta(f))
				.collect(Collectors.toList());
	}
	
}
