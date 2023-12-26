/**
 * 
 */
package Presentacion.Controller.Command.CommandAnimal;

import Negocio.Animal.TAnimal;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ModificarAnimalCommand implements Command {

	@Override
	public Context Execute(Object datos) {
		// TODO Auto-generated method stub
		return null;
	}

	//public Context Execute(Object datos) {
//		int res = FactoriaSA.getInstance().getAnimalSA().modificarAnimal((TAnimal)datos);
//		if(res > -1){
//			return new Context(Evento.MODIFICAR_ANIMAL_OK,res);
//		}else {
//			return new Context(Evento.MODIFICAR_ANIMAL_KO,res);
//		}
	//}
}