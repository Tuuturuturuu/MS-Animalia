package Negocio.VentaJPA;

import java.io.Serializable;
import javax.persistence.Embeddable;


@Embeddable
public class LineaVentaID implements Serializable {
	
	private static final long serialVersionUID = 0;

	public LineaVentaID() {
	}

	public LineaVentaID(Integer idVenta, Integer idProducto) {
		// TODO Auto-generated constructor stub
		this.id_Venta = idVenta;
		this.id_producto = idProducto;
	}

	private Integer id_Venta;

	private Integer id_producto;

	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof LineaVentaID)) return false;
	    
	    LineaVentaID other = (LineaVentaID) obj;

	    // Comparación teniendo en cuenta null
	    if (id_Venta == null && other.id_Venta != null) return false;
	    if (id_producto == null && other.id_producto != null) return false;

	    // Comparación de campos
	    return id_Venta.equals(other.id_Venta) && id_producto.equals(other.id_producto);
	}


	@Override
	public int hashCode() {
	    final int prime = 31;
	    int hash = 17;

	    hash = hash * prime + ((id_Venta == null) ? 0 : id_Venta.hashCode());
	    hash = hash * prime + ((id_producto == null) ? 0 : id_producto.hashCode());

	    return hash;
	}
}