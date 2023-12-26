package Presentacion.Controller.Command.CommandProductoJPA;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class MostrarProductoCommand implements Command {
	public Context Execute(Object datos) {
		TProducto res = FactoriaSA.getInstance().getProductoSA().mostrarProducto((Integer)datos);
		if(res.getID() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_PRODUCTO_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_PRODUCTO_KO,res);
		}
	}
}