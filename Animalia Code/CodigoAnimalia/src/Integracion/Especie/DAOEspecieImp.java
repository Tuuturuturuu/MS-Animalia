package Integracion.Especie;

import Negocio.Especie.TEspecie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class DAOEspecieImp implements DAOEspecie {

    @Override
    public Integer altaEspecie(TEspecie especie) {
        int exito = -1;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO especie(nombre, id_habitat, activo) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, especie.getNombreEspecie());
            statement.setInt(2, especie.getID_habitat());
            statement.setBoolean(3, especie.getActivo());

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
    public Integer bajaEspecie(Integer idEspecie) {
        int exito = -1;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "UPDATE especie SET activo = false WHERE id = ?");

            statement.setInt(1, idEspecie);
            exito = statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(exito != -1)
        	return idEspecie;
        else
        	return exito;
    }

    @Override
    public TEspecie mostrarEspecie(Integer idEspecie) {
        TEspecie especie = null;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM especie WHERE id = ? FOR UPDATE");
            statement.setInt(1, idEspecie);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                especie = new TEspecie();
                especie.setID(result.getInt("id"));
                especie.setNombreEspecie(result.getString("nombre"));
                especie.setID_habitat(result.getInt("id_habitat"));
                especie.setActivo(result.getBoolean("activo"));
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return especie;
    }

    @Override
    public Integer modificarEspecie(TEspecie especie) {

    	int exito = -1;
        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "UPDATE especie SET nombre = ?, id_habitat = ?, activo = ? WHERE id = ?");

            statement.setString(1, especie.getNombreEspecie());
            statement.setInt(2, especie.getID_habitat());
            statement.setBoolean(3, especie.getActivo());
            statement.setInt(4, especie.getID());

            exito = statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(exito != -1)
        	return especie.getID();
        else
        	return exito;

    }

    @Override
    public Set<TEspecie> listarEspecie() {
        Set<TEspecie> especies = new HashSet<>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM especie FOR UPDATE");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TEspecie especie = new TEspecie();
                especie.setID(result.getInt("id"));
                especie.setNombreEspecie(result.getString("nombre"));
                especie.setID_habitat(result.getInt("id_habitat"));
                especie.setActivo(result.getBoolean("activo"));
                especies.add(especie);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return especies;
    }
    
    @Override
    public Set<TEspecie> listarEspeciePorHabitat(Integer idHabitat) {
        Set<TEspecie> especies = new HashSet<>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM especie WHERE id_habitat = ? FOR UPDATE");
            statement.setInt(1, idHabitat);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TEspecie especie = new TEspecie();
                especie.setID(result.getInt("id"));
                especie.setNombreEspecie(result.getString("nombre"));
                especie.setID_habitat(result.getInt("id_habitat"));
                especie.setActivo(result.getBoolean("activo"));
                especies.add(especie);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return especies;
    }

    @Override
    public TEspecie leerPorNombreUnico(String nombreEspecie) {
        TEspecie especie = null;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM especie WHERE nombre = ? FOR UPDATE");
            statement.setString(1, nombreEspecie);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                especie = new TEspecie();
                especie.setID(result.getInt("id"));
                especie.setNombreEspecie(result.getString("nombre"));
                especie.setID_habitat(result.getInt("id_habitat"));
                especie.setActivo(result.getBoolean("activo"));
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return especie;
    }

    @Override
    public Set<TEspecie> listarEspeciePorHabitatActivos(Integer idHabitat) {
        Set<TEspecie> especies = new HashSet<>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM especie WHERE id_habitat = ? AND activo = true FOR UPDATE");
            statement.setInt(1, idHabitat);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TEspecie especie = new TEspecie();
                especie.setID(result.getInt("id"));
                especie.setNombreEspecie(result.getString("nombre"));
                especie.setID_habitat(result.getInt("id_habitat"));
                especie.setActivo(result.getBoolean("activo"));
                especies.add(especie);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return especies;
    }
}
