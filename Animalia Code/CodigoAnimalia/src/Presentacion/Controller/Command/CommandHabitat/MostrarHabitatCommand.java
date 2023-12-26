package Presentacion.Controller.Command.CommandHabitat;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.THabitat;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class MostrarHabitatCommand implements Command {

	public Context Execute(Object datos) {

		THabitat res = FactoriaSA.getInstance().getHabitatSA().MostrarHabitat((int) datos);
		if(res.getId() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_HABITAT_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_HABITAT_KO,res);
		}
	}
}