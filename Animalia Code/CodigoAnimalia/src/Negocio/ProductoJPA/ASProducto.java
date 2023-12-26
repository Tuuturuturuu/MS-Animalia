package Negocio.ProductoJPA;

import java.util.List;
import java.util.Set;

public interface ASProducto {

	public Integer altaProducto(TProducto producto);

	public Integer bajaProducto(Integer idProducto);

	public Integer modificarProducto(TProducto producto);

	public TProducto mostrarProducto(Integer idProducto);

	public List<TProducto> listarProductos();

	public List<TProducto> listarProductosPorMarcasDeProducto(Integer idMarcaDeProducto);
}