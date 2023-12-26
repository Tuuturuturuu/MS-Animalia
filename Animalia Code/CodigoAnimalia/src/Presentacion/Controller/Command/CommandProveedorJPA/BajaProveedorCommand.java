/**
 * 
 */
package Presentacion.Controller.Command.CommandProveedorJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class BajaProveedorCommand implements Command {
	
	public Context Execute(Object datos) {
		
			int res = FactoriaSA.getInstance().getProveedorSA().bajaProveedor((Integer)datos);
			if(res > -1){
				return new Context(Evento.BAJA_PROVEEDOR_OK,res);
			}else {
				return new Context(Evento.BAJA_PROVEEDOR_KO,res);
			}
		
	}
}