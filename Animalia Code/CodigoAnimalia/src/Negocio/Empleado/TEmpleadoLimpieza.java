package Negocio.Empleado;

public class TEmpleadoLimpieza extends TEmpleado {
    private Double suplemento;
    private String zona;
    
    
	public TEmpleadoLimpieza() {

	}
    
	public TEmpleadoLimpieza(Integer id, String nombre, String dni, Double sueldoBase, Integer telefono, Boolean activo, Double suplemento, String zona) {
		super(id, nombre, dni, sueldoBase, telefono, activo, 0);
		this.suplemento = suplemento;
		this.zona = zona;
	}
	

    public Double getSuplemento() {
        return suplemento;
    }

    public String getZona() {
        return zona;
    }

    public void setSuplemento(Double suplemento) {
        this.suplemento = suplemento;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
