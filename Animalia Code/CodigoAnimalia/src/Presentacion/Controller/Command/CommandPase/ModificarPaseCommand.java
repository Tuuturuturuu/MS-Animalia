/**
 * 
 */
package Presentacion.Controller.Command.CommandPase;

import Negocio.Pase.TPase;
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
public class ModificarPaseCommand implements Command {
	public Context Execute(Object datos) {
		int res = FactoriaSA.getInstance().getPaseSA().Modificar((TPase)datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_PASE_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_PASE_KO,res);
		}
	}
}