package Negocio.DepartamentoJPA;

public class TDepartamento {
    private Integer id;
    private String nombre;
    private Boolean activo;

    public TDepartamento(Integer id, String nombre, Boolean activo) {
        this.setId(id);
        this.setNombre(nombre);
        this.setActivo(activo);
    }
    
    public TDepartamento(Departamento departamento){
    	this.id = departamento.getId();
    	this.nombre = departamento.getNombre();
    	this.activo = departamento.getActivo();
    }
    
    public TDepartamento(){
    	
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
