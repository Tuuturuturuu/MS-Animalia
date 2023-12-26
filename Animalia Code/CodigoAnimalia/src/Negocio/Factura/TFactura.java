package Negocio.Factura;

import java.util.Date;

public class TFactura {

	private Integer id;

	private Double precio_total;

	private Date fecha_compra;

	private Boolean activo;

	public Integer GetId() {
		return id;
	}

	public void SetId(Integer id) {
		this.id = id;
	}

	public Double GetPrecioTotal() {
		return precio_total;
	}

	public void SetPrecioTotal(Double precio_total) {
		this.precio_total = precio_total;
	}

	public Date GetFechaCompra() {
		return fecha_compra;
	}

	public void SetFechaCompra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public Boolean GetActivo() {
		return activo;
	}

	public void SetActivo(Boolean activo) {
		this.activo = activo;
	}
}