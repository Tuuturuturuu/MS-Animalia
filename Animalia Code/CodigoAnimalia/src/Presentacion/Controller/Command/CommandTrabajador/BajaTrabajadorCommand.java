package Presentacion.Controller.Command.CommandTrabajador;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.TrabajadorJPA.TTrabajador;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class BajaTrabajadorCommand implements Command {

	public Context Execute(Object datos) {

		int res = FactoriaSA.getInstance().getTrabajadorSA().bajaTrabajador((int) datos);
		if (res > -1) {
			return new Context(Evento.BAJA_TRABAJADOR_OK, res);
		} else {
			return new Context(Evento.BAJA_TRABAJADOR_KO, res);
		}

	}
}