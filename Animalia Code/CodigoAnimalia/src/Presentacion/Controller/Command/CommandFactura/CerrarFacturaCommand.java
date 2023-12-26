/**
 * 
 */
package Presentacion.Controller.Command.CommandFactura;

import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.Factura.claseContenedorErroresFactura;

public class CerrarFacturaCommand implements Command {
	public Context Execute(Object datos) {
		claseContenedorErroresFactura contenedorDatos = (claseContenedorErroresFactura) datos;
		TFactura factura = contenedorDatos.gettFactura();
		TCarrito carrito = contenedorDatos.getCarrito();
		int res = FactoriaSA.getInstance().getFacturaSA().cerrarFactura(factura, carrito);
		if(res > -1){
			return new Context(Evento.CERRAR_FACTURA_OK,res);
		}else {
			return new Context(Evento.CERRAR_FACTURA_KO,res);
		}
	}
}