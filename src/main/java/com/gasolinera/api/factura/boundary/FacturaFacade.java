package com.gasolinera.api.factura.boundary;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gasolinera.api.factura.dto.entrada.DetalleNuevaFactura;
import com.gasolinera.api.factura.dto.entrada.FormaPagoNuevaFactura;
import com.gasolinera.api.factura.dto.entrada.NuevaFactura;
import com.gasolinera.api.factura.dto.respuesta.FacturaRespuesta;
import com.gasolinera.api.factura.model.Detalle;
import com.gasolinera.api.factura.model.Factura;
import com.gasolinera.api.factura.model.FormaPago;
import com.gasolinera.api.factura.service.FacturaService;
import com.gasolinera.api.ptoemision.model.PtoEmision;
import com.gasolinera.api.ptoemision.service.PtoEmisionService;
import com.gasolinera.api.seguridad.enums.NombreRol;
import com.gasolinera.api.seguridad.model.Usuario;
import com.gasolinera.api.seguridad.service.UsuarioService;
import com.gasolinera.api.util.Utils;
import com.gasolinera.api.util.dto.Filtro;

@Component
public class FacturaFacade {

	static final Logger LOGGER = Logger.getLogger(FacturaFacade.class.getName());

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PtoEmisionService ptoEmisionService;

	@Value("${empresa.facturacion.ambiente}")
	private String ambiente;

	@Value("${empresa.facturacion.ruc}")
	private String ruc;

	@Value("${empresa.facturacion.cantidad.decimales}")
	private static String CANT_DECIMALES;

	public FacturaRespuesta crearFactura(NuevaFactura nuevaFactura) throws Exception {
		Usuario usuario = usuarioService.usuarioLogueado();
		if (usuario.getPtoEmision() == null) {
			throw new Exception("El usuario no tiene asignado un punto de emision");
		}

		PtoEmision ptoEmision = usuario.getPtoEmision();

		Date fechaEmision = nuevaFactura.getFechaEmision();
		String codEst = ptoEmision.getCodEstablecimiento();
		String codPtoEmision = ptoEmision.getCodPtoEmision();
		String secuencial = ptoEmision.getSecuencial().toString();
		while (secuencial.length() > 0 && secuencial.length() < 9) {
			secuencial = "0" + secuencial;
		}

		String claveAcceso = Utils.claveAcceso(fechaEmision, ruc, ambiente, codEst, codPtoEmision, secuencial);

		Factura factura = new Factura();
		factura.setAmbiente(ambiente);
		factura.setClaveAcceso(claveAcceso);
		factura.setFechaEmision(fechaEmision);
		factura.setNombreCompletoCliente(nuevaFactura.getNombreCompletoCliente());
		factura.setTipoIdentificacion(nuevaFactura.getTipoIdentificacion());
		factura.setIdentificacion(nuevaFactura.getIdentificacion());
		factura.setDireccion(nuevaFactura.getDireccion());
		factura.setEmail(nuevaFactura.getEmail());
		factura.setEstablecimiento(codEst);
		factura.setPtoEmision(codPtoEmision);
		factura.setSecuencial(secuencial);
		factura.setCreadaPor(usuario.getNombreUsuario());
		factura.setPlaca(nuevaFactura.getPlaca());

		Double totalSinImpuestos = 0.00;
		Double subtotal12 = 0.00;
		Double subtotal0 = 0.00;
		Double totalIva = 0.00;
		Double totalDescuento = 0.00;
		Double total = 0.00;
		Double porcentajeIva = 0.00;

		for (DetalleNuevaFactura dn : nuevaFactura.getDetalles()) {
			Detalle det = new Detalle();
			det.setCantidad(dn.getCantidad());
			det.setCodigoProducto(dn.getCodigoProducto());
			det.setNombreProducto(dn.getNombreProducto());
			det.setPrecioUnitario(dn.getPrecioUnitario());
			det.setDescuento(dn.getDescuento());
			Double subtotal = Utils.redondear(dn.getPrecioUnitario() * dn.getCantidad() - dn.getDescuento());
			det.setSubtotal(subtotal);
			det.setPorcentajeIva(dn.getPorcentajeIva());
			if (dn.getPorcentajeIva() > 0) {
				porcentajeIva = dn.getPorcentajeIva();
				subtotal12 = subtotal12 + subtotal;
				Double iva = subtotal * porcentajeIva / 100;
				det.setIva(Utils.redondear(iva));
			} else {
				subtotal0 = subtotal0 + subtotal;
				det.setIva(0.00);
			}

			totalDescuento = totalDescuento + dn.getDescuento();
			totalSinImpuestos = totalSinImpuestos + subtotal;
			factura.getDetalles().add(det);
		}

		factura.setTotalDescuento(Utils.redondear(totalDescuento));
		factura.setTotalSinImpuestos(Utils.redondear(totalSinImpuestos));
		factura.setSubtotal0(Utils.redondear(subtotal0));
		factura.setSubtotal12(Utils.redondear(subtotal12));
		if (subtotal12 > 0) {
			totalIva = subtotal12 * porcentajeIva / 100;
			totalIva = Utils.redondear(totalIva);
		}

		for (FormaPagoNuevaFactura fp : nuevaFactura.getFormasPago()) {
			FormaPago pago = new FormaPago();
			pago.setCodigo(fp.getCodigo());
			pago.setDescripcion(fp.getDescripcion());
			pago.setValor(fp.getValor());
			factura.getFormasPago().add(pago);
		}

		factura.setTotalIva(totalIva);

		total = Utils.redondear(totalSinImpuestos + totalIva);
		factura.setTotal(total);

		LOGGER.log(Level.INFO, "Usuario logueado {0}", usuario.getNombreUsuario());
		LOGGER.log(Level.INFO, "secuencial {0}", claveAcceso);

		ptoEmisionService.incrementarSecuencial(ptoEmision);

		return facturaService.guardar(factura);
	}

	public FacturaRespuesta buscarPorId(Integer id) {
		return facturaService.buscarPorId(id);
	}

	public List<FacturaRespuesta> buscarTodas(Filtro filtro) {
		Usuario usuario = usuarioService.usuarioLogueado();
		String nombreUsuario = usuario.getNombreUsuario();
		String busqueda = Utils.obtenerBusqueda(filtro);
		if (NombreRol.ADMINISTRADOR.name().equals(usuario.getRol().getNombreRol().name())) {
			nombreUsuario = null;
		}
		return facturaService.facturas(busqueda, nombreUsuario);
		
	}
}
