/**
 * 
 */
package Presentacion.Controller.Command.CommandProveedorJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProveedorJPA.TProveedor;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class MostrarProveedorCommand implements Command {

	public Context Execute(Object datos) {
		TProveedor res = FactoriaSA.getInstance().getProveedorSA().mostrarProveedor((Integer)datos);
		if(res.getId() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_PROVEEDOR_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_PROVEEDOR_KO,res);
		}
	}
}