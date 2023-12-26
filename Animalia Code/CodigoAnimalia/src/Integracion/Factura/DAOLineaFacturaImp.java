package Integracion.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transactions.TransactionManager;

public class DAOLineaFacturaImp implements DAOLineaFactura {

	public Integer crearLineaFactura(TLineaFactura lineafactura) {
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource();
			PreparedStatement statement = c.prepareStatement("INSERT INTO lineafactura (id_factura, id_pase, cantidad, precio) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, lineafactura.GetIdFactura());
			statement.setInt(2, lineafactura.GetIdPase());
			statement.setInt(3, lineafactura.GetCantidad());
			statement.setDouble(4, lineafactura.GetPrecio());
			int affectedRows = statement.executeUpdate();
			
			statement.close();
			
			if (affectedRows == 0)
				return -1;
						
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Integer eliminarLineaFactura(Integer idFactura, Integer idPase) {
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource();
			PreparedStatement statement = c.prepareStatement("DELETE FROM lineafactura WHERE id_factura = ? AND id_pase = ?", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idFactura);
			statement.setInt(2, idPase);
			int affectedRows = statement.executeUpdate();
			
			statement.close();
			
			if (affectedRows == 0)
				return -1;
						
			return 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Integer modificarLineaFactura(TLineaFactura lineafactura) {
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource();
			PreparedStatement statement = c.prepareStatement("UPDATE lineafactura SET cantidad = ?, precio = ? WHERE id_factura = ? AND id_pase = ?", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, lineafactura.GetCantidad());
			statement.setDouble(2, lineafactura.GetPrecio());
			statement.setInt(3, lineafactura.GetIdFactura());
			statement.setInt(4, lineafactura.GetIdPase());
			int affectedRows = statement.executeUpdate();
			
			statement.close();
			
			if (affectedRows == 0)
				return -1;
						
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public TLineaFactura leerLineaFactura(Integer idFactura, Integer idPase) {
		TLineaFactura lineafactura = null;
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM lineafactura WHERE id_factura = ? AND id_pase = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idFactura);
			statement.setInt(2, idPase);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				lineafactura = new TLineaFactura();
				lineafactura.SetIdFactura(result.getInt(1));
				lineafactura.SetIdPase(result.getInt(2));
				lineafactura.SetCantidad(result.getInt(3));
				lineafactura.SetPrecio(result.getDouble(4));
			}
			result.close();
			statement.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineafactura;
	}

	public Set<TLineaFactura> leerTodasLineaFactura(Integer idFactura) {
		Set<TLineaFactura> lineasfactura = null;

		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM lineafactura WHERE id_factura = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idFactura);
			ResultSet result = statement.executeQuery();
			lineasfactura = new HashSet<TLineaFactura>();
			
			while (result.next()) {
				Integer idF = result.getInt(1);
				Integer idP = result.getInt(2);
				Integer cant = result.getInt(3);
				Double pre = result.getDouble(4);
				TLineaFactura f = new TLineaFactura();
				f.SetIdFactura(idF);
				f.SetIdPase(idP);
				f.SetCantidad(cant);
				f.SetPrecio(pre);
				lineasfactura.add(f);
			}

			result.close();
			statement.close();
			return lineasfactura;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineasfactura;
	}

}