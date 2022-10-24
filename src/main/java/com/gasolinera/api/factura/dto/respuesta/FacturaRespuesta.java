package com.gasolinera.api.factura.dto.respuesta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gasolinera.api.factura.model.Detalle;
import com.gasolinera.api.factura.model.Factura;
import com.gasolinera.api.factura.model.FormaPago;

public class FacturaRespuesta {

	private Integer id;

	private String ambiente;

	private String claveAcceso;

	private Date fechaEmision;

	private String nombreCompletoCliente;

	private String identificacion;

	private String direccion;
	
	private String placa;

	private String email;

	private String establecimiento;

	private String ptoEmision;

	private String secuencial;

	private Date fechaCreacion;

	private Double totalSinImpuestos;

	private Double subtotal12;

	private Double subtotal0;

	private Double totalIva;

	private Double totalDescuento;

	private Double total;

	private String creadaPor;

	private List<DetalleFacturaRespuesta> detalles;

	private List<FormaPagoFacturaRespuesta> formasPago;

	public FacturaRespuesta(Factura f) {
		this.detalles = new ArrayList<>();
		this.formasPago = new ArrayList<>();
		this.id = f.getId();
		this.ambiente = f.getAmbiente();
		this.claveAcceso = f.getClaveAcceso();
		this.fechaEmision = f.getFechaEmision();
		this.nombreCompletoCliente = f.getNombreCompletoCliente();
		this.identificacion = f.getIdentificacion();
		this.direccion = f.getDireccion();
		this.placa = f.getPlaca();
		this.email = f.getEmail();
		this.establecimiento = f.getEstablecimiento();
		this.ptoEmision = f.getPtoEmision();
		this.secuencial = f.getSecuencial();
		this.fechaCreacion = f.getFechaCreacion();
		this.totalSinImpuestos = f.getTotalSinImpuestos();
		this.subtotal12 = f.getSubtotal12();
		this.subtotal0 = f.getSubtotal0();
		this.totalIva = f.getTotalIva();
		this.totalDescuento = f.getTotalDescuento();
		this.total = f.getTotal();
		this.creadaPor = f.getCreadaPor();
	}
	
	public FacturaRespuesta(Factura f, Boolean full) {
		this(f);
		setDetalles(f.getDetalles());
		setFormasPago(f.getFormasPago());
	}

	public List<DetalleFacturaRespuesta> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		if(detalles != null) {
			detalles.stream().forEach(d -> this.detalles.add(new DetalleFacturaRespuesta(d)));
		}
	}

	public List<FormaPagoFacturaRespuesta> getFormasPago() {
		return formasPago;
	}

	public void setFormasPago(List<FormaPago> formasPago) {
		if(formasPago != null) {
			formasPago.stream().forEach(f -> this.formasPago.add(new FormaPagoFacturaRespuesta(f)));
		}
	}

	public Integer getId() {
		return id;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getEmail() {
		return email;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public String getPtoEmision() {
		return ptoEmision;
	}

	public String getSecuencial() {
		return secuencial;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public Double getTotalSinImpuestos() {
		return totalSinImpuestos;
	}

	public Double getSubtotal12() {
		return subtotal12;
	}

	public Double getSubtotal0() {
		return subtotal0;
	}

	public Double getTotalIva() {
		return totalIva;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public Double getTotal() {
		return total;
	}

	public String getCreadaPor() {
		return creadaPor;
	}

	public String getPlaca() {
		return placa;
	}

	
}
