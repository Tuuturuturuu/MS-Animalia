/**
 * 
 */
package Negocio.VentaJPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Id;
import javax.persistence.NamedQuery;
import Negocio.TrabajadorJPA.Trabajador;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import Negocio.TrabajadorJPA.TTrabajador;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findByid", query = "select obj from Venta obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findBytrabajador", query = "select obj from Venta obj where :trabajador = obj.trabajador "),
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findByprecio_total", query = "select obj from Venta obj where :precio_total = obj.precio_total "),
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findBymetodo_pago", query = "select obj from Venta obj where :metodo_pago = obj.metodo_pago "),
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findByfecha", query = "select obj from Venta obj where :fecha = obj.fecha "),
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findByversion", query = "select obj from Venta obj where :version = obj.version "),
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findBylineaVenta", query = "select obj from Venta obj where :lineaVenta MEMBER OF obj.lineaVenta "),
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findByactivo", query = "select obj from Venta obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.VentaJPA.Venta.findAll", query = "select obj from Venta obj") }
)
public class Venta implements Serializable {

	private static final long serialVersionUID = 0;

	public Venta() {
		this.lineaVenta = new HashSet<LineaVenta>();

	}
	public Venta(Venta venta) {
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 

	@ManyToOne
	private Trabajador trabajador;
	private double precio_total;
	private String metodo_pago;
	private Date fecha;
	@Version
	private Integer version;
	
	@OneToMany(mappedBy = "venta")
	private Set<LineaVenta> lineaVenta;
	private Boolean activo;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void transferToEntity(TVenta venta) {
		this.setFecha(venta.getFecha());
		this.setActivo(venta.getActivo());
		this.setMetodo_pago(venta.getMetodo_pago());
		this.setPrecio_total(venta.getPrecio_total());
	}

	

	public Set<LineaVenta> getListalineaVentas() {
		// begin-user-code
		// TODO Auto-generated method stub
		return lineaVenta;
		// end-user-code
	}

	public Trabajador getTrabajador() {
		// begin-user-code
		// TODO Auto-generated method stub
		return trabajador;
		// end-user-code
	}
	
	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	

	public double getPrecio_total() {
		return precio_total;
	}

	public void setPrecio_total(double precio_total) {
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
	public TVenta entitityToTransfer(){
		return new TVenta(this);
	}
	
}