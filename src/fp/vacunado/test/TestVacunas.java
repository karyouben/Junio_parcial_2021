package fp.vacunado.test;

import java.util.Map;
import java.util.Set;

import fp.vacunado.FactoriaVacunas;
import fp.vacunado.Marca;
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
		System.out.println("\nTestConjuntoDeMarcasPorProvincia");
		System.out.println("===================================================");
		testConjuntoDeMarcasPorProvincia(v);
		System.out.println("\nTestListaNombreUsuariosDeMayorEdadVacunadosCompletos");
		System.out.println("===================================================");
		testListaNombreUsuariosDeMayorEdadVacunadosCompletos(v,Marca.ASTRAZENECA,3);
		System.out.println("\nTestUsuarioMasJovenVacunadoPorProvincia");
		System.out.println("===================================================");
		testUsuarioMasJovenVacunadoPorProvincia(v);
		System.out.println("\nTestPorcentajeVacunasPorUsuarioPorRangoDeEdad");
		System.out.println("===================================================");
		testPorcentajeVacunasPorUsuarioPorRangoDeEdad(v,10,20);
		System.out.println("\nTestFechaEnLaQueSeAdministraronMasDosis");
		System.out.println("===================================================");
		testFechaEnLaQueSeAdministraronMasDosis(v,Marca.MODERNA,"Sevilla");

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
	

	private static void testListaNombreUsuariosDeMayorEdadVacunadosCompletos(Vacunas v,Marca marca, Integer n) {
		try {
			String msg=String.format("La lista de los %d usuarios vacunados con " +marca+ " Son: %s",
					marca,n,v.listaNombreUsuariosDeMayorEdadVacunadosCompletos(marca, n));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesesperada capturada " +e.getMessage());
		}
	}
	
	private static void testUsuarioMasJovenVacunadoPorProvincia(Vacunas v) {
		try {
			String msg=String.format("Los usuarios mas jovenes vacunados por provincia son: ");
			System.out.println(msg);
			Map<String,Integer> p=v.usuarioMasJovenVacunadoPorProvincia();
			p.entrySet().stream().forEach(System.out::println);
		}catch(Exception e) {
			System.err.println("Excepcion inesesperada capturada " +e.getMessage());
		}
	}
	
	private static void testConjuntoDeMarcasPorProvincia(Vacunas v) {
		try {
			String msg=String.format("El conjunto de marcas por provincia son: ");
			System.out.println(msg);
			Map<String, Set<Marca>> p=v.conjuntoDeMarcasPorProvincia();
			p.entrySet().stream().forEach(System.out::println);
		}catch(Exception e) {
			System.err.println("Excepcion inesesperada capturada " +e.getMessage());
		}
	}
	
	private static void testPorcentajeVacunasPorUsuarioPorRangoDeEdad(Vacunas v, Integer edadMin, Integer edadMax) {
		try {
			String msg=String.format("El porcentaje de vacunas en el intervalo de edad "+edadMin+"-"+edadMax+" es: ");
			System.out.println(msg);
			Map<Marca, Double> p=v.porcentajeVacunasPorUsuarioPorRangoDeEdad(edadMin, edadMax);
			p.entrySet().stream().forEach(System.out::println);
		}catch(Exception e) {
			System.err.println("Excepcion inesesperada capturada " +e.getMessage());
		}
		
	}
	
	private static void testFechaEnLaQueSeAdministraronMasDosis(Vacunas v, Marca marca, String provincia) {
		try {
			String msg=String.format("La fecha en los que hay una mayor vacunacion de %d en la provincia " +provincia+ " es: %s",
					marca,provincia,v.fechaEnLaQueSeAdministraronMasDosis(marca, provincia));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesesperada capturada " +e.getMessage());
		}
		
	}

}
