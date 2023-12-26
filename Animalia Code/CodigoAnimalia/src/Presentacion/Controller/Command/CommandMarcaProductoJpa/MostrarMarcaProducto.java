/**
 * 
 */
package Presentacion.Controller.Command.CommandMarcaProductoJpa;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class MostrarMarcaProducto implements Command {
	
	public Context Execute(Object datos) {

		TMarcaProducto res = FactoriaSA.getInstance().getASMarcaProducto().mostrarMarcaProducto((Integer)datos);
		if(res.getId() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_MARCA_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_MARCA_KO,res);
		}
	
	}
}