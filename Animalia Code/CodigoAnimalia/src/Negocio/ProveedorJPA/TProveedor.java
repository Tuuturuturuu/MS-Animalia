package Negocio.ProveedorJPA;

import java.util.List;

import javax.persistence.ManyToMany;

import Negocio.MarcaProductoJPA.MarcaProducto;

public class TProveedor {
	
	private Integer id;
	private String nombre;
	private String CIF;
	private Integer telefono;
	private Boolean activo;
	@ManyToMany
	private List<MarcaProducto> marcaProducto;
	
	public List<MarcaProducto> getMarcaProducto() {
		return marcaProducto;
	}

	public void setMarcaProducto(List<MarcaProducto> marcaProducto) {
		this.marcaProducto = marcaProducto;
	}

	public TProveedor() {

	}
	
	public TProveedor(Integer id,  String CIF, String nombre, Integer telefono, Boolean activo) {
		this.setId(id);
		this.setNombre(nombre);
		this.setTelefono(telefono);
		this.setCIF(CIF);
		this.setActivo(activo);
	}
	
	public TProveedor(Proveedor proveedor){
    	this.id = proveedor.getId();
    	this.nombre = proveedor.getNombre();
    	this.CIF = proveedor.getCIF();
    	this.telefono = proveedor.getTelefono();
    	this.activo = proveedor.getActivo();
    	this.setMarcaProducto(proveedor.getMarcas());
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String cIF) {
		CIF = cIF;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}