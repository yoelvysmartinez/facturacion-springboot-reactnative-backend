package com.gasolinera.api.seguridad.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class CambiarPassword {

	@NotNull
	@NotEmpty
	@NotBlank
	private String nuevoPassword;

	public String getNuevoPassword() {
		return nuevoPassword;
	}

	public void setNuevoPassword(String nuevoPassword) {
		this.nuevoPassword = nuevoPassword;
	}
	
}
