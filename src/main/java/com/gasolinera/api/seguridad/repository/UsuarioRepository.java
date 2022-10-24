package com.gasolinera.api.seguridad.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gasolinera.api.seguridad.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
	
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	
    boolean existsByNombreUsuario(String nombreUsuario);
    
    @Query("Select u from Usuario u where concat(u.nombre, ' ', u.apellido) like %:busqueda% or u.nombreUsuario like %:busqueda% or concat(u.ptoEmision.codEstablecimiento, '-', u.ptoEmision.codPtoEmision) like %:busqueda%")
    List<Usuario> buscarConFiltro(String busqueda);

}
