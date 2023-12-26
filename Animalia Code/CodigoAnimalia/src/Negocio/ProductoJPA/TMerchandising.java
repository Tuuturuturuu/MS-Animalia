/**
 * 
 */
package Negocio.ProductoJPA;


public class TMerchandising extends TProducto {
	
	private String categoria;
	private String edicionLimitada;
	
	public TMerchandising(Integer id, String nombre, Double precio, Integer stock, String categoria, String edicionLimitada, Boolean activo, Integer idMarcaProducto){
		super(id,nombre,precio,stock,activo, idMarcaProducto);
		this.categoria=categoria;
		this.edicionLimitada=edicionLimitada;
	}

	public TMerchandising() {
		// TODO Auto-generated constructor stub
	}

	public String getCategoria() {
		return categoria;
	}

	public String getEdicionLimitada() {
		return edicionLimitada;
	}

	public void setCategoria(String categoria) {
		this.categoria=categoria;
	}
	public void setEdicionLimitada(String edicionLimitada) {
		this.edicionLimitada=edicionLimitada;
	}
}