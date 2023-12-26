package Presentacion.Controller.Command.CommandVentaJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class ModificarVentaCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getASVenta().modificarVenta((TVenta)datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_VENTA_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_VENTA_KO,res);
		}
	}
}