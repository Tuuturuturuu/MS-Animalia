/**
 * 
 */
package Presentacion.Controller.Command.CommandPase;

import java.util.Set;

import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Pase.TPase;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarPasesPorHabitat implements Command {
	public Context Execute(Object datos) {
		Set<TPase> res = FactoriaSA.getInstance().getPaseSA().ListarPasePorHabitat((Integer)datos);
		if(res.size() == 1){
			TPase paseUnico = res.iterator().next();
			if (paseUnico.getID() <= 0)
				return new Context(Evento.LISTAR_PASES_POR_HABITAT_KO,paseUnico);
			else{
				return new Context(Evento.LISTAR_PASES_POR_HABITAT_OK,res);
			}
		}else{
			return new Context(Evento.LISTAR_PASES_POR_HABITAT_OK,res);
		}
	}
}