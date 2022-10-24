package com.gasolinera.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.gasolinera.api.util.dto.Filtro;

public class Utils {
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	public static String formatDate(Date date, String formatPattern) {
		if (date != null) {
			if (formatPattern == null) {
				formatPattern = DATE_PATTERN;
			}
			SimpleDateFormat format1 = new SimpleDateFormat(formatPattern, new Locale("es", "EC"));
			String result = format1.format(date);
			return result;
		}
		return null;

	}

	public static Double redondear(Double valor) {
		return Math.round(valor * 100.0) / 100.0;
	}

	public static double redondearDecimales(double valorInicial, int numeroDecimales) {
		double fraccion = Math.pow(10, numeroDecimales); 
		return  Math.round(valorInicial * fraccion) /fraccion;
	}

	public static String claveAcceso(Date fechaEmision, String ruc, String ambiente, String establecimiento,
			String ptoEmision, String secuencial) {
		String fechaEmisionStr = formatDate(fechaEmision, DATE_PATTERN);
		String claveAcceso = fechaEmisionStr.replaceAll("-", "");
		claveAcceso += "01";
		claveAcceso += ruc;
		claveAcceso += ambiente;
		String serie = establecimiento + ptoEmision;
		claveAcceso += serie;
		claveAcceso += secuencial;
		claveAcceso += "12345678";
		claveAcceso += "1";
		claveAcceso += modulo11(claveAcceso);

		return claveAcceso;
	}

	private static String modulo11(String claveAcceso) {
		int[] multiplos = { 2, 3, 4, 5, 6, 7 };
		int i = 0;
		int cantidad = claveAcceso.length();
		int total = 0;
		while (cantidad > 0) {
			total += Integer.parseInt(claveAcceso.substring(cantidad - 1, cantidad)) * multiplos[i];
			i++;
			i = i % 6;
			cantidad--;
		}
		int modulo11 = 11 - total % 11;
		if (modulo11 == 11) {
			modulo11 = 0;
		} else if (modulo11 == 10) {
			modulo11 = 1;
		}

		return Integer.toString(modulo11);
	}

	public static String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer", "");
		}

		return null;
	}

	public static String obtenerBusqueda(Filtro filtro) {
		if (filtro != null && filtro.getBusqueda() != null && !filtro.getBusqueda().trim().isEmpty())
			return filtro.getBusqueda().trim();
		return null;
	}

}
