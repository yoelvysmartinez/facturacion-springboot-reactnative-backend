package com.gasolinera.api.factura.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Factura {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String ambiente;
	
	private String claveAcceso;
	
	private Date fechaEmision;
	
	private String nombreCompletoCliente;

	private String tipoIdentificacion;
	
	private String identificacion;
	
	private String direccion;
	
	private String email;
	
	private String placa;
	
	private String establecimiento;
	
	private String ptoEmision;
	
	private String secuencial;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	private Double totalSinImpuestos;
	
	private Double subtotal12;
	
	private Double subtotal0;
	
	private Double totalIva;
	
	private Double totalDescuento;
	
	private Double total;
	
	private String creadaPor;
	
	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura") 
    private List<Detalle> detalles;
	
	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_factura") 
    private List<FormaPago> formasPago;

	
	
	public Factura() {
		this.fechaCreacion = new Date();
		this.detalles = new ArrayList<>();
		this.formasPago = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}

	public void setNombreCompletoCliente(String nombreCompletoCliente) {
		this.nombreCompletoCliente = nombreCompletoCliente;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
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

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getPtoEmision() {
		return ptoEmision;
	}

	public void setPtoEmision(String ptoEmision) {
		this.ptoEmision = ptoEmision;
	}

	

	public String getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Double getTotalSinImpuestos() {
		return totalSinImpuestos;
	}

	public void setTotalSinImpuestos(Double totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}

	public Double getSubtotal12() {
		return subtotal12;
	}

	public void setSubtotal12(Double subtotal12) {
		this.subtotal12 = subtotal12;
	}

	public Double getSubtotal0() {
		return subtotal0;
	}

	public void setSubtotal0(Double subtotal0) {
		this.subtotal0 = subtotal0;
	}

	public Double getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(Double totalIva) {
		this.totalIva = totalIva;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getCreadaPor() {
		return creadaPor;
	}

	public void setCreadaPor(String creadaPor) {
		this.creadaPor = creadaPor;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public List<FormaPago> getFormasPago() {
		return formasPago;
	}

	public void setFormasPago(List<FormaPago> formasPago) {
		this.formasPago = formasPago;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	

}
