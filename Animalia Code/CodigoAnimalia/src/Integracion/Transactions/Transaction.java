package Integracion.Transactions;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Transaction {

	public void start() throws Exception;

	public void commit() throws Exception;

	public void rollback() throws Exception;

	public Object getResource() throws Exception;

	public void borrarDatosTabla(String nombreTabla) throws Exception;

	public void cerrarConnection() throws Exception;

}