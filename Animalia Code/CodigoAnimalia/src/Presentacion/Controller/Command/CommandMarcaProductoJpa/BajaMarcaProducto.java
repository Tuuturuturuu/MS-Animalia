/**
 * 
 */
package Presentacion.Controller.Command.CommandMarcaProductoJpa;

import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class BajaMarcaProducto implements Command {

	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getASMarcaProducto().bajaMarcaProducto((Integer) datos);
		if(res > -1){
			return new Context(Evento.BAJA_MARCA_OK,res);
		}else {
			return new Context(Evento.BAJA_MARCA_KO,res);
		}
	}
}