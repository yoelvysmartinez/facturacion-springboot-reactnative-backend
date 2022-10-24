package com.gasolinera.api.producto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gasolinera.api.util.Utils;

@Entity
public class Productos {

	@Id
	@Column(name = "Cod_pro")
	private String codigoProducto;

	@Column(name = "Descripcion")
	private String nombreProducto;

	@Column(name = "IVA")
	private Boolean tieneIva;

	@Column(name = "PVP")
	private Double precioUnitario;

	@Transient
	private Double porcentajeIva;

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public Boolean getTieneIva() {
		return tieneIva;
	}

	public Double getPrecioUnitario() {
		if (tieneIva)
			return Utils.redondearDecimales( precioUnitario / 1.12, 4);
		
		return precioUnitario;
	}

	public Double getPorcentajeIva() {
		return tieneIva ? 12.00 : 0.00;
	}

}
