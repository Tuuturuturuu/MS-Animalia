package Presentacion.Controller.Command.CommandAnimal;

import Negocio.Animal.TAnimalAcuatico;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ModificarAnimalAcuaticoCommand implements Command{
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getAnimalSA().modificarAnimalAcuatico((TAnimalAcuatico)datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_ANIMAL_ACUATICO_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_ANIMAL_ACUATICO_KO,res);
		}
	}
}
