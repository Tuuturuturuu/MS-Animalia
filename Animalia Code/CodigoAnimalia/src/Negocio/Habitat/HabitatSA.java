package Negocio.Habitat;

import java.time.LocalDate;
import java.util.Set;

public interface HabitatSA {
	
	public Integer AltaHabitat(THabitat habitat);

	public Integer BajaHabitat(Integer idHabitat);

	public THabitat MostrarHabitat(Integer idHabitat);

	public Integer ModificarHabitat(THabitat habitat);

	public Set<THabitat> ListarHabitat();

	public Integer VincularEmpleadoHabitat(TTrabajo trabajo);

	public Integer DesvincularEmpleadoHabitat(TTrabajo trabajo);

	public Set<THabitat> ListarHabitatPorEmpleado(Integer idEmpleado);

	public THabitat CalcularHabitatMasIngresos(LocalDate inicio, LocalDate fin);
}