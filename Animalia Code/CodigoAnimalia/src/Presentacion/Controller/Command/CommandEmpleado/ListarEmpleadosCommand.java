/**
 * 
 */
package Presentacion.Controller.Command.CommandEmpleado;

import java.util.Set;

import Negocio.Animal.TAnimal;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarEmpleadosCommand implements Command {

	public Context Execute(Object datos) {
		Set<TEmpleado> res = FactoriaSA.getInstance().getEmpleadoSA().listarEmpleado();
		return new Context(Evento.VLISTAR_EMPLEADO, res);
	}
}