/**
 * 
 */
package Negocio.TrabajadorJPA;

public class TTrabajadorParcial extends TTrabajador {

	private Double horas;
	private Double euros_h;
	
	public TTrabajadorParcial(Integer id, String dni, String nombre, Double sueldo, Integer telefono, Integer idDepartamento, Boolean activo, Double horas, Double euros_h){
		super(id, dni, nombre, sueldo, telefono, idDepartamento, activo, 1);
		this.horas = horas;
		this.euros_h=euros_h;
	}
	
	public Double getHoras() {
		return horas;
	}

	public void setHoras(Double horas) {
		this.horas = horas;
	}

	public Double getEuros_h() {
		return euros_h;
	}

	public void setEuros_h(Double euros_h) {
		this.euros_h = euros_h;
	}

}