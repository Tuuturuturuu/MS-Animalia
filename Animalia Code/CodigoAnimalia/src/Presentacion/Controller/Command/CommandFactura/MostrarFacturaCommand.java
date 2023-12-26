/**
 * 
 */
package Presentacion.Controller.Command.CommandFactura;

import java.util.Set;

import Integracion.Factura.TLineaFactura;
import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConPases;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class MostrarFacturaCommand implements Command {

	public Context Execute(Object datos) {
		TFacturaConPases res = FactoriaSA.getInstance().getFacturaSA().mostrarFactura((Integer)datos);
		TFactura factura = res.getFactura();
		if (factura.GetId() <= 0)
			return new Context(Evento.MOSTRAR_FACTURA_KO,factura);
		else
			return new Context(Evento.MOSTRAR_FACTURA_OK,res);
		
	}
}