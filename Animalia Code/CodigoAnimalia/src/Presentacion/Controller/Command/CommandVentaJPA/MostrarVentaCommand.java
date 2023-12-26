package Presentacion.Controller.Command.CommandVentaJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.VentaJPA.TVenta;
import Negocio.VentaJPA.TVentaConProductos;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class MostrarVentaCommand implements Command {
	
	public Context Execute(Object datos) {
		TVentaConProductos res = FactoriaSA.getInstance().getASVenta().mostrarVenta((Integer)datos);
		TVenta venta = res.gettVenta();
		if (venta.getId() <= 0)
			return new Context(Evento.MOSTRAR_VENTA_KO,venta);
		else
			return new Context(Evento.MOSTRAR_VENTA_OK,res);
	}
}