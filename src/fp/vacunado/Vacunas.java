package fp.vacunado;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vacunas {
	

	private Set<Vacunado> vacunas;
	
	public Vacunas() {
		vacunas=new HashSet<>();
	}
	
	public Vacunas(Stream<Vacunado> vacunas) {
		this.vacunas=vacunas.collect(Collectors.toSet());
	}
	
	public Vacunas(Set<Vacunado> vacunas) {
		this.vacunas=new HashSet<>(vacunas);
	}
	
	public Vacunas(Collection<Vacunado> vacunas) {
		this.vacunas=new HashSet<>(vacunas);
	}
	
	// ejercicio 1
	
	public Boolean existeAlgunUsuarioResidenteVacunadoEnSuCumple(Set<String> provincias) {
		Predicate<Vacunado> p1=v->v.fechaNacimiento().getMonth().equals(v.fechaNacimiento().getMonth());
		Predicate<Vacunado> p2=v->v.fechaNacimiento().getDayOfMonth()==(v.fechaNacimiento().getDayOfMonth());
		return vacunas.stream()
				.filter(v->provincias.contains(v.provincia()))
				.anyMatch(p1.and(p2));
	}
	
	public Boolean existeAlgunUsuarioResidenteVacunadoEnSuCumple2(Set<String> provincias) {
		return vacunas.stream()
				.filter(v->provincias.contains(v.provincia()))
				.anyMatch(v->v.fechaNacimiento().getDayOfYear()==(v.fechaAdministracion().getDayOfYear()));
	}
	
	//ejercicio 2
	public Double calculaLaEdadMediaDeVacunacionDeLosUsuarios(String provincia) {
		return vacunas.stream()
				.filter(v->v.provincia().equals(provincia) && v.pautaCompleta())
				.mapToInt(Vacunado::edad)
				.average()
				.getAsDouble();
	}
	
	public Map<String,Set<Marca>> conjuntoDeMarcasPorProvincia(){
		Map<String,Set<Marca>> res=new HashMap<>();
		for(Vacunado v:vacunas) {
			String clave=v.provincia();
			if(!res.containsKey(clave)) {
				Set<Marca> conjunto= new HashSet<>();
				conjunto.add(v.marca());
				res.put(clave, conjunto);
			}else {
				res.get(clave).add(v.marca());
			}
		}return res;
	}
	

}
