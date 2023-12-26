/**
 * 
 */
package Negocio.Animal;

import java.util.Set;

public interface AnimalSA {
	
	public Integer altaAnimal(TAnimal animal);

	public Integer bajaAnimal(Integer idAnimal);

	public Integer modificarAnimalAcuatico(TAnimalAcuatico animal);
	public Integer modificarAnimalNoAcuatico(TAnimalNoAcuatico animal);

	public TAnimal mostrarAnimal(Integer idAnimal);

	public Set<TAnimal> listarAnimales();

	public Set<TAnimal> listarAnimalesAcuaticos();
	
	public Set<TAnimal> listarAnimalesNoAcuaticos();
	
	public Set<TAnimal> listarAnimalesPorEspecie(Integer idEspecie);
	
}