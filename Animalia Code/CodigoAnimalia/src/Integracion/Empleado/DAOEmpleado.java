package Integracion.Empleado;

import Negocio.Empleado.TEmpleado;
import java.util.Set;

public interface DAOEmpleado {

    public Integer alta(TEmpleado empleado);

    public Integer baja(Integer idEmpleado);

    public TEmpleado mostrar(Integer idEmpleado);

    public Integer modificar(TEmpleado empleado);

    public Set<TEmpleado> listarEmpleados();

    public Set<TEmpleado> listarPorHabitat(Integer idHabitat);

    public TEmpleado leerPorDniUnico(String dniEmpleado);

	public Set<TEmpleado> listarEmpleadosLimpieza();
	
	public Set<TEmpleado> listarEmpleadosZoologico();
}
