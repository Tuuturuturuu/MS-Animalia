/**
 * 
 */
package Presentacion.Controller.Command.CommandMarcaProductoJpa;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class AltaMarcaProducto implements Command {

	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getASMarcaProducto().altaMarcaProducto((TMarcaProducto) datos);
		
		if(res > -1) {
			return new Context(Evento.ALTA_MARCA_OK, res);
		} else {
			return new Context(Evento.ALTA_MARCA_KO, res);
		}
	}
}