package Presentacion.Controller.Command.CommandHabitat;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.THabitat;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.Evento;


public class AltaHabitatCommand implements Command {

	public Context Execute(Object datos) {
		
		int ok = FactoriaSA.getInstance().getHabitatSA().AltaHabitat((THabitat) datos);
		
		if (ok >= 0 ) {
			return new Context(Evento.ALTA_HABITAT_OK, ok);
		}
		
		else {
			return new Context(Evento.ALTA_HABITAT_KO, ok);
		}
	}
}