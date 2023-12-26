
package Negocio.VentaJPA;

public class TLineaVenta {

	private Integer idVenta;
	private Integer idProducto;
	private Double precioVenta;
	private Integer unidades;
	
	public TLineaVenta() {
		// TODO Auto-generated constructor stub
	}

	public TLineaVenta(Integer id, Integer id2, Integer unidades2, double precioVenta2) {
		this.idVenta = id;
		this.idProducto = id2;
		this.unidades = unidades2;
		this.precioVenta = precioVenta2;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}




}