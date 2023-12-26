package Integracion.Habitat;

import Negocio.Habitat.THabitat;
import java.util.Set;

public interface DAOHabitat {

	public Integer altaHabitat(THabitat habitat);

	public Integer bajaHabitat(Integer idHabitat);

	public THabitat mostarHabitat(Integer idHabitat);

	public Integer modificarHabitat(THabitat habitat);

	public Set<THabitat> listarHabitats();

	public Set<THabitat> listarHabitatPorEmpleado(Integer idEmpleado);

	public THabitat leerPorNombreUnico(String nombreHabitat);
}