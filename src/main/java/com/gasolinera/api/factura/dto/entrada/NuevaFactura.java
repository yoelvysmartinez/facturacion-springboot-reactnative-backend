package com.gasolinera.api.factura.dto.entrada;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NuevaFactura {

	@NotNull
	private Date fechaEmision;
	
	@NotBlank
	private String nombreCompletoCliente;
	
	@NotBlank
	private String identificacion;
	
	@NotBlank
	private String direccion;
	
	private String placa;
	
	private String email;
	
	@NotNull
	@NotEmpty
	private List<DetalleNuevaFactura> detalles;
	
	@NotNull
	@NotEmpty
	private List<FormaPagoNuevaFactura> formasPago;

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}

	public void setNombreCompletoCliente(String nombreCompletoCliente) {
		this.nombreCompletoCliente = nombreCompletoCliente;
	}

	public String getTipoIdentificacion() {
		if(identificacion == null || identificacion.isEmpty())
			return "";
		if(identificacion.length() == 13)
			return "04";
		if(identificacion.length() == 10)
			return "05";
		if(identificacion.equals("9999999999999"))
			return "07";
		return "08";
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<DetalleNuevaFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleNuevaFactura> detalles) {
		this.detalles = detalles;
	}

	public List<FormaPagoNuevaFactura> getFormasPago() {
		return formasPago;
	}

	public void setFormasPago(List<FormaPagoNuevaFactura> formasPago) {
		this.formasPago = formasPago;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	
}
