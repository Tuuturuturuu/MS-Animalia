/**
 * 
 */
package Presentacion.Controller.Command.CommandProductoJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Pase.TPase;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class AltaProductoCommand implements Command {
	
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getProductoSA().altaProducto((TProducto) datos);
		if(res > -1){
			return new Context(Evento.ALTA_PRODUCTO_OK,res);
		}else {
			return new Context(Evento.ALTA_PRODUCTO_KO,res);
		}
	}
}