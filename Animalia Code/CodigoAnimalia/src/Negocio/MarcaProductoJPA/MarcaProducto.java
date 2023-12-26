package Negocio.MarcaProductoJPA;

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

import java.util.List;

import Negocio.ProductoJPA.Producto;
import javax.persistence.OneToMany;
import Negocio.ProveedorJPA.Proveedor;
import javax.persistence.ManyToMany;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.MarcaProductoJPA.MarcaProducto.findByid", query = "select obj from MarcaProducto obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.MarcaProductoJPA.MarcaProducto.findBynombre", query = "select obj from MarcaProducto obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.MarcaProductoJPA.MarcaProducto.findByproducto", query = "select obj from MarcaProducto obj where :producto MEMBER OF obj.producto "),
		@NamedQuery(name = "Negocio.MarcaProductoJPA.MarcaProducto.findByversion", query = "select obj from MarcaProducto obj where :version = obj.version "),
		@NamedQuery(name = "Negocio.MarcaProductoJPA.MarcaProducto.findByactivo", query = "select obj from MarcaProducto obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.MarcaProductoJPA.MarcaProducto.findByproveedor", query = "select obj from MarcaProducto obj where :proveedor MEMBER OF obj.proveedor "),
	    @NamedQuery(name = "Negocio.MarcaProductoJPA.MarcaProducto.findAll", query = "select obj from MarcaProducto obj order by obj.id asc")})
public class MarcaProducto implements Serializable {
	
	private static final long serialVersionUID = 0;

	
	public MarcaProducto() {
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String nombre;
	
	@OneToMany(mappedBy = "marcaProducto")
	private List<Producto> producto;
	@Version
	private Integer version;
	private Boolean activo;
	@ManyToMany(mappedBy = "marcaProducto")
	private List<Proveedor> proveedor;
	private int contadorProductos;

	public Integer getId() {
		return this.id;
	}

	
	public void setId(Integer id) {
		this.id=id;
	}

	
	public String getNombre() {
		return this.nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	
	public void transferToEntity(TMarcaProducto marcaProducto) {
		this.nombre=marcaProducto.getNombre();
		this.activo= marcaProducto.getActivo();
		
	}
	
    public TMarcaProducto entityToTransfer(){
    	return new TMarcaProducto(this);
    }
    

	public MarcaProducto(TMarcaProducto MarcaProducto) {
		this.contadorProductos = 0;
		transferToEntity(MarcaProducto);
	}
	
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo=activo;
	}
	
	public List<Producto> getProductos(){
		return this.producto;
	}
	public void setProductos(List<Producto> productos){
		this.producto=productos;
	}
	public List<Proveedor> getProveedores(){
		return this.proveedor;
	}
	public void setProveedores(List<Proveedor> proveedores){
		this.proveedor=proveedores;
	}

	public int getContadorProductos() {
		return contadorProductos;
	}

	public void setContadorProductos(int contadorProductos) {
		this.contadorProductos = contadorProductos;
	}
	
	
}