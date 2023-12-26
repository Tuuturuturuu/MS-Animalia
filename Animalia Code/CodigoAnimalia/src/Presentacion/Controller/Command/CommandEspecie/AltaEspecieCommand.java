/**
 * 
 */
package Presentacion.Controller.Command.CommandEspecie;

import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class AltaEspecieCommand implements Command {

	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getEspecieSA().altaEspecie((TEspecie)datos);
		if(res > -1){
			return new Context(Evento.ALTA_ESPECIE_OK,res);
		}else {
			return new Context(Evento.ALTA_ESPECIE_KO,res);
		}
	}
}