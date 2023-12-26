package Presentacion.Controller.Command.CommandAnimal;

import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Negocio.Animal.TAnimalNoAcuatico;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ModificarAnimalSelectCommand implements Command {
	public Context Execute(Object datos) {
		TAnimal res = FactoriaSA.getInstance().getAnimalSA().mostrarAnimal((Integer)datos);
		if(res.getTipo() == 1){
			return new Context(Evento.VMODIFICAR_ANIMAL_ACUATICO,new TAnimalAcuatico(res));
		}else if (res.getTipo() == 0){
			return new Context(Evento.VMODIFICAR_ANIMAL_NO_ACUATICO,new TAnimalNoAcuatico(res));
		}else{
			return new Context(Evento.MODIFICAR_ANIMAL_KO,res);
		}
	}
}
