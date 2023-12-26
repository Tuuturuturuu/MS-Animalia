package Negocio.ProductoJPA;

public class TProducto {
	
	private Integer id;
	private String nombre;
	private Double precio;
	private Integer stock;
	private Boolean activo;
	private Integer idMarcaProducto;
	

	public TProducto(Integer id, String nombre, Double precio, Integer stock,Boolean activo, Integer idMarcaProducto){
		this.id=id;
		this.nombre=nombre;
		this.precio=precio;
		this.stock=stock;
		this.activo=activo;
		this.idMarcaProducto=idMarcaProducto;

	}
	
	public TProducto(Producto producto){
		this.id=producto.getId();
		this.nombre=producto.getNombre();
		this.precio=producto.getPrecio();
		this.stock=producto.getStock();
		this.activo=producto.getActivo();
		this.idMarcaProducto=producto.getMarcaProducto().getId();
		
    }
    
	public TProducto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getID() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	public Integer getStock() {
		return stock;
	}

	public Double getPrecio() {
		return precio;
	}
	public Integer getIdMarcaProducto() {
		return idMarcaProducto;
	}
	public void setID(Integer id) {
		this.id=id;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setStock(Integer stock) {
		this.stock=stock;
	}
	public void setPrecio(Double precio) {
		this.precio=precio;
	}
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo=activo;
	}
	public void setIdMarcaProducto(Integer idMarcaProducto){
		this.idMarcaProducto=idMarcaProducto;
	}

}