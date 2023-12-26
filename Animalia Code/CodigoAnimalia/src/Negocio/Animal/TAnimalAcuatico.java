/**
 * 
 */
package Negocio.Animal;

public class TAnimalAcuatico extends TAnimal {

	private String tipoAgua;

	private Integer temperatura;
	
	public TAnimalAcuatico(){
		
	}
	public TAnimalAcuatico(TAnimal animal){
		super(animal.getId(), animal.getNombre(), 1, animal.getId_Especie(), animal.isActivo());
	}
	
	public TAnimalAcuatico(Integer id, String nombre,String tipoAgua, Integer temperatura, Integer idEspecie ,boolean activo){
		super(id, nombre, 1, idEspecie, activo);
		this.temperatura = temperatura;
		this.tipoAgua = tipoAgua;
	}

	public String getTipoAgua() {
		return this.tipoAgua;
	}

	public Integer getTemperatura() {
		return this.temperatura;
	}

	public void setTipoAgua(String tipoAgua) {
		this.tipoAgua = tipoAgua;
	}

	public void setTemperatura(Integer temperatura) {
		this.temperatura = temperatura;
	}
}