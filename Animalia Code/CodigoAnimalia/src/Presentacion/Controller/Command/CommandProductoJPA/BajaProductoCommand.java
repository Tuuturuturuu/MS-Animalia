/**
 * 
 */
package Presentacion.Controller.Command.CommandProductoJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class BajaProductoCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getProductoSA().bajaProducto((Integer) datos);
		if(res > -1){
			return new Context(Evento.BAJA_PRODUCTO_OK,res);
		}else {
			return new Context(Evento.BAJA_PRODUCTO_KO,res);
		}
	}
}