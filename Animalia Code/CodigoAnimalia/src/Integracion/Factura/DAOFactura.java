package Integracion.Factura;

import Negocio.Factura.TFactura;
import java.util.Set;

public interface DAOFactura {

	public Integer crearFactura(TFactura factura);

	public TFactura leerFactura(Integer id);

	public Integer modificarFactura(TFactura factura);

	public Integer realizarDevolucion(TFactura factura);

	public Set<TFactura> leerTodasFactura();
}