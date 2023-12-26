package Presentacion.Controller.Command.CommandHabitat;

import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.Evento;


public class BajaHabitatCommand implements Command {

	public Context Execute(Object datos) {
		
		int ok = FactoriaSA.getInstance().getHabitatSA().BajaHabitat((int)datos);
		
		if (ok >= 0 ) {
			return new Context(Evento.BAJA_HABITAT_OK, ok);
		}
		
		else {
			return new Context(Evento.BAJA_HABITAT_KO, ok);
		}
	}
}