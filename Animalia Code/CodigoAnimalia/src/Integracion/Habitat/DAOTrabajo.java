package Integracion.Habitat;

import Negocio.Habitat.TTrabajo;
import java.util.Set;

public interface DAOTrabajo {
	
	public Integer altaTrabajo(TTrabajo trabajo);

	public Integer bajaTrabajo(Integer idEmpleado, Integer idHabitat);

	public Set<TTrabajo> listarTrabajo();

	public Integer vincularHabitatEmpleado(TTrabajo trabajo);

	public Integer desvincularHabitatEmpleado(TTrabajo trabajo);

	public Set<TTrabajo> mostrarTrabajoPorEmpleado(Integer idEmpleado);

	public Set<TTrabajo> mostrarTrabajoPorHabitat(Integer idHabitat);

	public TTrabajo mostrarTrabajo(TTrabajo trabajo);
}