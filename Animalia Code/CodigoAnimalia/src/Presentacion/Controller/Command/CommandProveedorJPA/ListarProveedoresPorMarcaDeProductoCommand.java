
package Presentacion.Controller.Command.CommandProveedorJPA;

import java.util.List;
import java.util.Set;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProveedorJPA.TProveedor;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarProveedoresPorMarcaDeProductoCommand implements Command {

	public Context Execute(Object datos) {
		List<TProveedor> ok = FactoriaSA.getInstance().getProveedorSA().listarProveedoresPorMarcaDeProducto((Integer) datos);

		return new Context(Evento.VLISTAR_PROVEEDORES, ok);
	}

}