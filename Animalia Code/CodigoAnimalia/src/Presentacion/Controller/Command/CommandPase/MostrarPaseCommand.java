/**
 * 
 */
package Presentacion.Controller.Command.CommandPase;


import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Pase.TPase;
import Presentacion.Evento;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author PC
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class MostrarPaseCommand implements Command {
	public Context Execute(Object datos) {
		TPase res = FactoriaSA.getInstance().getPaseSA().Mostrar((Integer)datos);
		if(res.getID() > -1){ //Si el id es -1, significa que hay un error
			return new Context(Evento.MOSTRAR_PASE_OK,res);
		}else {
			return new Context(Evento.MOSTRAR_PASE_KO,res);
		}
	}
}