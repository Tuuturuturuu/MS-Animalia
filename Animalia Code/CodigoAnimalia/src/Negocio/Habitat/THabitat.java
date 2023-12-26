package Negocio.Habitat;

public class THabitat {

    private Integer id;
    private Boolean activo;
    private String nombre;
    private Integer tamano;

    public THabitat(Integer id, Boolean activo, String nombre, Integer tamano) {
        this.id = id;
        this.activo = activo;
        this.nombre = nombre;
        this.tamano = tamano;
    }
    
    public THabitat(){
    	
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isActivo() {
        return activo;
    }
  
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
