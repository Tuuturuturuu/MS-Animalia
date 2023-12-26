package Negocio.ProductoJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;


import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.ProductoJPA.Merchadising.findByid", query = "select obj from Merchadising obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.ProductoJPA.Merchadising.findBycategoria", query = "select obj from Merchadising obj where :categoria = obj.categoria "),
		@NamedQuery(name = "Negocio.ProductoJPA.Merchadising.findByedicionLimitada", query = "select obj from Merchadising obj where :edicionLimitada = obj.edicionLimitada ") })
public class Merchadising extends Producto implements Serializable {

	private static final long serialVersionUID = 0;

	public Merchadising() {
	}
	
	public Merchadising(TProducto producto) {
		transferToEntity(producto);
	}

	
	
	private String categoria;
	
	private String edicionLimitada;


	public String getCategoria() {
		return categoria;
	}

	public String getEdicionLimitada() {
		return edicionLimitada;
	}



	public void setCategoria(String categoria) {
		this.categoria=categoria;
		}


	public void setEdicionLimitada(String edicionLimitada) {
		this.edicionLimitada=edicionLimitada;
	}


	public TMerchandising entityToTransfer() {
		return new TMerchandising(super.getId(),super.getNombre(),super.getPrecio(),super.getStock(),categoria,edicionLimitada,super.getActivo(),super.getMarcaProducto().getId());
	
	}
	
	//FORMA 1 TRANSFER TO ENTITY
	public void transferToEntity(TProducto producto){
		super.transferToEntity(producto);
		TMerchandising merchadisingAux=(TMerchandising) producto;
		this.categoria=merchadisingAux.getCategoria();
		this.edicionLimitada=merchadisingAux.getEdicionLimitada();
	}
	//FORMA 2 TRANSFER TO ENTITY
	public Merchadising(TMerchandising m) {
		super(m);
		this.categoria=m.getCategoria();
		this.edicionLimitada=m.getEdicionLimitada();
	}
}