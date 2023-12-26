package Presentacion.Controller.Command.CommandHabitat;

import java.util.Set;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.THabitat;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarHabitatCommand implements Command {

	public Context Execute(Object datos) {
		
		Set<THabitat> ok = FactoriaSA.getInstance().getHabitatSA().ListarHabitat();
		
		return new Context(Evento.VLISTAR_HABITAT, ok);
	}
}