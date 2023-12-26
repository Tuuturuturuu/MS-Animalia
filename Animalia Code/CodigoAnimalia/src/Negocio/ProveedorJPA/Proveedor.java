/**
 * 
 */
package Negocio.ProveedorJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import Negocio.MarcaProductoJPA.MarcaProducto;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "CIF") })
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.ProveedorJPA.Proveedor.findByid", query = "select obj from Proveedor obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.ProveedorJPA.Proveedor.findByCIF", query = "select obj from Proveedor obj where :CIF = obj.CIF "),
		@NamedQuery(name = "Negocio.ProveedorJPA.Proveedor.findBynombre", query = "select obj from Proveedor obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.ProveedorJPA.Proveedor.findBytelefono", query = "select obj from Proveedor obj where :telefono = obj.telefono "),
		@NamedQuery(name = "Negocio.ProveedorJPA.Proveedor.findByversion", query = "select obj from Proveedor obj where :version = obj.version "),
		@NamedQuery(name = "Negocio.ProveedorJPA.Proveedor.findBymarcaProducto", query = "select obj from Proveedor obj where :marcaProducto MEMBER OF obj.marcaProducto "),
		@NamedQuery(name = "Negocio.ProveedorJPA.Proveedor.findByactivo", query = "select obj from Proveedor obj where :activo = obj.activo "),
	    @NamedQuery(name = "Negocio.ProveedorJPA.Proveedor.findAll", query = "select obj from Proveedor obj order by obj.id asc")})


public class Proveedor implements Serializable {

	private static final long serialVersionUID = 0;

	public Proveedor() {}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String CIF;
	
	private String nombre;
	
	private Integer telefono;
	
	@Version
	private Integer version;

	@ManyToMany
	private List<MarcaProducto> marcaProducto;
	
	private boolean activo;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCIF() {
		return this.CIF;
	}

	public void setCIF(String cif) {
		this.CIF = cif;
	}

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer tlf) {
		this.telefono = tlf;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

	public void transferToEntity(TProveedor proveedor) {
		this.id = proveedor.getId();
		this.CIF = proveedor.getCIF();
		this.nombre = proveedor.getNombre();
		this.telefono = proveedor.getTelefono();
		this.setActivo(proveedor.isActivo());
	}
	
	public TProveedor entityToTransfer(){
	    	return new TProveedor(this);
	}


	public Proveedor(TProveedor proveedor) {
		transferToEntity(proveedor);
	}

	public List<MarcaProducto> getMarcas() {
		return this.marcaProducto;
	}

	public void setMarcas(List<MarcaProducto> marcas){
		this.marcaProducto = marcas;
	}
	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;		
	}

}