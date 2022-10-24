package com.gasolinera.api.seguridad.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gasolinera.api.seguridad.enums.NombreRol;
import com.gasolinera.api.seguridad.model.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {
	Optional<Rol> findByNombreRol(NombreRol nombreRol);
}
