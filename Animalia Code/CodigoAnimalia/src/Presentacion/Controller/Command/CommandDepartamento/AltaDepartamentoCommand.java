/**
 * 
 */
package Presentacion.Controller.Command.CommandDepartamento;

import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Pase.TPase;
import Presentacion.Evento;

public class AltaDepartamentoCommand implements Command {
	
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getASDepartamento().altaDepartamento((TDepartamento) datos);
		if(res > -1){
			return new Context(Evento.ALTA_Departamento_OK,res);
		}else {
			return new Context(Evento.ALTA_Departamento_KO,res);
		}
	}
}