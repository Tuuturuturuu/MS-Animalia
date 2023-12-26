/**
 * 
 */
package Presentacion.Controller.Command.CommandDepartamento;

import java.util.List;
import java.util.Set;

import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Pase.TPase;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarDepartamentoCommand implements Command {
	
	public Context Execute(Object datos) {
		List<TDepartamento> res = FactoriaSA.getInstance().getASDepartamento().listarDepartamento();
		 return new Context(Evento.VLISTAR_DEPARTAMENTO, res);
	}
}