
package Presentacion.Controller.Command.CommandProveedorJPA;

import java.util.Set;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProveedorJPA.TProveedor;
import Negocio.ProveedorJPA.TProveedorConMarcas;
import Negocio.TrabajadorJPA.TTrabajador;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class DesvincularProveedorConMarcaDeProductoCommand implements Command {

	@Override
	public Context Execute(Object datos) {
		int ok = FactoriaSA.getInstance().getProveedorSA().desvincularProveedorConMarcaDeProducto((TProveedorConMarcas) datos);
		
		if (ok >= 0 ) {
			return new Context(Evento.DESVINCULAR_PROVEEDOR_DE_MARCA_OK, ok);
		}
		
		else {
			return new Context(Evento.DESVINCULAR_PROVEEDOR_DE_MARCA_KO, ok);
		}
	}

	
}