package Integracion.Transactions;

public abstract class TransactionManager {

	private static TransactionManager instance;

	public static synchronized TransactionManager getInstance() 
	{
		if(instance == null)
		{
			instance = new TransactionManagerImp();
		}
		return instance;
	}

	public abstract Transaction newTransaction() throws Exception;

	public abstract Transaction getTransaction() throws Exception;

	public abstract void deleteTransaction() throws Exception;
}