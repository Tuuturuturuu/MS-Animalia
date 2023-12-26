/**
 * 
 */
package Presentacion.Controller.Command.CommandEmpleado;

import java.util.HashMap;
import java.util.Set;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarEmpleadosPorEspecieEnHabitat implements Command {

	public Context Execute(Object datos) {
				
		try {
			Set<TEmpleado> res = FactoriaSA.getInstance().getEmpleadoSA().ListarEmpleadosPorEspecieEnHabitat((Integer)datos);
			
			if(res.size() == 1){
				
				TEmpleado empleadoUnico = res.iterator().next();
				
				if (empleadoUnico.getId() <= 0){
					return new Context(Evento.LISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT_KO, empleadoUnico);
				}
				else {
					return new Context(Evento.LISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT_OK, res);
				}
			} else {
				return new Context(Evento.LISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT_OK,res);
			}
			
		}catch (Exception e) {
			return new Context(Evento.LISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT_KO);
		}
	}
}