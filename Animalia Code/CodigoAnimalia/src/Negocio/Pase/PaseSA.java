/**
 * 
 */
package Negocio.Pase;

import java.util.Set;


public interface PaseSA {
	
	public Integer Alta(TPase pase);
	public Integer Baja(Integer id);
	public Integer Modificar(TPase pase);
	public TPase Mostrar(Integer id);
	public Set<TPase> Listar();
	public Set<TPase> ListarPasePorHabitat(Integer idHabitat);
}