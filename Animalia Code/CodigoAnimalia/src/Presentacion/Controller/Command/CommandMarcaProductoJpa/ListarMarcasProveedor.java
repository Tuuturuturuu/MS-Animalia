package Presentacion.Controller.Command.CommandMarcaProductoJpa;

import java.util.List;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class ListarMarcasProveedor implements Command {
	
	public Context Execute(Object datos) {
		List<TMarcaProducto> res = FactoriaSA.getInstance().getASMarcaProducto().listarMarcaPorProveedor((Integer) datos);
		return new Context(Evento.VLISTAR_MARCA, res);
	}
}