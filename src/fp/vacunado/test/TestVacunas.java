package fp.vacunado.test;

import java.util.Set;

import fp.vacunado.FactoriaVacunas;
import fp.vacunado.Vacunado;
import fp.vacunado.Vacunas;

public class TestVacunas {

	public static void main(String[] args) {
		Set<Vacunado> vacunas=FactoriaVacunas.leeVacunas("data/Vacunas.csv");
		Vacunas v= new Vacunas(vacunas);
		System.out.println("\nTestExisteAlgunUsuarioResidenteVacunadoEnSuCumple");
		System.out.println("===================================================");
		testExisteAlgunUsuarioResidenteVacunadoEnSuCumple(v,Set.of("ESPAÑA","PORTUGAL"));
		System.out.println("\nTestCalculaLaEdadMediaDeVacunacionDeLosUsuarios");
		System.out.println("===================================================");
		testCalculaLaEdadMediaDeVacunacionDeLosUsuarios(v,"ESPAÑA");

	}


	private static void testExisteAlgunUsuarioResidenteVacunadoEnSuCumple(Vacunas v, Set<String> provincias) {
		try {
			String msg=String.format("Existe algun usuario residente en las provincias %d que coincida su cumple con la fecha de vacunacion? %s ",
					provincias,v.existeAlgunUsuarioResidenteVacunadoEnSuCumple(provincias));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesesperada capturada " +e.getMessage());
		}
		
	}
	

	private static void testCalculaLaEdadMediaDeVacunacionDeLosUsuarios(Vacunas v, String provincia) {
		try {
			String msg=String.format("La media de vacunas en la provincia %d es: %s ",
					provincia,v.calculaLaEdadMediaDeVacunacionDeLosUsuarios(provincia));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesesperada capturada " +e.getMessage());
		}
	}

}
