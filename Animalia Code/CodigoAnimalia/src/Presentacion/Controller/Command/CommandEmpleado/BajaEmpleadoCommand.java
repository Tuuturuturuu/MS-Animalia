/**
 * 
 */
package Presentacion.Controller.Command.CommandEmpleado;

import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class BajaEmpleadoCommand implements Command {

	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getEmpleadoSA().bajaEmpleado((Integer)datos);
		Context resContext;
		
		if(res >= 0){
			resContext = new Context(Evento.BAJA_EMPLEADO_OK, res);
		}else{
			resContext = new Context(Evento.BAJA_EMPLEADO_KO, res);
		}
		
		return resContext;
	}
}