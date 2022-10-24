package com.gasolinera.api.cliente.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.gasolinera.api.cliente.model.Clientes;

@Repository
public class ClienteRepository {

	private static Integer CANT_ITEM = 20;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Clientes> buscarTodos(String busqueda) {
		
		String sql = "SELECT c from Clientes c ";
		Integer codigo = 0;
		if (busqueda != null) {
			
			try {
				codigo = Integer.valueOf(busqueda);
			} catch (Exception e) {
				// TODO: handle exception
			}
			sql += " WHERE c.identificacion =:busquedaExacta or c.placa like :busquedaExacta ";

			if (codigo > 0) {
				sql += "or codigo =:codigo";
			}
		}

		sql += " Order By nombreCompletoCliente ";

		TypedQuery<Clientes> query = entityManager.createQuery(sql, Clientes.class).setMaxResults(CANT_ITEM);

		if (busqueda != null) {
			busqueda = busqueda.toUpperCase();
			query.setParameter("busquedaExacta", busqueda);
			
			if (codigo > 0) {
				query.setParameter("codigo", codigo);
			}

		}

		return query.getResultList();
	};
}
