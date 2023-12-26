/**
 * 
 */
package Presentacion.Controller.Command.CommandDepartamento;


import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class BajaDepartamentoCommand implements Command {
	
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getASDepartamento().bajaDepartamento((Integer)datos);
		if(res > -1){
			return new Context(Evento.BAJA_DEPARTAMENTO_OK,res);
		}else {
			return new Context(Evento.BAJA_DEPARTAMENTO_KO,res);
		}
	}
}