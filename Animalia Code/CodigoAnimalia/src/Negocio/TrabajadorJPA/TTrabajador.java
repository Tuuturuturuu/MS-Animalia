/**
 * 
 */
package Negocio.TrabajadorJPA;

public abstract class TTrabajador {

	private String dni;
	private String nombre;
	private Integer id;
	private Double sueldo;
	private Integer telefono;
	private Integer idDepartamento;
	private Boolean activo;
	
	private Integer tipo;
	
	public TTrabajador(Integer id, String dni, String nombre, Double sueldo, Integer telefono, Integer idDepartamento, Boolean activo, Integer tipo){
		this.dni = dni;
		this.nombre=nombre;
		this.id=id;
		this.sueldo=sueldo;
		this.telefono=telefono;
		this.idDepartamento=idDepartamento;
		this.activo=activo;
		this.tipo = tipo;
	}
	
	public TTrabajador(Trabajador trabajador){
		this.dni = trabajador.getDni();
		this.nombre=trabajador.getNombre();
		this.id=trabajador.getId();
		this.sueldo=trabajador.getSueldo();
		this.telefono=trabajador.getTelefono();
		this.idDepartamento = trabajador.getDepartamento().getId();
		this.activo=trabajador.getActivo();
		this.tipo = trabajador.getTipo();
	}
			
	
	public Integer getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	
	public Double getSueldo(){
		return sueldo;
	}
	
	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setIdDepartamento(Integer dep) {
		this.idDepartamento = dep;
	}

	
	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
    public Integer getTipo(){
    	return tipo;
    }
    
    public void setTipo(Integer tipo){
    	this.tipo = tipo;
    }
    
}