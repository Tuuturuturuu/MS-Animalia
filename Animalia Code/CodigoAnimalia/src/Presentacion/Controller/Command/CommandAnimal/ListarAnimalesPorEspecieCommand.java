/**
 * 
 */
package Presentacion.Controller.Command.CommandAnimal;

import java.util.Set;

import Negocio.Animal.TAnimal;
import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author PC
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class ListarAnimalesPorEspecieCommand implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#Execute(Object datos)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Context Execute(Object datos) {
		Set<TAnimal> res = FactoriaSA.getInstance().getAnimalSA().listarAnimalesPorEspecie((Integer)datos);
		if(res.size() == 1){
			TAnimal animalUnico = res.iterator().next();
			if (animalUnico.getId() <= 0)
				return new Context(Evento.LISTAR_ANIMALES_POR_ESPECIE_KO, animalUnico);
			else{
				return new Context(Evento.LISTAR_ANIMALES_POR_ESPECIE_OK, res);
			}
		}else{
			return new Context(Evento.LISTAR_ANIMALES_POR_ESPECIE_OK,res);
		}
	}
}