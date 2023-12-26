package Presentacion.Controller.Command;

public class Context {

	private Integer evento;

	private Object datos;
	
	public Context(Integer evento, Object datos)
	{
		this.evento = evento;
		this.datos = datos;
	}
	//Agregado para poder utilizar el actualizar en las vistas
	public Context(Integer evento) {
		this.evento = evento;
	}

	public Integer getEvento() {
		return evento;
	}

	public Object getDatos() {
		return datos;
	}
}