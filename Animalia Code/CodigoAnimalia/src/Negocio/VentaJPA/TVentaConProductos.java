/**
 * 
 */
package Negocio.VentaJPA;

import java.util.Set;

import Negocio.Factura.TFactura;
import Negocio.ProductoJPA.TProducto;

public class TVentaConProductos {

	private TVenta tVenta;

	private Set<TLineaVenta> tLineaVenta;

	private Set<TProducto> tProducto;

	public TVenta gettVenta() {
		return tVenta;
	}

	public void settVenta(TVenta tVenta) {
		this.tVenta = tVenta;
	}

	public Set<TLineaVenta> gettLineaVenta() {
		return tLineaVenta;
	}

	public void settLineaVenta(Set<TLineaVenta> tLineaVenta) {
		this.tLineaVenta = tLineaVenta;
	}

	public Set<TProducto> gettProducto() {
		return tProducto;
	}

	public void settProducto(Set<TProducto> tProducto) {
		this.tProducto = tProducto;
	}

}