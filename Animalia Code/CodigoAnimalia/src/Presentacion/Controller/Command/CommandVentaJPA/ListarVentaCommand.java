
package Presentacion.Controller.Command.CommandVentaJPA;

import java.util.List;
import java.util.Set;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarVentaCommand implements Command {
	
	public Context Execute(Object datos) {
		List<TVenta> res = FactoriaSA.getInstance().getASVenta().listarVentas();
		return new Context(Evento.LISTAR_VENTAS, res);
	}
}