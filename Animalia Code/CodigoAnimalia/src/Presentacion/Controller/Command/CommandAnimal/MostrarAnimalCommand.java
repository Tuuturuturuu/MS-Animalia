/**
 * 
 */
package Presentacion.Controller.Command.CommandAnimal;

import Negocio.Animal.TAnimal;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class MostrarAnimalCommand implements Command {

	public Context Execute(Object datos) {
		TAnimal res = FactoriaSA.getInstance().getAnimalSA().mostrarAnimal((int) datos);
		if(res.getId() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_ANIMAL_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_ANIMAL_KO,res);
		}
	}
}