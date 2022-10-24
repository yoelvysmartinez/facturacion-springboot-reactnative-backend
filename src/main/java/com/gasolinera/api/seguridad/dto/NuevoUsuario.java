package com.gasolinera.api.seguridad.dto;

import javax.validation.constraints.NotBlank;

public class NuevoUsuario {
	
	@NotBlank
	private String nombre;

	@NotBlank
	private String apellido;

	@NotBlank
	private String nombreUsuario;

	@NotBlank
	private String password;
	
	private Integer ptoEmisionId;
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPtoEmisionId() {
		return ptoEmisionId;
	}

	public void setPtoEmisionId(Integer ptoEmisionId) {
		this.ptoEmisionId = ptoEmisionId;
	}
	
	
}