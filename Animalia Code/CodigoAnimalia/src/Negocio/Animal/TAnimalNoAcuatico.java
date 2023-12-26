/**
 * 
 */
package Negocio.Animal;

public class TAnimalNoAcuatico extends TAnimal {
	
	private Integer numPatas;
	
	public TAnimalNoAcuatico(){
		
	}
	public TAnimalNoAcuatico(TAnimal animal){
		super(animal.getId(), animal.getNombre(), 1, animal.getId_Especie(), animal.isActivo());
	}
	public TAnimalNoAcuatico(Integer id, String nombre, Integer numPatas, Integer idEspecie, boolean activo){
		super(id, nombre, 0, idEspecie, activo);
		this.numPatas = numPatas;
	}

	public Integer getNumPatas() {
		return this.numPatas;
	}

	public void setNumPatas(Integer numPatas) {
		this.numPatas = numPatas;
	}
}