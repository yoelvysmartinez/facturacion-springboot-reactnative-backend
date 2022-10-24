package com.gasolinera.api.factura.repository;

import org.springframework.data.repository.CrudRepository;

import com.gasolinera.api.factura.model.Factura;

public interface FacturaCrudRepository extends CrudRepository<Factura, Integer> {

}
