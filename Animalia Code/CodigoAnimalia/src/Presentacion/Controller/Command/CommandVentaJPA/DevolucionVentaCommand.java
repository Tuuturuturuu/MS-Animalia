package Presentacion.Controller.Command.CommandVentaJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.VentaJPA.TLineaVenta;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class DevolucionVentaCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getASVenta().devolucionVenta((TLineaVenta)datos);
		if(res > -1){
			return new Context(Evento.DEVOLVER_VENTA_OK,res);
		}else {
			return new Context(Evento.DEVOLVER_VENTA_KO,res);
		}
	}
}