package Integracion.Pase;

import Negocio.Pase.TPase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class DAOPaseImp implements DAOPase {

    public Integer altaPase(TPase pase) {
        TransactionManager tManager = TransactionManager.getInstance();
        int exito = -1;

        try {
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO pase(fecha, hora, precio, cantidad_disponible, id_habitat, activo) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formatear la fecha
            String formattedDate = dateFormat.format(pase.getFecha()); // Obtener la fecha formateada como String

            statement.setString(1, formattedDate); // Establecer la fecha formateada en el PreparedStatement
            LocalTime hora = pase.getHora().toLocalTime(); // Convertir la hora a LocalTime
            statement.setObject(2, hora); // Utilizar setObject para manejar LocalTime
            statement.setDouble(3, pase.getPrecio());
            statement.setInt(4, pase.getCantidadDisponible());
            statement.setInt(5, pase.getIDHabitat());
            statement.setBoolean(6, true);

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next())
                exito = result.getInt(1);

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exito;
    }

    public TPase mostrarPase(Integer id) {
        TPase pase = null;

        try {
            TransactionManager tm = TransactionManager.getInstance();
            Transaction t = tm.getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement("SELECT * FROM pase WHERE id = ? FOR UPDATE");
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                pase = new TPase();
                pase.setID(result.getInt("id"));
                pase.setFecha(result.getDate("fecha"));
                pase.setHora(result.getTime("hora"));
                pase.setPrecio(result.getDouble("precio"));
                pase.setCantidadDisponible(result.getInt("cantidad_disponible"));
                pase.setIDHabitat(result.getInt("id_habitat"));
                pase.setActivo(result.getBoolean("activo"));
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pase;
    }

    public Integer modificarPase(TPase pase) {
    	int exito = -1;
    	try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement(
                    "UPDATE pase SET fecha = ?, hora = ?, precio = ?, cantidad_disponible = ?, id_habitat = ?, activo = ? WHERE id = ?");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formatear la fecha
            String formattedDate = dateFormat.format(pase.getFecha()); // Obtener la fecha formateada como String
            statement.setString(1, formattedDate); // Establecer la fecha formateada en el PreparedStatement
            LocalTime hora = pase.getHora().toLocalTime(); // Convertir la hora a LocalTime
            statement.setObject(2, hora); // Utilizar setObject para manejar LocalTime
            statement.setDouble(3, pase.getPrecio());
            statement.setInt(4, pase.getCantidadDisponible());
            statement.setInt(5, pase.getIDHabitat());
            statement.setBoolean(6, pase.getActivo());
            statement.setInt(7, pase.getID());

            exito = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    	if(exito != -1)
        	return pase.getID();
        else
        	return exito;
    }

    public Integer bajaPase(Integer id) {
    	int exito = -1;
        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement("UPDATE pase SET activo = false WHERE id = ?");
            statement.setInt(1, id);

            exito = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(exito != -1)
        	return id;
        else
        	return exito;

    }

    public Set<TPase> listarPases() {
        Set<TPase> pases = new HashSet<TPase>();

        try {
            Transaction t = TransactionManager.getInstance().getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement("SELECT * FROM pase FOR UPDATE");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TPase pase = new TPase();
                pase.setID(result.getInt("id"));
                pase.setFecha(result.getDate("fecha"));
                pase.setHora(result.getTime("hora"));
                pase.setPrecio(result.getDouble("precio"));
                pase.setCantidadDisponible(result.getInt("cantidad_disponible"));
                pase.setIDHabitat(result.getInt("id_habitat"));
                pase.setActivo(result.getBoolean("activo"));
                pases.add(pase);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pases;
    }

    public Set<TPase> listarPasesPorHabitat(Integer idHabitat) {
        Set<TPase> pases = new HashSet<TPase>();

        try {
            Transaction t = TransactionManager.getInstance().getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement("SELECT * FROM pase WHERE id_habitat = ? FOR UPDATE");
            statement.setInt(1, idHabitat);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TPase pase = new TPase();
                pase.setID(result.getInt("id"));
                pase.setFecha(result.getDate("fecha"));
                pase.setHora(result.getTime("hora"));
                pase.setPrecio(result.getDouble("precio"));
                pase.setCantidadDisponible(result.getInt("cantidad_disponible"));
                pase.setIDHabitat(result.getInt("id_habitat"));
                pase.setActivo(result.getBoolean("activo"));
                pases.add(pase);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pases;
    }

    public TPase leerPorCampoUnico(Date fecha, Time hora, Integer id_habitat) {
        TPase pase = null;

        try {
            Transaction t = TransactionManager.getInstance().getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM pase WHERE fecha = ? AND hora = ? AND id_habitat = ? FOR UPDATE");
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formatear la fecha
            String formattedDate = dateFormat.format(fecha); // Obtener la fecha formateada como String
            
            statement.setString(1, formattedDate);
            statement.setObject(2, hora.toLocalTime());
            statement.setInt(3, id_habitat);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                pase = new TPase();
                pase.setID(result.getInt("id"));
                pase.setFecha(result.getDate("fecha"));
                pase.setHora(result.getTime("hora"));
                pase.setPrecio(result.getDouble("precio"));
                pase.setCantidadDisponible(result.getInt("cantidad_disponible"));
                pase.setIDHabitat(result.getInt("id_habitat"));
                pase.setActivo(result.getBoolean("activo"));
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pase;
    }

    public Set<TPase> listarPasesPorHabitatActivos(Integer idHabitat) {
        Set<TPase> pases = new HashSet<TPase>();

        try {
            Transaction t = TransactionManager.getInstance().getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c
                    .prepareStatement("SELECT * FROM pase WHERE id_habitat = ? AND activo = true FOR UPDATE");
            statement.setInt(1, idHabitat);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TPase pase = new TPase();
                pase.setID(result.getInt("id"));
                pase.setFecha(result.getDate("fecha"));
                pase.setHora(result.getTime("hora"));
                pase.setPrecio(result.getDouble("precio"));
                pase.setCantidadDisponible(result.getInt("cantidad_disponible"));
                pase.setIDHabitat(result.getInt("id_habitat"));
                pase.setActivo(result.getBoolean("activo"));
                pases.add(pase);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pases;
    }
}
