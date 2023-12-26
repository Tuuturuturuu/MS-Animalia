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

public class CalcularNominaDepartamentoCommand implements Command {
	
	
	public Context Execute(Object datos) {
		Double res = FactoriaSA.getInstance().getASDepartamento().calcularNominaDepartamento((Integer)datos);
		if(res > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.CALCULAR_NOMINA_DEPARTAMENTO_OK,res);
		}else {
			return new Context(Evento.CALCULAR_NOMINA_DEPARTAMENTO_KO,res);
		}
	}
}