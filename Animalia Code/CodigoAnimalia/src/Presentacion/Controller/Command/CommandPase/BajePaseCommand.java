/**
 * 
 */
package Presentacion.Controller.Command.CommandPase;

import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class BajePaseCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getPaseSA().Baja((Integer)datos);
		if(res > -1){
			return new Context(Evento.BAJA_PASE_OK,res);
		}else {
			return new Context(Evento.BAJA_PASE_KO,res);
		}
	}
}