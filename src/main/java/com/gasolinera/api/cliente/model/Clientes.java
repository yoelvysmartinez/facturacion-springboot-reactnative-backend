package com.gasolinera.api.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "Clientes")
public class Clientes {

	
	@Id
	@Column(name = "Ced_iden")
	private String identificacion;
	
	@Column(name = "Nombres_Completos")
	private String nombreCompletoCliente;
	
	@Column(name = "Direccion")
	private String direccion;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "Nro_placa")
	private String placa;
	
	@Column(name = "codigoclien")
	private Integer codigo;
	
	@Transient
	private String tipoIdentificacion;

	public String getIdentificacion() {
		return identificacion;
	}

	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getEmail() {
		return email;
	}

	public String getPlaca() {
		return placa;
	}

	public Integer getCodigo() {
		return codigo;
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
	
	
}
