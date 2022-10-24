package com.gasolinera.api.factura.dto.entrada;

import javax.validation.constraints.NotBlank;

public class DetalleNuevaFactura {

	@NotBlank
	private String codigoProducto;
	
	@NotBlank
	private String nombreProducto;
	
	@NotBlank
	private Double cantidad;
	
	@NotBlank
	private Double precioUnitario;
	
	@NotBlank
	private Double descuento;
	
	@NotBlank
	private Double porcentajeIva;

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getPorcentajeIva() {
		return porcentajeIva;
	}

	public void setPorcentajeIva(Double porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}
	
	
}
