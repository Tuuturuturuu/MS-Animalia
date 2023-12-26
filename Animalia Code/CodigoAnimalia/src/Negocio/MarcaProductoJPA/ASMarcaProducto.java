/**
 * 
 */
package Negocio.MarcaProductoJPA;

import java.util.List;


public interface ASMarcaProducto {
	
	public Integer altaMarcaProducto(TMarcaProducto marca);	
	public Integer bajaMarcaProducto(Integer id);
	public Integer modificarMarcaProducto(TMarcaProducto marca);
	public TMarcaProducto mostrarMarcaProducto(Integer id);
	public List<TMarcaProducto> listarMarcaProducto();
	public List<TMarcaProducto> listarMarcaPorProveedor(Integer idProveedor);
}