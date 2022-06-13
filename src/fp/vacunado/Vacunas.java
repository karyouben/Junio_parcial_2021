package fp.vacunado;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
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
	
	
	@Override
	public String toString() {
		return "Vacunas [vacunas=" + vacunas + "]";
	}

	public Set<Vacunado> getVacunas() {
		return vacunas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(vacunas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vacunas other = (Vacunas) obj;
		return Objects.equals(vacunas, other.vacunas);
	
	// ejercicio 1
	

	}

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
	
	//ejercicio 3
	
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
	
	//ejercicio 4
	
	public List<String> listaNombreUsuariosDeMayorEdadVacunadosCompletos(Marca marca,Integer n){
		return vacunas.stream()
				.filter(v->v.marca().equals(marca) && v.pautaCompleta())
				.sorted(Comparator.comparing(Vacunado::edad).thenComparing(Comparator.comparing(Vacunado::fechaAdministracion)).reversed())
				.limit(n)
				.map(Vacunado::usuario)
				.collect(Collectors.toList());
	}
	
	//ejercicio 5
	
	public Map<String,Integer> usuarioMasJovenVacunadoPorProvincia(){
		return vacunas.stream()
				.filter(v->v.pautaCompleta())
				.collect(Collectors.groupingBy(Vacunado::usuario,
						Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Vacunado::fechaNacimiento).reversed()
								.thenComparing(Comparator.naturalOrder())), v->v.get().edad())));
	}
	
	//ejercicio 6
	public Map<Marca,Double> porcentajeVacunasPorUsuarioPorRangoDeEdad(Integer edadMin,Integer edadMax){
		Map<Marca,Double> res=null;
		Map<Marca,Long> m=numeroUsuariosPorMarca(edadMin, edadMax);
		Long p=numeroUsuariosVacunadosTotal(edadMin, edadMax);
		
		if(p>0) {
			res=m.entrySet().stream()
					.collect(Collectors.toMap(v->v.getKey(), v->v.getValue()*100.0/p));
		}return res;
	}
	


	private Map<Marca,Long> numeroUsuariosPorMarca(Integer edadMin,Integer edadMax){
		return vacunas.stream()
				.filter(v->v.edad()>=edadMin && v.edad()<=edadMax)
				.collect(Collectors.groupingBy(Vacunado::marca,Collectors.counting()));
	}
	
	private Long numeroUsuariosVacunadosTotal(Integer edadMin, Integer edadMax) {
		return vacunas.stream()
				.filter(v->v.edad()>=edadMin && v.edad()<=edadMax)
				.count();
	}
	
	//ejercicio 7
	
	public LocalDate fechaEnLaQueSeAdministraronMasDosis(Marca marca,String provincia) {
		Map<LocalDate,Integer> m=dosisPorFecha(marca, provincia);
		Comparator<Map.Entry<LocalDate, Integer>> c=Comparator.comparing(Map.Entry::getValue);
		return m.entrySet().stream()
				.max(c)
				.get()
				.getKey();
	}
	
	public Map<LocalDate,Integer> dosisPorFecha(Marca marca,String provincia){
		return vacunas.stream()
				.filter(v->v.marca().equals(marca) && v.provincia().equals(provincia))
				.collect(Collectors.groupingBy(Vacunado::fechaAdministracion,Collectors.collectingAndThen(Collectors.counting(), v->v.intValue())));
		
	}


}
