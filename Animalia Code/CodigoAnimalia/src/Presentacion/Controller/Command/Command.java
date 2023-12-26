package Presentacion.Controller.Command;

public abstract interface Command {

	public abstract Context Execute(Object datos);
}