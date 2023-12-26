package Negocio.ProductoJPA;


public class TAlimentacion extends TProducto {
	
	private String tipoAl;

	
	public TAlimentacion(Integer id, String nombre, Double precio, Integer stock, String type,Boolean activo, Integer idMarcaProducto){
		super(id,nombre,precio,stock,activo, idMarcaProducto);
		this.tipoAl=type;
		
	}
	public TAlimentacion() {
		// TODO Auto-generated constructor stub
	}
	public String getTipoAl() {
		return tipoAl;
	}

	
	public void setTipoAl(String tipo) {
		this.tipoAl = tipo;
	}
}