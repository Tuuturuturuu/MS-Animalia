package Presentacion.VentaJPA;

import Negocio.VentaJPA.TCarritoJPA;
import Negocio.VentaJPA.TVenta;

public class claseContenedorErroresVenta {
	private int idError;
	private TCarritoJPA Carrito;
	private TVenta tventa;
	
	public claseContenedorErroresVenta(int idError, TCarritoJPA carrito, TVenta venta) {
		super();
		this.idError = idError;
		this.Carrito = carrito;
		this.tventa = venta;
	}
	public TVenta gettVenta() {
		return tventa;
	}
	public void settFactura(TVenta tFactura) {
		this.tventa = tFactura;
	}
	public int getIdError() {
		return idError;
	}
	public void setIdError(int idError) {
		this.idError = idError;
	}
	public TCarritoJPA getCarrito() {
		return Carrito;
	}
	public void setCarrito(TCarritoJPA carrito) {
		Carrito = carrito;
	}
}
