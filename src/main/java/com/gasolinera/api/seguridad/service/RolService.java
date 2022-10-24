package com.gasolinera.api.seguridad.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasolinera.api.seguridad.enums.NombreRol;
import com.gasolinera.api.seguridad.model.Rol;
import com.gasolinera.api.seguridad.repository.RolRepository;

@Service
public class RolService {

	@Autowired
	private RolRepository rolRepository;
	
	public Optional<Rol> findByNombreRol(NombreRol nombreRol){
		return rolRepository.findByNombreRol(nombreRol);
	}
}
