package com.gasolinera.api.factura.dto.respuesta;

import com.gasolinera.api.factura.model.Detalle;

public class DetalleFacturaRespuesta {

	private Integer id;

	private String codigoProducto;

	private String nombreProducto;

	private Double cantidad;

	private Double precioUnitario;

	private Double descuento;

	private Double porcentajeIva;

	private Double iva;

	private Double subtotal;

	public DetalleFacturaRespuesta(Detalle det) {
		this.id = det.getId();
		this.codigoProducto = det.getCodigoProducto();
		this.nombreProducto = det.getNombreProducto();
		this.cantidad = det.getCantidad();
		this.precioUnitario = det.getPrecioUnitario();
		this.descuento = det.getDescuento();
		this.porcentajeIva = det.getPorcentajeIva();
		this.iva = det.getIva();
		this.subtotal = det.getSubtotal();
	}

	public Integer getId() {
		return id;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public Double getDescuento() {
		return descuento;
	}

	public Double getPorcentajeIva() {
		return porcentajeIva;
	}

	public Double getIva() {
		return iva;
	}

	public Double getSubtotal() {
		return subtotal;
	}

}
