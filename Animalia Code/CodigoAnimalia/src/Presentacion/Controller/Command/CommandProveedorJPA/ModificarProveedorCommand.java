/**
 * 
 */
package Presentacion.Controller.Command.CommandProveedorJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProveedorJPA.TProveedor;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class ModificarProveedorCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getProveedorSA().modificarProveedor((TProveedor)datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_PROVEEDOR_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_PROVEEDOR_KO,res);
		}
	}
}