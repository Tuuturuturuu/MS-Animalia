package Presentacion.Controller.Command.CommandHabitat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.THabitat;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

//REVISAR
public class CalcularHabitatMasIngresosCommand implements Command {

	public Context Execute(Object datos) {
		THabitat ok = new THabitat();
		try{
			HashMap<String, String> params = (HashMap<String, String>) datos;
			ok = FactoriaSA.getInstance().getHabitatSA().CalcularHabitatMasIngresos(ParseStringToLocalDate(params.get("fechaIni")), ParseStringToLocalDate(params.get("fechaFin")));

			if (ok != null ) {
				if(ok.getId() <= 0)
					return new Context(Evento.CALCULAR_HABITAT_CON_MAS_INGRESOS_KO, ok.getId());

				else
					return new Context(Evento.CALCULAR_HABITAT_CON_MAS_INGRESOS_OK, ok);
			}
			
			else {
				return new Context(Evento.CALCULAR_HABITAT_CON_MAS_INGRESOS_KO, ok);
			}
			
		}catch (Exception e) {
			
			e.getStackTrace();
		}
		return new Context(Evento.MOSTRAR_HABITAT_KO, ok);
		
	}
	
public LocalDate ParseStringToLocalDate (String date) throws Exception {
		
		date = date.replace('/', '-');

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     
		try {
			LocalDate dateTime = LocalDate.parse(date, format);
			return dateTime;
			} 
		catch (Exception  e) {
			e.printStackTrace();
			throw e;
			}
		
	}
}