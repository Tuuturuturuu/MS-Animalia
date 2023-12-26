package Integracion.Habitat;

import Negocio.Habitat.TTrabajo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class DAOTrabajoImp implements DAOTrabajo {
	
	@Override
	public Integer altaTrabajo(TTrabajo trabajo) {
		
	    int exito = -1;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO trabajo(id_habitat, id_empleado) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, trabajo.getIdHabitat());
            statement.setInt(2, trabajo.getIdEmpleado());
           

            exito = statement.executeUpdate();
            if (exito == 0){
            	exito = -1;
            }
            else {
            	exito = trabajo.getIdEmpleado();
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exito;
		
	}

	//REVISAR
	@Override
	public Integer bajaTrabajo(Integer idEmpleado, Integer idHabitat) {
	    int exito = -1;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM trabajo WHERE id_habitat = ? AND id_empleado = ?");
            
            statement.setInt(1, idHabitat);
            statement.setInt(2, idEmpleado);
            
            exito = statement.executeUpdate();
            if (exito == 0){
            	exito = -1;
            }
            else {
            	exito = idEmpleado;
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(exito != -1)
        	return idEmpleado;
        else
        	return exito;
	
	}

	
	@Override
	public Set<TTrabajo> listarTrabajo() {
		
		Set<TTrabajo> trabajos = new HashSet<>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM trabajo FOR UPDATE");
           
            ResultSet result = statement.executeQuery();

            
            while (result.next()) {
            	TTrabajo trabajo = new TTrabajo();
               
            	trabajo.setIdEmpleado(result.getInt("id_empleado"));
                trabajo.setIdHabitat(result.getInt("id_habitat"));
                
                trabajos.add(trabajo);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trabajos;
	}
	
//REVISAR
	@Override
	public Integer vincularHabitatEmpleado(TTrabajo trabajo) {
        int exito = altaTrabajo(trabajo);
        return exito;
	}
	
	
	//REVISAR
	@Override
	public Integer desvincularHabitatEmpleado(TTrabajo trabajo) {
	        int	exito = bajaTrabajo(trabajo.getIdEmpleado(), trabajo.getIdHabitat());
	        return exito;
	
	}
	
	@Override
	public Set<TTrabajo> mostrarTrabajoPorEmpleado(Integer idEmpleado) {
		
		Set<TTrabajo> trabajos = new HashSet<>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM trabajo WHERE id_empleado = ? FOR UPDATE");
            
            statement.setInt(1, idEmpleado);
            
            ResultSet result = statement.executeQuery();

            while (result.next()) {
            	TTrabajo trabajo = new TTrabajo();
            	
               
            	trabajo.setIdEmpleado(result.getInt("id_empleado"));
                trabajo.setIdHabitat(result.getInt("id_habitat"));
                
                trabajos.add(trabajo);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trabajos;
	
	}
	@Override
	public Set<TTrabajo> mostrarTrabajoPorHabitat(Integer idHabitat) {
		Set<TTrabajo> trabajos = new HashSet<>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM trabajo WHERE id_habitat = ? FOR UPDATE");
            
            statement.setInt(1, idHabitat);
            
            ResultSet result = statement.executeQuery();

           
            while (result.next()) {
            	 TTrabajo trabajo = new TTrabajo();
            	
               
            	trabajo.setIdEmpleado(result.getInt("id_empleado"));
                trabajo.setIdHabitat(result.getInt("id_habitat"));
                
                trabajos.add(trabajo);
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trabajos;
		
	}

	@Override
	public TTrabajo mostrarTrabajo(TTrabajo trabajo) {
		
		TTrabajo trab = null;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM trabajo WHERE id_empleado = ? AND id_habitat = ? FOR UPDATE");
            
            statement.setInt(1, trabajo.getIdEmpleado());
            statement.setInt(2, trabajo.getIdHabitat());
            
            ResultSet result = statement.executeQuery();
            if(result.next()){
            	trab = new TTrabajo();
            	trab.setIdEmpleado(result.getInt("id_empleado"));
                trab.setIdHabitat(result.getInt("id_habitat"));
            }
         
            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trab;
		
	}
}