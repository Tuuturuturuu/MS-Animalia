/**
 * 
 */
package Presentacion.Controller.Command.CommandEmpleado;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ModificarEmpleadoCommand implements Command {

	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getEmpleadoSA().modificarEmpleado((TEmpleado)datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_EMPLEADO_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_EMPLEADO_KO,res);
		}
	}
}