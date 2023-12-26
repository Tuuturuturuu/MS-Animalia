package Negocio.ProductoJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.ProductoJPA.Alimentacion.findByid", query = "select obj from Alimentacion obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.ProductoJPA.Alimentacion.findBytipo", query = "select obj from Alimentacion obj where :tipo = obj.tipoAl ") })
public class Alimentacion extends Producto implements Serializable {
	
	private static final long serialVersionUID = 0;

	public Alimentacion() {
	}
	
	public Alimentacion(TProducto producto) {
		transferToEntity(producto);
	}

	
	
	private String tipoAl;

	
	

	public String getTipoAl() {
		return tipoAl;
	}

	
	

	public void setTipoAl(String tipo) {
		this.tipoAl=tipo;
	}

	public TAlimentacion entityToTransfer() {
		return new TAlimentacion(super.getId(), super.getNombre(),super.getPrecio(),super.getStock(),tipoAl,super.getActivo(),super.getMarcaProducto().getId());
	}
	
	public void transferToEntity(TProducto producto){
		super.transferToEntity(producto);
		TAlimentacion alimentacionAux=(TAlimentacion) producto;
		this.tipoAl=alimentacionAux.getTipoAl();
	}
//	public Alimentacion(TAlimentacion a) {
//		super(a);
//		this.tipoAl=a.getTipoAl();
//	}
	
}