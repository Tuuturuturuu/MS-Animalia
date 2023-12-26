package Presentacion.Controller.Command.CommandEmpleado;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class AltaEmpleadoLimpiezaCommand  implements Command{

	@Override
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getEmpleadoSA().altaEmpleado((TEmpleado)datos);
		if(res > -1){
			return new Context(Evento.ALTA_EMPLEADO_LIMPIEZA_OK,res);
		}else {
			return new Context(Evento.ALTA_EMPLEADO_LIMPIEZA_KO,res);
		}
	}

}
