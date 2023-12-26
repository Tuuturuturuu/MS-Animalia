package Negocio.ProveedorJPA;

public class TProveedorConMarcas {
	private Integer id_proveedor;
	
	private Integer id_marca;
	
	public TProveedorConMarcas() {

	}
	public TProveedorConMarcas(Integer id_proveedor, Integer id_marca) {
		
		this.id_proveedor = id_proveedor;
		this.id_marca = id_marca;
	}
	public Integer getIdProveedor() {
		return id_proveedor;
	}
	public void setIdProveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	public Integer getIMarca() {
		return id_marca;
	}
	public void setIdMarca(Integer id_marca) {
		this.id_marca = id_marca;
	}
	
}
