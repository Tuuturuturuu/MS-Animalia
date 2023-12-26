/**
 * 
 */
package Presentacion.Controller.Command.CommandDepartamento;

import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Pase.TPase;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ModificarDepartamentoCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getASDepartamento().modificarDepartamento((TDepartamento)datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_DEPARTAMENTO_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_DEPARTAMENTO_KO,res);
		}
	}
}