/**
 * 
 */
package Presentacion.Controller.Command.CommandPase;

import Negocio.Pase.TPase;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class AltaPaseCommand implements Command {
	
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getPaseSA().Alta((TPase) datos);
		if(res > -1){
			return new Context(Evento.ALTA_PASE_OK,res);
		}else {
			return new Context(Evento.ALTA_PASE_KO,res);
		}
	}
}