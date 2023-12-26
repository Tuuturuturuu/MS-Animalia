package Presentacion.Controller.Command.CommandTrabajador;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.TrabajadorJPA.TTrabajador;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ModificarTrabajadorCommand implements Command {

	public Context Execute(Object datos) {

		int ok = FactoriaSA.getInstance().getTrabajadorSA().modificarTrabajador((TTrabajador) datos);

		if (ok > -1) {
			return new Context(Evento.MODIFICAR_TRABAJADOR_OK, ok);
		}

		else {
			return new Context(Evento.MODIFICAR_TRABAJADOR_KO, ok);
		}
	}
}