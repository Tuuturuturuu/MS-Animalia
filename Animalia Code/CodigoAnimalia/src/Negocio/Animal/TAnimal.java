/**
 * 
 */
package Negocio.Animal;

public class TAnimal {

	private Integer id;
	private Integer id_Especie;
	private String nombre;
	private Integer tipo;
	private boolean activo;

	public TAnimal() {

	}
	

	public TAnimal(Integer id, String nombre, Integer tipo, Integer idEspecie, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.setTipo(tipo);
		this.activo = activo;
		this.setId_Especie(idEspecie);
	}

	public Integer getId() {

		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getId_Especie() {
		return id_Especie;
	}

	public void setId_Especie(Integer id_Especie) {
		this.id_Especie = id_Especie;
	}
	

	
}