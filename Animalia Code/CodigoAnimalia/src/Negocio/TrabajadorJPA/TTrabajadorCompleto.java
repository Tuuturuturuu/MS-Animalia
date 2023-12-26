package Negocio.TrabajadorJPA;

public class TTrabajadorCompleto extends TTrabajador {
	
	private Double h_extra;
	private Double euros_h_extra;
	
	public TTrabajadorCompleto(Integer id, String dni, String nombre, Double sueldo, Integer telefono, Integer idDepartamento, Boolean activo, Double h_extra, Double euros_h_extra){
		super(id, dni, nombre, sueldo, telefono, idDepartamento, activo, 0);
		this.h_extra = h_extra;
		this.euros_h_extra=euros_h_extra;
	}

	public Double getH_extra() {
		return h_extra;
	}

	public void setH_extra(Double h_extra) {
		this.h_extra = h_extra;
	}

	public Double getEuros_h_extra() {
		return euros_h_extra;
	}

	public void setEuros_h_extra(Double euros_h_extra) {
		this.euros_h_extra = euros_h_extra;
	}
}