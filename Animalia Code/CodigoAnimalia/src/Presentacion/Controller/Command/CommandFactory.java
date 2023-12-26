package Presentacion.Controller.Command;

public abstract class CommandFactory {

	private static CommandFactory instance;

	public static synchronized CommandFactory getInstance() {
		if(instance == null)
		{
			instance = new CommandFactoryImp();
		}
		return instance;
	}

	public abstract Command GetCommand(Integer event);
}