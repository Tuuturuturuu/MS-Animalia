package Integracion.Factura;

import java.util.Set;

public interface DAOLineaFactura {
	
	public Integer crearLineaFactura(TLineaFactura lineafactura);

	public Integer eliminarLineaFactura(Integer idFactura, Integer idPase);

	public Integer modificarLineaFactura(TLineaFactura lineafactura);

	public TLineaFactura leerLineaFactura(Integer idFactura, Integer idPase);

	public Set<TLineaFactura> leerTodasLineaFactura(Integer idFactura);

}