package Integracion.Transactions;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


public class TransactionMySQL implements Transaction {

	private static final String DB_NAME_PROP = "dbname";
	private static final String DB_HOST_PROP = "host";
	private static final String DB_PASSWORD_PROP = "password";
	private static final String DB_PORT_PROP = "port";
	private static final String DB_USER_PROP = "user";

	private Connection connection;
	private String db_properties;

	public TransactionMySQL() throws Exception {
		try {
			db_properties = "config/dbconfig.properties";
			Properties prop = new Properties();
			prop.load(new FileInputStream(db_properties));
			String host = prop.getProperty(DB_HOST_PROP);
			String port = prop.getProperty(DB_PORT_PROP);
			String db = prop.getProperty(DB_NAME_PROP);
			String user = prop.getProperty(DB_USER_PROP);
			String password = prop.getProperty(DB_PASSWORD_PROP);

			String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + password + "&useSSL=false" + "&serverTimezone=UTC";
			connection = DriverManager.getConnection(connectionString);

		}
		catch (SQLException e) {
			connection = null;
			System.out.println("Error al establecer la conexión");
			System.out.println(e.getMessage());
		}
	}

	public void start() throws Exception {
		connection.setAutoCommit(false);
	}

	public void commit() throws Exception {
		connection.commit();
		connection.close();
		TransactionManager t = TransactionManager.getInstance();
		t.deleteTransaction();
	}

	public void rollback() throws Exception {
		connection.rollback();
		connection.close();
		TransactionManager t = TransactionManager.getInstance();
		t.deleteTransaction();
	}

	public Object getResource() {
		return connection;
	}

	public void borrarDatosTabla(String nombreTabla) {

		try {
			PreparedStatement ps;
			ps = connection.prepareStatement("DELETE FROM " + nombreTabla);
			ps.execute();

			ps = connection.prepareStatement("ALTER TABLE " + nombreTabla + " AUTO_INCREMENT = 1");
			ps.execute();

			ps.close();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
		public void cerrarConnection() throws Exception {
		connection.close();

	}
}