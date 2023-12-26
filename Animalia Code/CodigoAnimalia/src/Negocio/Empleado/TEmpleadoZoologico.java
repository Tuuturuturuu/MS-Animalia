package Negocio.Empleado;

public class TEmpleadoZoologico extends TEmpleado {
    private String especialidad;
    private Double tasa;
    private Integer experiencia;

    
	public TEmpleadoZoologico() {

	}
    
	public TEmpleadoZoologico(Integer id, String nombre, String dni, Double sueldoBase, Integer telefono, Boolean activo, String especialidad, Double tasa, Integer experiencia) {
		super(id, nombre, dni, sueldoBase, telefono, activo, 1);
		this.especialidad = especialidad;
		this.tasa = tasa;
		this.experiencia = experiencia;
	}
    
    
    public String getEspecialidad() {
        return especialidad;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public Double getTasa() {
        return tasa;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }
}
