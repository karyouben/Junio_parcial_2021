package fp.vacunado;

import java.time.LocalDate;

public record Vacunado(String usuario,LocalDate fechaNacimiento,String provincia,Marca marca,LocalDate fechaAdministracion,Boolean pautaCompleta) {
	
	public Integer edad() {
		return fechaAdministracion().getYear()-fechaNacimiento.getYear();
	}
	


}
