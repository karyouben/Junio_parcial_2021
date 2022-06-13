package fp.vacunado;

import java.time.LocalDate;

public record Vacunado(String usuario,LocalDate fechaNacimiento,String provincia,Marca marca,LocalDate fechaAdministracion,Boolean pautaCompleta) implements Comparable<Vacunado> {
	
	public Integer edad() {
		return fechaAdministracion().getYear()-fechaNacimiento.getYear();
	}
	
	public int compareTo(Vacunado o) {
		int res=this.usuario().compareTo(o.usuario());
		return res;
		}
	
	


}
