
package Negocio.VentaJPA;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import Negocio.ProductoJPA.Producto;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByprecioVenta", query = "select obj from LineaVenta obj where :precioVenta = obj.precioVenta "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByunidades", query = "select obj from LineaVenta obj where :unidades = obj.unidades "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByventa", query = "select obj from LineaVenta obj where :venta = obj.venta "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByproducto", query = "select obj from LineaVenta obj where :producto = obj.producto "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByid", query = "select obj from LineaVenta obj where :id = obj.id ") })
public class LineaVenta implements Serializable {

	private static final long serialVersionUID = 0;

	@EmbeddedId
	private LineaVentaID id;

	public void setId(LineaVentaID id) {
		this.id = id;
	}
	
	public LineaVentaID getId() {
		return id;
	}

	private double precioVenta;

	private Integer unidades;
	
	@ManyToOne
	@MapsId("id_Venta") private Venta venta;

	@ManyToOne
	@MapsId("id_producto") private Producto producto;

	public void transferToEntity(TLineaVenta lineaVenta) {
		this.setPrecioVenta(lineaVenta.getPrecioVenta());
		this.setUnidades(lineaVenta.getUnidades());
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
}