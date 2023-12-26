package Negocio.Empleado;

public class TEmpleado {
    private Integer id;
    private String nombre;
    private String dni;
    private Double sueldoBase;
    private Integer telefono;
    private Boolean activo;
	private Integer tipo;
    
    
	public TEmpleado() {

	}
    
	public TEmpleado(Integer id, String nombre, String dni, Double sueldoBase, Integer telefono, Boolean activo, Integer tipo) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.sueldoBase = sueldoBase;
		this.telefono = telefono;
		this.activo = activo;
		this.tipo = tipo;
	}
    

    public Boolean getActivo() {
        return activo;
    }

    public String getDni() {
        return dni;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getSueldoBase() {
        return sueldoBase;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSueldoBase(Double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    
    public Integer getTipo(){
    	return tipo;
    }
    
    public void setTipo(Integer tipo){
    	this.tipo = tipo;
    }
}
