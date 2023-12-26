package Negocio.Habitat;

public class TTrabajo {
	
	private Integer id_habitat;
	
	private Integer id_empleado;
	
	public TTrabajo() {

	}
	public TTrabajo(Integer id_habitat, Integer id_empleado) {
		
		this.id_habitat = id_habitat;
		this.id_empleado = id_empleado;
	}

	public Integer getIdHabitat() {
		return this.id_habitat;
	}

	public Integer getIdEmpleado() {
		return this.id_empleado;
	}
	
	public void setIdHabitat(Integer idH){
		this.id_habitat = idH;
	}
	
	public void setIdEmpleado(Integer idE){
		this.id_empleado = idE;
	}
	
}