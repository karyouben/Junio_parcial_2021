package fp.vacunado.test;

import java.util.Set;

import fp.vacunado.FactoriaVacunas;
import fp.vacunado.Vacunado;

public class TestFactoriaVacunas {

	public static void main(String[] args) {
		testLeeVacunas("data/Vacunas.csv");

	}

	private static void testLeeVacunas(String fichero) {
		System.out.println("\nTestLeeVacunas ==========");
		Set<Vacunado> vacunas=FactoriaVacunas.leeVacunas(fichero);
		System.out.println(" Vacunado: ");
		for(Vacunado v:vacunas) {
			System.out.println(v);
		}
		
	}

}
