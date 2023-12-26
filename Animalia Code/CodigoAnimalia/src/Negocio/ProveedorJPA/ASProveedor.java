
package Negocio.ProveedorJPA;

import java.util.List;
import java.util.Set;


public interface ASProveedor {

	public Integer altaProveedor(TProveedor proveedor);

	public Integer bajaProveedor(Integer idProveedor); 

	public Integer modificarProveedor(TProveedor proveedor);

	public TProveedor mostrarProveedor(Integer idProveedor);

	public List<TProveedor> listarProveedores();

	public List<TProveedor> listarProveedoresPorMarcaDeProducto(Integer idMarca);

	public Integer desvincularProveedorConMarcaDeProducto(TProveedorConMarcas datos);

	public Integer vincularProveedorConMarcaDeProducto(TProveedorConMarcas datos);
}