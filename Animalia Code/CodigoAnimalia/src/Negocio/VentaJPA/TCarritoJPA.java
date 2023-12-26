package Negocio.VentaJPA;

import java.util.HashSet;
import java.util.Set;


public class TCarritoJPA {

	private TVenta tVenta;

	private Set<TLineaVenta> tLineaVenta;
	
	public TCarritoJPA() {
		tLineaVenta = new HashSet<TLineaVenta>();
		tVenta = new TVenta();
	}
	public Set<TLineaVenta> getLineasVenta() {
		return tLineaVenta;
	}
	public void setLineasVentas(Set<TLineaVenta> tLineaVenta) {
		this.tLineaVenta = tLineaVenta;
	}

	public TVenta getVenta() {
		return tVenta;
	}

	public void setCompra(TVenta tVenta) {
		this.tVenta = tVenta;
	}
	
}