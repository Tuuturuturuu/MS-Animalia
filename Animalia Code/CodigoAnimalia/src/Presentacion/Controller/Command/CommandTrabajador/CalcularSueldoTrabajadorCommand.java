package Presentacion.Controller.Command.CommandTrabajador;

import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class CalcularSueldoTrabajadorCommand implements Command {

	public Context Execute(Object datos) {

		Double res = FactoriaSA.getInstance().getTrabajadorSA().calcularSueldoTrabajador((int) datos);

		if (res > -1) {
			return new Context(Evento.CALCULAR_SUELDO_TRABAJADOR_OK, res);
		} else {
			return new Context(Evento.CALCULAR_SUELDO_TRABAJADOR_KO, res);
		}
	}
}