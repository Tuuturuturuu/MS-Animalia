package Negocio.TrabajadorJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
        @NamedQuery(name = "Negocio.TrabajadorJPA.TrabajadorCompleto.findByid", query = "select obj from TrabajadorCompleto obj where :id = obj.id "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.TrabajadorCompleto.findByh_extra", query = "select obj from TrabajadorCompleto obj where :h_extra = obj.h_extra "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.TrabajadorCompleto.findByeuro_h_extra", query = "select obj from TrabajadorCompleto obj where :euro_h_extra = obj.euro_h_extra ") })
public class TrabajadorCompleto extends Trabajador implements Serializable {
    private static final long serialVersionUID = 0;

    public TrabajadorCompleto() {
    }
    
    public TrabajadorCompleto(TTrabajador t) {
    	this.transferToEntity(t);
    }

    private Double h_extra;
    private Double euro_h_extra;

    public Double getH_extra() {
        return h_extra;
    }

    public void setH_extra(Double h_extra) {
        this.h_extra = h_extra;
    }

    public Double getEuro_h_extra() {
        return euro_h_extra;
    }

    public void setEuro_h_extra(Double euro_h_extra) {
        this.euro_h_extra = euro_h_extra;
    }

	@Override
	public TTrabajador entityToTransfer() {
		return new TTrabajadorCompleto(super.getId(), super.getDni(), super.getNombre(), super.getSueldo(), super.getTelefono(), super.getDepartamento().getId(), super.getActivo(), h_extra, euro_h_extra);
	}
	
    public void transferToEntity(TTrabajador trabajador) {
    	
    	super.transferToEntity(trabajador);
    	TTrabajadorCompleto tc = (TTrabajadorCompleto) trabajador;
    	this.setH_extra(tc.getH_extra());
    	this.setEuro_h_extra(tc.getEuros_h_extra());
    }

	@Override
	public Double calcularSueldo() {
		return super.getSueldo() + (this.h_extra * this.euro_h_extra);
	}
}
