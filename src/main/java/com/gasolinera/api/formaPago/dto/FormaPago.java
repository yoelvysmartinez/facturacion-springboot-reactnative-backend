package com.gasolinera.api.formaPago.dto;

public class FormaPago {
 
	private String codigo;
	
	private String descripcion;

	public FormaPago(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	
}
