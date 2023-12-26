package Negocio.Factura;

import java.util.HashSet;
import java.util.Set;
import Integracion.Factura.TLineaFactura;

public class TCarrito {

	private Set<TLineaFactura> tLineasFactura;

	private TFactura tFactura;

	public TCarrito() {
		tLineasFactura = new HashSet<TLineaFactura>();
		tFactura = new TFactura();
	}
	
	public Set<TLineaFactura> getLineasFactura() {
		return tLineasFactura;
	}

	public void setLineasFactura(Set<TLineaFactura> tLineaFactura) {
		this.tLineasFactura = tLineaFactura;
	}

	public TFactura getFactura() {
		return tFactura;
	}

	public void setCompra(TFactura tFactura) {
		this.tFactura = tFactura;
	}
	
}