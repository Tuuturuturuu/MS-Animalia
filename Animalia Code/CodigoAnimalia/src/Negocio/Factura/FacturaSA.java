package Negocio.Factura;

import java.util.Set;
import Integracion.Factura.TLineaFactura;

public interface FacturaSA {

	public Integer cerrarFactura(TFactura factura, TCarrito carrito);

	public TFacturaConPases mostrarFactura(Integer id);

	public Set<TFactura> listarFacturas();

	public Integer modificarFactura(TFactura factura);

	public Integer devolverFactura(TLineaFactura lineaFactura);
}