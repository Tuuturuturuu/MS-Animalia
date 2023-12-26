package Presentacion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import Negocio.DepartamentoJPA.Departamento;
import Negocio.EMFSingleton.EMFSingleton;
import Negocio.TrabajadorJPA.Trabajador;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;

public class main {

	public static void main(String[] args) {
		Context c = new Context(Evento.CREAR_MAIN_VIEW, null);
		ApplicationController.getInstance().manageRequest(c);
	}

}
