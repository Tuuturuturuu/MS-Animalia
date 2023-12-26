/**
 * 
 */
package Presentacion.Controller.Command.CommandProductoJPA;

import java.util.List;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarProductosPorMarcaCommand implements Command {

	public Context Execute(Object datos) {
		List<TProducto> ok = FactoriaSA.getInstance().getProductoSA().listarProductosPorMarcasDeProducto((Integer) datos);

		return new Context(Evento.VLISTAR_PRODUCTO, ok);
		
	}
}