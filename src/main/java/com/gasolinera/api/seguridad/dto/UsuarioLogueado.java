package com.gasolinera.api.seguridad.dto;

import com.gasolinera.api.ptoemision.model.PtoEmision;
import com.gasolinera.api.seguridad.model.Usuario;

public class UsuarioLogueado {

	private String token;
	
	private String rol;
	
	private String nombre;
	
	private String apellidos;
	
	private String nombreUsuario;
	
	private Integer ptoEmisionId;
	
	private String nombrePtoEmision;
	
	private String codEstablecimiento;
	
	private String codPtoEmision;
	
	

	public UsuarioLogueado(String token, Usuario usuario) {
		
		this.token = token;
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellido();
		this.nombreUsuario = usuario.getNombreUsuario();
		this.rol = usuario.getRol() != null ? usuario.getRol().getNombreRol().name() : null;
		
		PtoEmision ptoEmision = usuario.getPtoEmision();
		if(ptoEmision != null) {
			this.ptoEmisionId = ptoEmision.getId();
			this.nombrePtoEmision = ptoEmision.getNombre();
			this.codEstablecimiento = ptoEmision.getCodEstablecimiento();
			this.codPtoEmision = ptoEmision.getCodPtoEmision();
		}
	}

	public String getToken() {
		return token;
	}

	public String getRol() {
		return rol;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getNombrePtoEmision() {
		return nombrePtoEmision;
	}

	public String getCodEstablecimiento() {
		return codEstablecimiento;
	}

	public String getCodPtoEmision() {
		return codPtoEmision;
	}

	public Integer getPtoEmisionId() {
		return ptoEmisionId;
	}
	
	
	
}
