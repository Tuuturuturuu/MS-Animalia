package Negocio.Factura;

import java.util.Set;
import Integracion.Factura.TLineaFactura;
import Negocio.Pase.TPase;

public class TFacturaConPases {

	private TFactura tFactura;

	private Set<TLineaFactura> tLineasFactura;

	private Set<TPase> tPases;

	public Set<TPase> getPases() {
		return tPases;
	}

	public void setPases(Set<TPase> pases) {
		this.tPases = pases;
	}

	public TFactura getFactura() {
		return tFactura;
	}

	public void setFactura(TFactura factura) {
		this.tFactura = factura;
	}

	public Set<TLineaFactura> getLineasFactura() {
		return tLineasFactura;
	}

	public void setLineasFactura(Set<TLineaFactura> lineasfactura) {
		this.tLineasFactura = lineasfactura;
	}
}