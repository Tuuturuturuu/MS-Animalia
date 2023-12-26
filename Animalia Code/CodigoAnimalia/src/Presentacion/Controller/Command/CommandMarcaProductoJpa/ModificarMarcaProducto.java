/**
 * 
 */
package Presentacion.Controller.Command.CommandMarcaProductoJpa;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class ModificarMarcaProducto implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getASMarcaProducto().modificarMarcaProducto((TMarcaProducto) datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_MARCA_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_MARCA_KO,res);
		}
	}
}