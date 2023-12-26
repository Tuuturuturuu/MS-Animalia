package Presentacion.Factura;

import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;

public class claseContenedorErroresFactura {
	private int idError;
	private TCarrito Carrito;
	private TFactura tFactura;
	
	public claseContenedorErroresFactura(int idError, TCarrito carrito, TFactura factura) {
		super();
		this.idError = idError;
		this.Carrito = carrito;
		this.tFactura = factura;
	}
	public TFactura gettFactura() {
		return tFactura;
	}
	public void settFactura(TFactura tFactura) {
		this.tFactura = tFactura;
	}
	public int getIdError() {
		return idError;
	}
	public void setIdError(int idError) {
		this.idError = idError;
	}
	public TCarrito getCarrito() {
		return Carrito;
	}
	public void setCarrito(TCarrito carrito) {
		Carrito = carrito;
	}
	
}
