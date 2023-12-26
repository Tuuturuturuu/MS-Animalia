package Negocio.Empleado;

import java.util.Set;

public interface EmpleadoSA {
	
	public Integer altaEmpleado(TEmpleado empleado);

	public Integer bajaEmpleado(Integer id_empleado);

	public Integer modificarEmpleado(TEmpleado empleado);

	public TEmpleado mostrarEmpleado(Integer id_empleado);

	public Set<TEmpleado> listarEmpleado();

	public Set<TEmpleado> listarEmpleadoHabitat(Integer idHabitat);

	public Set<TEmpleado> ListarEmpleadosPorEspecieEnHabitat(Integer idEspecie);

	public Set<TEmpleado> listarEmpleadosLimpieza();
	
	public Set<TEmpleado> listarEmpleadosZoologico();
}