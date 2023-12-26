package Integracion.Factura;

public class TLineaFactura {

	private Integer id_factura;

	private Integer id_pase;

	private Integer cantidad;

	private Double precio;

	public TLineaFactura(int idPase, int cantidadPase) {
		// TODO Auto-generated constructor stub
		this.id_pase = idPase;
		this.cantidad = cantidadPase;
	}

	public TLineaFactura() {
		// TODO Auto-generated constructor stub
	}

	public Integer GetIdFactura() {
		return id_factura;
	}

	public void SetIdFactura(Integer id_factura) {
		this.id_factura = id_factura;
	}

	public Integer GetIdPase() {
		return id_pase;
	}

	public void SetIdPase(Integer id_pase) {
		this.id_pase = id_pase;
	}

	public Integer GetCantidad() {
		return cantidad;
	}

	public void SetCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double GetPrecio() {
		return precio;
	}

	public void SetPrecio(Double precio) {
		this.precio = precio;
	}
}