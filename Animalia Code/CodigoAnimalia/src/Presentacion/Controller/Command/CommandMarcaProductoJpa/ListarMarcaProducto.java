/**
 * 
 */
package Presentacion.Controller.Command.CommandMarcaProductoJpa;

import java.util.List;
import java.util.Set;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class ListarMarcaProducto implements Command {

	public Context Execute(Object datos) {
		List<TMarcaProducto> res = FactoriaSA.getInstance().getASMarcaProducto().listarMarcaProducto();
		 return new Context(Evento.VLISTAR_MARCA, res);
	}
}