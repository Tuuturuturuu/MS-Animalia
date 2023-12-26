package Integracion.FactoriaQuery;

import Integracion.FactoriaQuery.CalcularHabitatConMasIngresos;
import Integracion.FactoriaQuery.ListarEmpleadoPorEspecieEnHabitat;
import Integracion.FactoriaQuery.Query;

public class FactoriaQueryImp extends FactoriaQuery {
	
	public Query getNewQuery(String nombre) {
		
		switch (nombre){
		
			case "CalcularHabitatConMasIngresos": 
				return new CalcularHabitatConMasIngresos();
			case "ListarEmpleadoPorEspecieEnHabitat" : 
				return new ListarEmpleadoPorEspecieEnHabitat(); 
		}
		
		return null;
	}
}