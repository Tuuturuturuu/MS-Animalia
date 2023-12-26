/**
 * 
 */
package Presentacion.Controller.Command.CommandProveedorJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.TTrabajo;
import Negocio.ProveedorJPA.TProveedorConMarcas;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class VincularProveedorConMarcaDeProductoCommand implements Command {

	public Context Execute(Object datos) {
		int ok = FactoriaSA.getInstance().getProveedorSA().vincularProveedorConMarcaDeProducto((TProveedorConMarcas) datos);
		
		if (ok >= 0 ) {
			return new Context(Evento.VINCULAR_PROVEEDOR_A_MARCA_OK, ok);
		}
		
		else {
			return new Context(Evento.VINCULAR_PROVEEDOR_A_MARCA_KO, ok);
		}
	}
}