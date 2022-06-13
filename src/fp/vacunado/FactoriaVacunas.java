package fp.vacunado;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaVacunas {
	
	public static Set<Vacunado> leeVacunas(String fichero){
		Checkers.checkNoNull(fichero);
		List<String> lineas=Ficheros.leeFichero("Error al leer el fichero", fichero);
		lineas.remove(0);
		
		Set<Vacunado> res=new HashSet<Vacunado>();
		for(String linea:lineas) {
			Vacunado v=parseaVacunas(linea);
			res.add(v);
		}return res;
	}

	private static Vacunado parseaVacunas(String linea) {
		Checkers.checkNoNull(linea);
		String[] trozos=linea.split(",");
		String usuario=trozos[0].trim();
		LocalDate fechaNacimiento=LocalDate.parse(trozos[1].trim(),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String provincia=trozos[2].trim();
		Marca marca=Marca.valueOf(trozos[3].trim().toUpperCase());
		LocalDate fechaAdministracion=LocalDate.parse(trozos[4].trim(),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Boolean pautaCompleta=parseaBooleano(trozos[5].trim());
		return new Vacunado(usuario, fechaNacimiento, provincia, marca, fechaAdministracion, pautaCompleta);
	}
private static Boolean parseaBooleano(String cadena) {
	Boolean res=null;
	cadena=cadena.toUpperCase();
	if(cadena.equals("TRUE")) {
		res=true;
	}else if(cadena.equals("FALSE")) {
		res=false;
	}return res;
}

}
