/**
 * 
 */
package Negocio.VentaJPA;

import java.util.List;
import java.util.Set;

import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;


public interface ASVenta {
	
	public Integer cerrarVenta(TCarritoJPA carrito);

	public TVentaConProductos mostrarVenta(Integer idVenta);

	public List<TVenta> listarVentas();

	public List<TVenta> listarVentaPorTrabajador(Integer idTrabajador);

	public Integer devolucionVenta(TLineaVenta tLineaVenta);
	
	public Integer modificarVenta(TVenta venta);
	
	public TCarritoJPA comprobarTrabajador(Integer idTrabajador);
}