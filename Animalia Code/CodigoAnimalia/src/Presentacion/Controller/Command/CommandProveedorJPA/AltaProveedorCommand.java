/**
 * 
 */
package Presentacion.Controller.Command.CommandProveedorJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProveedorJPA.TProveedor;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class AltaProveedorCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getProveedorSA().altaProveedor((TProveedor) datos);
		if(res > -1){
			return new Context(Evento.ALTA_PROVEEDOR_OK,res);
		}else {
			return new Context(Evento.ALTA_PROVEEDOR_KO,res);
		}
	}
}