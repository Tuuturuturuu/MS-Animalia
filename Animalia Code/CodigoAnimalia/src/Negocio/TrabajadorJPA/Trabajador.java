package Negocio.TrabajadorJPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.ManyToOne;
import java.util.Set;

import Negocio.DepartamentoJPA.Departamento;
import Negocio.VentaJPA.Venta;
import javax.persistence.OneToMany;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "nombre"), @UniqueConstraint(columnNames = "dni") })
@Entity
@NamedQueries({
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findByid", query = "select obj from Trabajador obj where :id = obj.id "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findBynombre", query = "select obj from Trabajador obj where :nombre = obj.nombre "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findBysueldo", query = "select obj from Trabajador obj where :sueldo = obj.sueldo "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findBytelefono", query = "select obj from Trabajador obj where :telefono = obj.telefono "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findBydni", query = "select obj from Trabajador obj where :dni = obj.dni "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findBydepartamento", query = "select obj from Trabajador obj where obj.departamento.id = :departamento_id "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findByventa", query = "select obj from Trabajador obj where :venta MEMBER OF obj.venta "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findByversion", query = "select obj from Trabajador obj where :version = obj.version "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findByactivo", query = "select obj from Trabajador obj where :activo = obj.activo "),
        @NamedQuery(name = "Negocio.TrabajadorJPA.Trabajador.findAll", query = "select obj from Trabajador obj order by obj.id asc")})
public abstract class Trabajador implements Serializable {
    private static final long serialVersionUID = 0;

    public Trabajador() {
    }

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String nombre;
    private Double sueldo;
    private Integer telefono;
    private String dni;
	private Integer tipo;
    @ManyToOne
    private Departamento departamento;
    
    @OneToMany(mappedBy = "trabajador")
    private Set<Venta> venta;
    @Version
    private Integer version;
    private Boolean activo;

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

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
	public Integer getTipo(){
		return tipo;
	}
	
	public void setTipo(Integer tipo) {
		this.tipo=tipo;
	}

	public Departamento getDepartamento() { // ?? estaba como Set<Departamento>
        return departamento;
	}
	
	public void setDepartamento(Departamento departamento) { // ?? estaba como Departamento... departamentos
		this.departamento = departamento;
	}

    public Set<Venta> getVenta() {
        return venta;
    }

    public void setVenta(Set<Venta> venta) {
        this.venta = venta;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void transferToEntity(TTrabajador trabajador) {
        this.setDni(trabajador.getDni());
        this.setNombre(trabajador.getNombre());
        this.setSueldo(trabajador.getSueldo());
        this.setTelefono(trabajador.getTelefono());
        this.setActivo(trabajador.getActivo());
        this.setTipo(trabajador.getTipo());
	}
    
    public abstract TTrabajador entityToTransfer();

    public Trabajador(TTrabajador trabajador) {
    	transferToEntity(trabajador);
	}

    public abstract Double calcularSueldo();

}
