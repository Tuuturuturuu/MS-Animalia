package Presentacion.Controller.Command.CommandVentaJPA;

import java.util.List;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class ListarVentasPorTrabajadorCommand implements Command {

	public Context Execute(Object datos) {
		List<TVenta> res = FactoriaSA.getInstance().getASVenta().listarVentaPorTrabajador((Integer)datos);
		return new Context(Evento.LISTAR_VENTAS, res);
		 
	}
}