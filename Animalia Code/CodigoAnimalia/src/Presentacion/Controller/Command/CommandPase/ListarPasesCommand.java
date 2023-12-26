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


public class ListarPasesCommand implements Command {
	
	public Context Execute(Object datos) {
		Set<TPase> res = FactoriaSA.getInstance().getPaseSA().Listar();
		return new Context(Evento.LISTAR_PASES,res);
	}
}