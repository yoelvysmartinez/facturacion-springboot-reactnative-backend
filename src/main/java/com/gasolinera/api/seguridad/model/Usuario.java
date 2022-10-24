package com.gasolinera.api.seguridad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gasolinera.api.ptoemision.model.PtoEmision;

@Entity
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
	@Column(unique = true)
	private String nombreUsuario;
	
	@JsonIgnore
	private String password;
	
	@ManyToOne
    @JoinColumn(name = "id_rol")
	private Rol rol;

	@ManyToOne
    @JoinColumn(name = "id_ptoEmision")
    private PtoEmision ptoEmision;
	
	public Usuario() {
	}

	public Usuario(String nombre, String apellido, String nombreUsuario, String password, Rol rol) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.rol = rol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public PtoEmision getPtoEmision() {
		return ptoEmision;
	}

	public void setPtoEmision(PtoEmision ptoEmision) {
		this.ptoEmision = ptoEmision;
	}
	
	
	
}
