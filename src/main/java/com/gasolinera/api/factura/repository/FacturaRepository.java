package com.gasolinera.api.factura.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gasolinera.api.factura.model.Factura;

@Repository
public class FacturaRepository {

	private static Integer CANT_ITEM = 20;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private FacturaCrudRepository facturaCrudRepository;

	public List<Factura> buscarTodas(String busqueda, String nombreUsuario) {
		String sql = "SELECT f from Factura f where f.creadaPor =:nombreUsuario";
		if (nombreUsuario == null) {
			sql = "SELECT f from Factura f";
			if (busqueda != null) {
				sql += " where ";
			}
		}
		if (busqueda != null) {
			sql += " f.nombreCompletoCliente like :busqueda or f.identificacion like :busqueda or f.placa like :busqueda or concat(f.establecimiento,'-', f.ptoEmision, '-', f.secuencial) like :busqueda ";
		}
		sql += " Order By id desc";

		TypedQuery<Factura> query = entityManager.createQuery(sql, Factura.class)
				.setMaxResults(CANT_ITEM);

		if (busqueda != null) {
			query.setParameter("busqueda", "%" + busqueda + "%");
		}

		if(nombreUsuario != null) {
			query.setParameter("nombreUsuario", nombreUsuario);
		}
		return query.getResultList();
	};

	public Optional<Factura> buscarPorId(Integer id) {
		return facturaCrudRepository.findById(id);
	}

	public Factura guardar(Factura factura) {
		return facturaCrudRepository.save(factura);
	}
}
