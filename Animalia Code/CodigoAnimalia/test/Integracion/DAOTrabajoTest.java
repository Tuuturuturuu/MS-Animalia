package Integracion;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Empleado.DAOEmpleado;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Habitat.DAOHabitat;
import Integracion.Habitat.DAOTrabajo;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Habitat.THabitat;
import Negocio.Habitat.TTrabajo;

public class DAOTrabajoTest {
	
	private static DAOTrabajo daoT;
	private static DAOEmpleado daoE;
	private static DAOHabitat daoH;
	
	@BeforeClass
	public static void beforeClass() {
		
		daoT = FactoriaDAO.getInstance().getDAOTrabajo();
		daoE = FactoriaDAO.getInstance().getDAOEmpleado();
		daoH = FactoriaDAO.getInstance().getDAOHabitat();
	}
	
	@Test
	public void testVincularTrabajo(){
		try{
			Transaction t = crearTransaccion();
			t.start();
			TTrabajo trabajo = crearTTrabajo();
			Integer idTrabajo = daoT.vincularHabitatEmpleado(trabajo);
			if (idTrabajo < 0) {
				t.rollback();
				fail("Error: vincularHabitatEmpleado() debería retornar ID > 0");
			}
			t.commit();
		}catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
		
	}
	
	private Transaction crearTransaccion() {
		try {
			TransactionManager tm = TransactionManager.getInstance();
			tm.newTransaction();
			return tm.getTransaction();
		} catch (Exception e) {
			fail("Error transaccional");
			return null;
		}
	}
	
	private TTrabajo crearTTrabajo(){
		
		TTrabajo trabajo = new TTrabajo();
		
		Integer idEmpleado = daoE.alta(crearTEmpleadoLimpieza());
		Integer idHabitat = daoH.altaHabitat(crearTHabitat());
		
		trabajo.setIdEmpleado(idEmpleado);
		trabajo.setIdHabitat(idHabitat);
		
		return trabajo;
		
	}
	
    private TEmpleado crearTEmpleadoLimpieza() {
        TEmpleadoLimpieza empleado = new TEmpleadoLimpieza();
        empleado.setId(generateRandom());
        empleado.setNombre(generarNombreAleatorio());
        empleado.setDni(generarDniAleatorio());
        empleado.setSueldoBase((generateRandomDouble()));
        empleado.setTelefono(generateRandom());
        empleado.setActivo(true);
        empleado.setTipo(0);
        empleado.setSuplemento((double) generateRandom());
        empleado.setZona(generarNombreAleatorio());
        return empleado;
    }
    
	private THabitat crearTHabitat(){
		
		THabitat habitat = new THabitat();
		
		habitat.setNombre(generarNombreAleatorio());
		habitat.setActivo(true);
		habitat.setTamano(generateRandom());
		habitat.setId(generateRandom());
		
		return habitat;
		
	}
    
    private String generarNombreAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder nombreAleatorio = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(caracteres.length());
            nombreAleatorio.append(caracteres.charAt(index));
        }
        return nombreAleatorio.toString();
    }

    private String generarDniAleatorio() {
        String caracteres = "0123456789";
        StringBuilder dniAleatorio = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            dniAleatorio.append(caracteres.charAt(index));
        }
        return dniAleatorio.toString();
    }
	
	private int generateRandom(){
		return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
	}
	
	private double generateRandomDouble()
	{
		return ThreadLocalRandom.current().nextDouble(0, 10000 + 1);
	}


}
