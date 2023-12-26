/**
 * 
 */
package Presentacion.Controller.Command.CommandFactura;

import Integracion.Factura.TLineaFactura;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class DevolverFacturaCommand implements Command {

	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getFacturaSA().devolverFactura((TLineaFactura)datos);
		if(res > -1){
			return new Context(Evento.DEVOLVER_FACTURA_OK,res);
		}else {
			return new Context(Evento.DEVOLVER_FACTURA_KO,res);
		}
	}
}