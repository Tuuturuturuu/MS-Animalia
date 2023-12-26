/**
 * 
 */
package Presentacion.Controller.Command.CommandEmpleado;

import java.util.Set;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class ListarEmpleadosPorHabitat implements Command {

	public Context Execute(Object datos) {
		Set<TEmpleado> res = FactoriaSA.getInstance().getEmpleadoSA().listarEmpleadoHabitat((Integer)datos);
		if(res.size() == 1){
			TEmpleado empleadoUnico = res.iterator().next();
			if (empleadoUnico.getId() <= 0)
				return new Context(Evento.LISTAR_EMPLEADOS_POR_HABITAT_KO, empleadoUnico);
			else{
				return new Context(Evento.LISTAR_EMPLEADOS_POR_HABITAT_OK, res);
			}
		}else{
			return new Context(Evento.LISTAR_EMPLEADOS_POR_HABITAT_OK,res);
		}
	}

}