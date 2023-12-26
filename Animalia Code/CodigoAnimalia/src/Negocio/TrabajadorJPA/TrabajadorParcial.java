package Negocio.TrabajadorJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
        @NamedQuery(name = "Negocio.TrabajadorJPA.TrabajadorParcial.findByid", query = "select obj from TrabajadorParcial obj where :id = obj.id "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.TrabajadorParcial.findByhoras", query = "select obj from TrabajadorParcial obj where :horas = obj.horas "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.TrabajadorParcial.findByeuro_h", query = "select obj from TrabajadorParcial obj where :euro_h = obj.euro_h ") })
public class TrabajadorParcial extends Trabajador implements Serializable {
    private static final long serialVersionUID = 0;

    public TrabajadorParcial() {
    }
    
    public TrabajadorParcial(TTrabajador t) {
    	this.transferToEntity(t);
    }

    private Double horas;
    private Double euro_h;

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public Double getEuro_h() {
        return euro_h;
    }

    public void setEuro_h(Double euro_h) {
        this.euro_h = euro_h;
    }

	@Override
	public TTrabajador entityToTransfer() {
		return new TTrabajadorParcial(super.getId(), super.getDni(), super.getNombre(), super.getSueldo(), super.getTelefono(), super.getDepartamento().getId(), super.getActivo(), horas, euro_h);
	}
	
    public void transferToEntity(TTrabajador trabajador) {
    	
    	super.transferToEntity(trabajador);
    	TTrabajadorParcial tc = (TTrabajadorParcial) trabajador;
    	this.setHoras(tc.getHoras());
    	this.setEuro_h(tc.getEuros_h());
    }

	@Override
	public Double calcularSueldo() {
		return super.getSueldo() + (this.horas * this.euro_h);
	}
}
