package Presentacion.FactoriaVistas;

import Presentacion.Controller.Command.Context;

public abstract class FactoriaVistas {

	private static FactoriaVistas instance;

	public static synchronized FactoriaVistas getInstance() {
		if(instance == null)
		{
			instance = new FactoriaVistasImp();
		}
		return instance;
	}

	public abstract IGUI generarVista(Context context);
}