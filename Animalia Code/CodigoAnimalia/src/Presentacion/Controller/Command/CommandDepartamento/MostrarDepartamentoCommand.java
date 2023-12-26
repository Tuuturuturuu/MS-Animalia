/**
 * 
 */
package Presentacion.Controller.Command.CommandDepartamento;

import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Pase.TPase;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class MostrarDepartamentoCommand implements Command {
	
	public Context Execute(Object datos) {
		TDepartamento res = FactoriaSA.getInstance().getASDepartamento().mostrarDepartamento((Integer)datos);
		if(res.getId() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_DEPARTAMENTO_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_DEPARTAMENTO_KO,res.getId());
		}
	}
}