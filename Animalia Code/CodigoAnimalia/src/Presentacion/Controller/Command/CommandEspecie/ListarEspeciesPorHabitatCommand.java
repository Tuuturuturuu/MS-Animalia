/**
 * 
 */
package Presentacion.Controller.Command.CommandEspecie;

import java.util.Set;

import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarEspeciesPorHabitatCommand implements Command {

	public Context Execute(Object datos) {
		Set<TEspecie> res = FactoriaSA.getInstance().getEspecieSA().listarEspeciePorHabitat((Integer)datos);
		if(res.size() == 1){
			TEspecie especieUnica = res.iterator().next();
			if (especieUnica.getID() <= 0)
				return new Context(Evento.LISTAR_ESPECIE_POR_HABITAT_KO,especieUnica);
			else{
				return new Context(Evento.LISTAR_ESPECIE_POR_HABITAT_OK,res);
			}
		}else{
			return new Context(Evento.LISTAR_ESPECIE_POR_HABITAT_OK,res);
		}
	}
}