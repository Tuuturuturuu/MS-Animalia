package Presentacion.Controller.Command.CommandVentaJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.VentaJPA.TCarritoJPA;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.VentaJPA.claseContenedorErroresVenta;


public class CerrarVentaCommand implements Command {
	public Context Execute(Object datos) {
		claseContenedorErroresVenta contenedorDatos = (claseContenedorErroresVenta) datos;
		TVenta venta = contenedorDatos.gettVenta();
		TCarritoJPA carrito = contenedorDatos.getCarrito();
		carrito.setCompra(venta);
		int res = FactoriaSA.getInstance().getASVenta().cerrarVenta(carrito);
		//Para poder enviar el integer del error de la vista correcta
		venta.setId(res);
		carrito.setCompra(venta);
		if(res > -1){
			return new Context(Evento.CERRAR_VENTA_OK,carrito);
		}else {
			return new Context(Evento.CERRAR_VENTA_KO,carrito);
		}
	}
	
}