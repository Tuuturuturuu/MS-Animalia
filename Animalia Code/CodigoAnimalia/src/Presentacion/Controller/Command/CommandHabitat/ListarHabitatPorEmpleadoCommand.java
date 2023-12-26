package Presentacion.Controller.Command.CommandHabitat;

import java.util.Set;

import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.THabitat;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarHabitatPorEmpleadoCommand implements Command {

	public Context Execute(Object datos) {
		
		Set<THabitat> res = FactoriaSA.getInstance().getHabitatSA().ListarHabitatPorEmpleado((Integer) datos);
		if(res.size() == 1){
			THabitat habitatUnica = res.iterator().next();
			if (habitatUnica.getId() <= 0)
				return new Context(Evento.LISTAR_HABITATS_POR_EMPLEADO_KO,habitatUnica);
			else{
				return new Context(Evento.LISTAR_HABITATS_POR_EMPLEADO_OK,res);
			}
		}else{
			return new Context(Evento.LISTAR_HABITATS_POR_EMPLEADO_OK,res);
		}
	}
}