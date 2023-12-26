package Presentacion.Controller.Command.CommandAnimal;

import Negocio.Animal.TAnimal;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class AltaAnimalNoAquaticoCommand implements Command {

	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getAnimalSA().altaAnimal((TAnimal)datos);
		if(res > -1){
			return new Context(Evento.ALTA_ANIMAL_NO_ACUATICO_OK,res);
		}else {
			return new Context(Evento.ALTA_ANIMAL_NO_ACUATICO_KO,res);
		}
	}
}