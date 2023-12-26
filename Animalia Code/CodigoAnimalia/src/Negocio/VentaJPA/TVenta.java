package Negocio.VentaJPA;

import java.util.Date;

public class TVenta {
	
	public TVenta(Venta venta) {
		// TODO Auto-generated constructor stub
		this.id = venta.getId();
		this.activo = venta.getActivo();
		this.idTrabajador = venta.getTrabajador().getId();
		this.fecha = venta.getFecha();
		this.metodo_pago = venta.getMetodo_pago();
		this.precio_total = venta.getPrecio_total();
	}
	public TVenta() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(Double precio_total) {
		this.precio_total = precio_total;
	}
	public String getMetodo_pago() {
		return metodo_pago;
	}
	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Integer getIdTrabajador() {
		return idTrabajador;
	}
	public void setIdTrabajador(Integer idTrabajador) {
		this.idTrabajador = idTrabajador;
	}
	private Integer id;
	private Double precio_total;
	private String metodo_pago;
	private Date fecha;
	private Boolean activo;
	private Integer idTrabajador;
	
	
}