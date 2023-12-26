/**
 * 
 */
package Presentacion.Controller.Command.CommandEmpleado;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class MostrarEmpleadoCommand implements Command {

	public Context Execute(Object datos) {
		TEmpleado res = FactoriaSA.getInstance().getEmpleadoSA().mostrarEmpleado((int) datos);
		if(res.getId() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_EMPLEADO_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_EMPLEADO_KO,res);
		}
	}
}