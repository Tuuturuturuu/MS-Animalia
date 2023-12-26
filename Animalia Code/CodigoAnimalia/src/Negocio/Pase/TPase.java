package Negocio.Pase;

import java.sql.Date;
import java.sql.Time;

public class TPase {
	private Integer id;
	private Date fecha;
	private Time hora;
	private Double precio;
	private Integer cantidad_disponible;
	private Integer id_habitat;
	private Boolean activo;
	

	public TPase() {

	}
	
	public TPase(Integer id, Date fecha, Time hora, Double precio, Integer cantidad_disponible, Integer id_habitat, Boolean activo){
		this.id=id;
		this.fecha=fecha;
		this.hora=hora;
		this.precio=precio;
		this.cantidad_disponible=cantidad_disponible;
		this.id_habitat=id_habitat;
		this.activo=activo;
		
	}

	public Integer getID() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public Time getHora() {
		return hora;
	}

	public Double getPrecio() {
		return precio;
	}

	public Integer getCantidadDisponible() {
		return cantidad_disponible;
	}

	public Integer getIDHabitat() {
		return id_habitat;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidad_disponible = cantidadDisponible;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public void setIDHabitat(Integer idHabitat) {
		this.id_habitat = idHabitat;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
