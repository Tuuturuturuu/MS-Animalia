package Presentacion.Controller.Command.CommandTrabajador;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.TrabajadorJPA.TTrabajador;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class MostrarTrabajadorCommand implements Command {

	public Context Execute(Object datos) {

		TTrabajador res = FactoriaSA.getInstance().getTrabajadorSA().mostrarTrabajador((int) datos);
		if (res.getId() > -1) {
			return new Context(Evento.MOSTRAR_TRABAJADOR_OK, res);
		} else {
			return new Context(Evento.MOSTRAR_TRABAJADOR_KO, res);
		}
	}
}