/**
 * 
 */
package Presentacion.Controller.Command.CommandFactura;

import java.util.Set;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Factura.TFactura;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarFacturasCommand implements Command {
	public Context Execute(Object datos) {
		 Set<TFactura> res = FactoriaSA.getInstance().getFacturaSA().listarFacturas();
		 return new Context(Evento.LISTAR_FACTURAS, res);
	}
}