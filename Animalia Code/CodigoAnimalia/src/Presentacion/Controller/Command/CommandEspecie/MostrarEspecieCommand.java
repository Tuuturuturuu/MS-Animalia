/**
 * 
 */
package Presentacion.Controller.Command.CommandEspecie;

import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class MostrarEspecieCommand implements Command {

	public Context Execute(Object datos) {
		TEspecie res = FactoriaSA.getInstance().getEspecieSA().mostrarEspecie((Integer)datos);
		if(res.getID() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_ESPECIE_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_ESPECIE_KO,res);
		}
	}
}