package Negocio.DepartamentoJPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import Negocio.TrabajadorJPA.Trabajador;

import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
@Entity
@NamedQueries({
    @NamedQuery(name = "Negocio.DepartamentoJPA.Departamento.findByid", query = "select obj from Departamento obj where :id = obj.id "),
    @NamedQuery(name = "Negocio.DepartamentoJPA.Departamento.findBytrabajador", query = "select obj from Departamento obj where :trabajador MEMBER OF obj.trabajador "),
    @NamedQuery(name = "Negocio.DepartamentoJPA.Departamento.findBynombre", query = "select obj from Departamento obj where :nombre = obj.nombre "),
    @NamedQuery(name = "Negocio.DepartamentoJPA.Departamento.findByversion", query = "select obj from Departamento obj where :version = obj.version "),
    @NamedQuery(name = "Negocio.DepartamentoJPA.Departamento.findByactivo", query = "select obj from Departamento obj where :activo = obj.activo "),
    @NamedQuery(name = "Negocio.DepartamentoJPA.Departamento.findAll", query = "select obj from Departamento obj order by obj.id asc")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 0;

    public Departamento() {}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @OneToMany(mappedBy = "departamento")
    private List<Trabajador> trabajador;

    private String nombre;
    
    @Version
    private Integer version;
    
    private Boolean activo;
    
	private int contadorTrabajadores;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<Trabajador> getTrabajadores() {
        return trabajador;
    }

    public void setTrabajadores(List<Trabajador> trabajadores) {
        this.trabajador = trabajadores;
    }

    public void transferToEntity(TDepartamento departamento) {
        this.setNombre(departamento.getNombre());
        this.setActivo(departamento.getActivo());
    }
    
    public TDepartamento entityToTransfer(){
    	return new TDepartamento(this);
    }

    public Departamento(TDepartamento TDepartamento) {
    	this.contadorTrabajadores = 0;
    	transferToEntity(TDepartamento);
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
	public int getNumTrabajadores(){
		return contadorTrabajadores;
		
	}
	public void setNumTrabajadores(int numTrabajadores){
		this.contadorTrabajadores = numTrabajadores;
	}
}
