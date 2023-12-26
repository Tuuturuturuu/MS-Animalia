package Integracion.Transactions;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {

	private ConcurrentHashMap<Thread, Transaction> transacciones;

	public TransactionManagerImp(){
		transacciones = new ConcurrentHashMap<Thread, Transaction>();
	}
	
	@Override
	public Transaction newTransaction() throws Exception {
		if(transacciones.get(Thread.currentThread()) == null){
			Transaction t = TransactionFactory.getInstance().createTransaction();
			transacciones.put(Thread.currentThread(), t);
			return t;
		}
		throw new Exception();
	}

	@Override
	public Transaction getTransaction() throws Exception {
		if(transacciones.get(Thread.currentThread()) != null){
			return transacciones.get(Thread.currentThread());
		}
		throw new Exception();
	}

	@Override
	public void deleteTransaction() throws Exception {
		if(transacciones.get(Thread.currentThread()) != null){
			transacciones.remove(Thread.currentThread());
		}
		else throw new Exception();
	}
}