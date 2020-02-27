package com.globant.mx.directv.utilities;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.ParseException;
import org.testng.log4testng.Logger;

public class FechasUtilerias {
	
	private static final Logger LOG = Logger.getLogger(FechasUtilerias.class);
	
	public static Date convertStringADate(String fechaOld) {
		// UNUSED
		return new Date();
	}
	

	public static Calendar obtenerCalendarHoy() {
		return  Calendar.getInstance();
	}
	
	
	public static String obtenerDateHoy(String mascara) {
		SimpleDateFormat formato = new SimpleDateFormat(mascara);
		return formato.format(new Date());
	}
	
	
	
	public static String convertDateToString(Date fecha, String mascara) {
		try {
			String DATE_FORMAT_NOW = mascara;
			 Date date = fecha;
			 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			 return sdf.format(date);
		}catch(ParseException ex) {
			LOG.error("ParseException on convertCalendarToString() " + ex.getMessage());
			return "";
		}
	}
	
	
	
	public static String convertCalendarToString(Calendar calendario, String mascara) {
		try {
			SimpleDateFormat format1 = new SimpleDateFormat(mascara);
			String formatted = format1.format(calendario.getTime());
			Date fecha =format1.parse(formatted); 
			return convertDateToString(fecha, mascara);
		} catch (java.text.ParseException e) {
			LOG.error("ParseException on convertCalendarToString() " + e.getMessage());
			return "";
		}
	}
	
	
	public static Calendar sumaMesesACalendario(Calendar calendario , int numMeses) {
		calendario.add(Calendar.MONTH, numMeses);
		return calendario;
	}
	
	public static Calendar sumarDiasACalendario(Calendar calendario, int diasASumas) {
		calendario.add(Calendar.DAY_OF_YEAR, diasASumas);
		return calendario;
	}
	
}
