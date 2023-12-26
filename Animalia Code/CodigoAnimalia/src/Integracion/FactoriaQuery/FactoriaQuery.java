package Integracion.FactoriaQuery;

import Integracion.FactoriaQuery.FactoriaQueryImp;
import Integracion.FactoriaQuery.Query;

public abstract class FactoriaQuery {

	private static FactoriaQuery instance;


	public static FactoriaQuery getInstance() {
		
		if(instance == null) instance = new FactoriaQueryImp();
		
		return instance;
	}

	public Query generateQuery(Integer id) {

		return null;
	}
	
	public abstract Query getNewQuery(String nombre);

}