package Integracion.Factura;

import Negocio.Factura.TFactura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.sql.Date;
import java.util.Set;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class DAOFacturaImp implements DAOFactura {

	public Integer crearFactura(TFactura factura) {
		TransactionManager tManager = TransactionManager.getInstance();
		int exito = -1;
		try {
			Transaction t = tManager.getTransaction();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement(
					"INSERT INTO factura(precio_total ,fecha_compra) VALUES (?,DATE_FORMAT(NOW(), '%Y-%m-%d'))", Statement.RETURN_GENERATED_KEYS);
			statement.setDouble(1, factura.GetPrecioTotal());
			statement.executeUpdate();
			ResultSet result = statement.getGeneratedKeys();
			if (result.next())
				exito = result.getInt(1);
			statement.close();
			result.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return exito;
	}

	public TFactura leerFactura(Integer id) {
		TFactura factura = null;
		try 
		{
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM factura WHERE id = ? FOR UPDATE");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				factura = new TFactura();
				factura.SetId(result.getInt(1));
				factura.SetPrecioTotal(result.getDouble(2));
				factura.SetFechaCompra(new Date(result.getDate(3).getTime()));
				factura.SetActivo(result.getBoolean(4));
			}
			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factura;
	}

	public Integer modificarFactura(TFactura factura) {
		int exito = -1;
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction t = tManager.getTransaction();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c
					.prepareStatement("UPDATE factura SET precio_total = ?, fecha_compra = ?, activo = ? WHERE id = ?");
			
			statement.setDouble(1, factura.GetPrecioTotal());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formatear la fecha
	        String formattedDate = dateFormat.format(factura.GetFechaCompra()); // Obtener la fecha formateada como String
	        statement.setString(2, formattedDate); // Establecer la fecha formateada en el PreparedStatement
			statement.setBoolean(3, factura.GetActivo());
			statement.setInt(4, factura.GetId());
			exito = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(exito != -1)
        	return factura.GetId();
        else
        	return exito;
	}

	public Integer realizarDevolucion(TFactura factura) {
		int exito = -1;
		try 
		{
			Transaction t = TransactionManager.getInstance().getTransaction();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate("UPDATE factura SET precio_total = "+ factura.GetPrecioTotal() +" WHERE id = " + factura.GetId()+";");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return exito;
	}

	public Set<TFactura> leerTodasFactura() {
		Set<TFactura> facturas = new HashSet<TFactura>();
		try
		{
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection c = (Connection) t.getResource();
		PreparedStatement statement = c.prepareStatement("SELECT * FROM factura FOR UPDATE");
		ResultSet result = statement.executeQuery();
		while (result.next())
		{
			TFactura factura = new TFactura();
			factura.SetId(result.getInt(1));
			factura.SetPrecioTotal(result.getDouble(2));
			factura.SetFechaCompra(new Date(result.getDate(3).getTime()));
			factura.SetActivo(result.getBoolean(4));
			facturas.add(factura);
		}
	    statement.close();
		result.close();	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return facturas;
	}
}