
package Negocio.Especie;

public class TEspecie {

	private Integer id;
	private Boolean activo;
	private String nombreEspecie;
	private Integer id_habitat;
	
	
	public TEspecie() {

	}
	
	public TEspecie(Integer id, String nombreEspecie, Integer id_habitat, Boolean activo) {
		this.id = id;
		this.nombreEspecie = nombreEspecie;
		this.id_habitat = id_habitat;
		this.activo = activo;
	}

	
	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public String getNombreEspecie() {
		return nombreEspecie;
	}

	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
	}

	public Integer getID_habitat() {
		return id_habitat;
	}

	public void setID_habitat(Integer id_habitat) {
		this.id_habitat = id_habitat;
	}
}