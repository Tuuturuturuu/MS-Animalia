package Negocio.ProductoJPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import Negocio.MarcaProductoJPA.MarcaProducto;
import javax.persistence.ManyToOne;
import java.util.Set;
import Negocio.VentaJPA.LineaVenta;
import javax.persistence.OneToMany;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;


@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findByid", query = "select obj from Producto obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findBynombre", query = "select obj from Producto obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findBystock", query = "select obj from Producto obj where :stock = obj.stock "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findByprecio", query = "select obj from Producto obj where :precio = obj.precio "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findByversion", query = "select obj from Producto obj where :version = obj.version "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findBymarcaProducto", query = "select obj from Producto obj where :marcaProducto = obj.marcaProducto "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findByactivo", query = "select obj from Producto obj where :activo = obj.activo ") })
public class Producto implements Serializable {
	private static final long serialVersionUID = 0;

	public Producto() {
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String nombre;

	private Integer stock;
	
	private Double precio;
	
	
	@Version
	private Integer version;
	
	@ManyToOne
	private MarcaProducto marcaProducto;
	
	@OneToMany(mappedBy = "producto")
	private Set<LineaVenta> lineaVenta;

	private Boolean activo;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setId(Integer id) {
		this.id=id;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public void setStock(Integer stock) {
		this.stock=stock;
	}

	
	public void setPrecio(Double precio) {
		this.precio=precio;
	}
	
	
	public void transferToEntity(TProducto producto) {
		this.setNombre(producto.getNombre());
		this.setPrecio(producto.getPrecio());
		this.setActivo(producto.getActivo());
		this.setStock(producto.getStock());
		
	}

	  
	public Producto(TProducto producto) {
		transferToEntity(producto);
	}

	
	public Boolean getActivo() {
		return activo;
	}
	
	public void setActivo(Boolean activo) {
		this.activo=activo;
	}
	public MarcaProducto getMarcaProducto() {
		return marcaProducto;
	}
	
	public void setMarcaProducto(MarcaProducto marcaProducto) {
		this.marcaProducto=marcaProducto;
	}
}