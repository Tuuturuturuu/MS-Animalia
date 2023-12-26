package Presentacion.Controller.Command.CommandHabitat;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.TTrabajo;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class VincularEmpleadoHabitatCommand implements Command {

	public Context Execute(Object datos) {
		
		int ok = FactoriaSA.getInstance().getHabitatSA().VincularEmpleadoHabitat((TTrabajo) datos);
		
		if (ok >= 0 ) {
			return new Context(Evento.VINCULAR_EMPLEADO_A_HABITAT_OK, ok);
		}
		
		else {
			return new Context(Evento.VINCULAR_EMPLEADO_A_HABITAT_KO, ok);
		}
	}
}