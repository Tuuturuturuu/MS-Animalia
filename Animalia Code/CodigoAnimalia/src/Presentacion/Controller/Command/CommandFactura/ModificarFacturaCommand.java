package Presentacion.Controller.Command.CommandFactura;

import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Factura.TFactura;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ModificarFacturaCommand implements Command {

	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getFacturaSA().modificarFactura((TFactura)datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_FACTURA_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_FACTURA_KO,res);
		}
	}
}