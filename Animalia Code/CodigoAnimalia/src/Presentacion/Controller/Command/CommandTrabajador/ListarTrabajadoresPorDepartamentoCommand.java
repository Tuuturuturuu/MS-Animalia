package Presentacion.Controller.Command.CommandTrabajador;

import java.util.List;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.TrabajadorJPA.TTrabajador;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

public class ListarTrabajadoresPorDepartamentoCommand implements Command {

	public Context Execute(Object datos) {

		List<TTrabajador> ok = FactoriaSA.getInstance().getTrabajadorSA()
				.listarTrabajadoresPorDepartamento((Integer) datos);

		return new Context(Evento.VLISTAR_TRABAJADOR, ok);

	}
}