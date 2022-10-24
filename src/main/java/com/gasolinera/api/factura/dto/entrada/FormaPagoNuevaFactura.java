package com.gasolinera.api.factura.dto.entrada;

import javax.validation.constraints.NotBlank;

public class FormaPagoNuevaFactura {

	@NotBlank
	private String codigo;

	@NotBlank
	private String descripcion;

	@NotBlank
	private Double valor;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
