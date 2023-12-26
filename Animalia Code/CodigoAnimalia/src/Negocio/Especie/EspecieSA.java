/**
 * 
 */
package Negocio.Especie;

import java.util.Set;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author PC
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface EspecieSA {

	public Integer altaEspecie(TEspecie especie);


	public Integer bajaEspecie(Integer idEspecie);

	public TEspecie mostrarEspecie(Integer idEspecie);

	public Integer modificarEspecie(TEspecie especie);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<TEspecie> listarEspecies();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idHabitat
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<TEspecie> listarEspeciePorHabitat(Integer idHabitat);
}