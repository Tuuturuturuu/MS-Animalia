package Integracion.Habitat;

import Negocio.Habitat.THabitat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;


public class DAOHabitatImp implements DAOHabitat {
	
	@Override
	public Integer altaHabitat(THabitat habitat) {
		int exito = -1;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO habitat(nombre,tamano,activo) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, habitat.getNombre());
            statement.setInt(2, habitat.getTamano());
            statement.setBoolean(3, habitat.isActivo());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                exito = result.getInt(1);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exito;
	}

	@Override
	public Integer bajaHabitat(Integer idHabitat) {
		int exito = -1;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "UPDATE habitat SET activo = false WHERE id = ?");

            statement.setInt(1, idHabitat);
            exito = statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(exito != -1)
        	return idHabitat;
        else
        	return exito;
	}

	@Override
	public THabitat mostarHabitat(Integer idHabitat) {
		THabitat habitat = null;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM habitat WHERE id = ? FOR UPDATE");
            statement.setInt(1, idHabitat);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                habitat = new THabitat();
                habitat.setId(result.getInt("id"));
                habitat.setNombre(result.getString("nombre"));
                habitat.setTamano(result.getInt("tamano"));
                habitat.setActivo(result.getBoolean("activo"));
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitat;
	}

	@Override
	public Integer modificarHabitat(THabitat habitat) {

    	int exito = -1;
        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "UPDATE habitat SET nombre = ?, tamano = ?, activo = ? WHERE id = ?");

            statement.setString(1, habitat.getNombre());
            statement.setInt(2, habitat.getTamano());
            statement.setBoolean(3, habitat.isActivo());
            statement.setInt(4, habitat.getId());

            exito = statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(exito != -1)
        	return habitat.getId();
        else
        	return exito;
	}

	@Override
	public Set<THabitat> listarHabitats() {
		Set<THabitat> habitats = new HashSet<>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM habitat FOR UPDATE");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
            	THabitat habitat = new THabitat();
            	habitat.setId(result.getInt("id"));
            	habitat.setNombre(result.getString("nombre"));
            	habitat.setTamano(result.getInt("tamano"));
            	habitat.setActivo(result.getBoolean("activo"));
                habitats.add(habitat);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitats;
	}

	//REVISAR la query
	@Override
	public Set<THabitat> listarHabitatPorEmpleado(Integer idEmpleado) {
		Set<THabitat> habitats = new HashSet<>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM habitat h JOIN trabajo t ON h.id = t.id_habitat WHERE t.id_empleado = ? FOR UPDATE");
            
            statement.setInt(1, idEmpleado);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
            	THabitat habitat = new THabitat();
            	habitat.setId(result.getInt("id"));
            	habitat.setNombre(result.getString("nombre"));
            	habitat.setTamano(result.getInt("tamano"));
            	habitat.setActivo(result.getBoolean("activo"));
            	habitats.add(habitat);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitats;
	}

	@Override
	public THabitat leerPorNombreUnico(String nombreHabitat) {
		THabitat habitat = null;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM habitat WHERE nombre = ? FOR UPDATE");
            statement.setString(1, nombreHabitat);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
            	habitat = new THabitat();
            	habitat.setId(result.getInt("id"));
            	habitat.setNombre(result.getString("nombre"));
            	habitat.setTamano(result.getInt("tamano"));
            	habitat.setActivo(result.getBoolean("activo"));
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitat;
	}
}