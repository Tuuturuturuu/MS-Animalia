package Presentacion.Controller.Command.CommandHabitat;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.THabitat;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class ModificarHabitatCommand implements Command {

	public Context Execute(Object datos) {
		
		int ok = FactoriaSA.getInstance().getHabitatSA().ModificarHabitat((THabitat)datos);
		
		if (ok > -1 ) {
			return new Context(Evento.MODIFICAR_HABITAT_OK, ok);
		}
		
		else {
			return new Context(Evento.MODIFICAR_HABITAT_KO, ok); 
		}
	}
}