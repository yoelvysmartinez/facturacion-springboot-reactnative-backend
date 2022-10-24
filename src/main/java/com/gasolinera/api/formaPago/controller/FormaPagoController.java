package com.gasolinera.api.formaPago.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasolinera.api.formaPago.dto.FormaPago;

@Controller
@RequestMapping("/forma-pago")
public class FormaPagoController {

	@GetMapping
	public ResponseEntity<List<FormaPago>> formasPagoFacturacionElectronica() {

		List<FormaPago> formasPago = new LinkedList<>();
        formasPago.add(new FormaPago("01", "EFECTIVO"));
        formasPago.add(new FormaPago("16", "TARJETA DE DÉBITO"));
        formasPago.add(new FormaPago("19", "TARJETA DE CRÉDITO"));
        formasPago.add(new FormaPago("20", "OTROS CON UTILIZACION DEL SISTEMA FINANCIERO"));
        formasPago.add(new FormaPago("15", "COMPENSACIÓN DE DEUDAS"));
        formasPago.add(new FormaPago("17", "DINERO ELECTRÓNICO"));
        formasPago.add(new FormaPago("18", "TARJETA PREPAGO"));
        formasPago.add(new FormaPago("21", "ENDOSO DE TÍTULOS"));
		
		
		return new ResponseEntity<List<FormaPago>>(formasPago, HttpStatus.OK);
	}
}
