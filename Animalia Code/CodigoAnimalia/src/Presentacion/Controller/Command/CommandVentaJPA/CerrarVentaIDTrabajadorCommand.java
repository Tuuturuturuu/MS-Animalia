package Presentacion.Controller.Command.CommandVentaJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.TrabajadorJPA.TTrabajador;
import Negocio.VentaJPA.TCarritoJPA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class CerrarVentaIDTrabajadorCommand implements Command {

	public Context Execute(Object datos) {
		TCarritoJPA res = FactoriaSA.getInstance().getASVenta().comprobarTrabajador((Integer) datos);
		if(res.getVenta().getIdTrabajador() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.VCERRAR_VENTA, res);
		}else {
			return new Context(Evento.CERRAR_VENTA_IDTRABAJADOR_KO,res.getVenta().getIdTrabajador());
		}
	}

}
