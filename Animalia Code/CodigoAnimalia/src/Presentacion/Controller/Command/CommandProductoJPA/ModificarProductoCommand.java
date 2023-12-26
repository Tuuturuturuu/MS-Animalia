/**
 * 
 */
package Presentacion.Controller.Command.CommandProductoJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ModificarProductoCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getProductoSA().modificarProducto((TProducto) datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_PRODUCTO_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_PRODUCTO_KO,res);
		}
	}
}