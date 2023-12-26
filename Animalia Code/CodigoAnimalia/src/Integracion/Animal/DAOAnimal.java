/**
 * 
 */
package Integracion.Animal;

import java.util.Set;
import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Negocio.Animal.TAnimalNoAcuatico;

public interface DAOAnimal {

	public Integer altaAnimal(TAnimal animal);

	public Set<TAnimal> listarAnimalesPorEspecieActivos(Integer idEspecie);

	public TAnimal mostrarAnimal(Integer idAnimal);

	public Integer modificarAnimalNoAcuatico(TAnimalNoAcuatico animal);

	public Integer modificarAnimalAcuatico(TAnimalAcuatico animal);

	public Integer modificarAnimal(TAnimal animal);

	public Set<TAnimal> listarAnimales();

	public Set<TAnimal> listarAnimalesAcuaticos();

	public Set<TAnimal> listarAnimalesNoAcuaticos();

	public Set<TAnimal> listarAnimalesPorEspecie(Integer idEspecie);

	public Integer bajaAnimal(Integer idAnimal);

	public TAnimal leerPorNombreUnico(String nombreAnimal);
}