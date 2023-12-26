package Negocio.MarcaProductoJPA;

public class TMarcaProducto {

	private Integer id;

	private String nombre;

	private Boolean activo;
	
    public TMarcaProducto(MarcaProducto marca_producto){
    	this.id = marca_producto.getId();
    	this.nombre = marca_producto.getNombre();
    	this.activo = marca_producto.getActivo();
    }

	public TMarcaProducto(Integer id, String nombre, Boolean activo){
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}
	
	public TMarcaProducto(){
		
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idMarca) {
		this.id = idMarca;
	}

	public String getNombre() {
		return this.nombre;
		
	}

	public void setNombre(String nombreMarca) {
		this.nombre = nombreMarca;
	}

	public Boolean getActivo() {
		return this.activo;
		
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}