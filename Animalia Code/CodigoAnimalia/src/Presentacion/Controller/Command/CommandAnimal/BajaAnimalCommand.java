/**
 * 
 */
package Presentacion.Controller.Command.CommandAnimal;

import Negocio.Animal.TAnimal;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class BajaAnimalCommand implements Command{

	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getAnimalSA().bajaAnimal((Integer)datos);
		Context resContext;
		
		if(res >= 0){
			resContext = new Context(Evento.BAJA_ANIMAL_OK, res);
		}else{
			resContext = new Context(Evento.BAJA_ANIMAL_KO, res);
		}
		
		return resContext;
	}
}