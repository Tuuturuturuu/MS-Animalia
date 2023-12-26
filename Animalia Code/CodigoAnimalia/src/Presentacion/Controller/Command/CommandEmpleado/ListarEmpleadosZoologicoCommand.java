package Presentacion.Controller.Command.CommandEmpleado;

import java.util.Set;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarEmpleadosZoologicoCommand implements Command{

	public Context Execute(Object datos) {
		Set<TEmpleado> res = FactoriaSA.getInstance().getEmpleadoSA().listarEmpleadosZoologico();
		return new Context(Evento.VLISTAR_EMPLEADOS_ZOOLOGICO,res);
	}
}
