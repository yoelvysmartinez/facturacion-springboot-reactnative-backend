package com.gasolinera.api.producto.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.gasolinera.api.producto.model.Productos;

@Repository
public class ProductoRepository {

	private static Integer CANT_ITEM = 20;

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Productos> buscarTodos(String busqueda) {
		String sql = "SELECT p from Productos p ";

		if (busqueda != null) {
			sql += " WHERE p.codigoProducto like :busqueda or p.nombreProducto like :busqueda ";
		}
		sql += " Order By codigoProducto desc";

		TypedQuery<Productos> query = entityManager.createQuery(sql, Productos.class).setMaxResults(CANT_ITEM);

		if (busqueda != null) {
			query.setParameter("busqueda", "%" + busqueda + "%");
		}

		return query.getResultList();
	};
}
