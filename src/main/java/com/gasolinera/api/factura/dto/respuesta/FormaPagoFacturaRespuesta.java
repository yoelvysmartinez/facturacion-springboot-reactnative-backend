package com.gasolinera.api.factura.dto.respuesta;

import com.gasolinera.api.factura.model.FormaPago;

public class FormaPagoFacturaRespuesta {
	private Integer id;

	private String codigo;

	private String descripcion;

	private String plazo;

	private String unidadTiempo;

	private Double valor;

	public FormaPagoFacturaRespuesta(FormaPago fp) {
		this.id = fp.getId();
		this.codigo = fp.getCodigo();
		this.descripcion = fp.getDescripcion();
		this.plazo = fp.getPlazo();
		this.unidadTiempo = fp.getUnidadTiempo();
		this.valor = fp.getValor();
	}

	public Integer getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getPlazo() {
		return plazo;
	}

	public String getUnidadTiempo() {
		return unidadTiempo;
	}

	public Double getValor() {
		return valor;
	}
}
