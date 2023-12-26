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


public class ListarEspeciesCommand implements Command {

	public Context Execute(Object datos) {
		Set<TEspecie> res = FactoriaSA.getInstance().getEspecieSA().listarEspecies();
			return new Context(Evento.LISTAR_ESPECIES, res);
	}
}