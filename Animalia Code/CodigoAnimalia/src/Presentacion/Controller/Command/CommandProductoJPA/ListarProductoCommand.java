package Presentacion.Controller.Command.CommandProductoJPA;

import java.util.List;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarProductoCommand implements Command {
	public Context Execute(Object datos) {
		List<TProducto> res = FactoriaSA.getInstance().getProductoSA().listarProductos();
		 return new Context(Evento.VLISTAR_PRODUCTO, res);
	}
}