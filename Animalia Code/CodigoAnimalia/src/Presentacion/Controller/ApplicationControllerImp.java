package Presentacion.Controller;

import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.CommandFactory;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.FactoriaVistas;
import Presentacion.FactoriaVistas.IGUI;

public class ApplicationControllerImp extends ApplicationController {

	private IGUI curIGUI;
	
	public void manageRequest(Context context) {
		Command c = CommandFactory.getInstance().GetCommand(context.getEvento());
		if(c == null) //Hay redirección de vistas
		{
			curIGUI = FactoriaVistas.getInstance().generarVista(context);
			return;
		}
		else{
			Context responseContext = c.Execute(context.getDatos());
			if(FactoriaVistas.getInstance().generarVista(responseContext) == null)
				curIGUI.actualizar(responseContext);
			//else
				//FactoriaVistas.getInstance().generarVista(responseContext);
		}
	}
}
