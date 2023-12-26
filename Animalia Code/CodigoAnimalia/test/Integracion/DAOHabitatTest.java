package Integracion;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Set;
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

public class DAOHabitatTest {
	
	private static DAOHabitat daoH;
	private static DAOEmpleado daoEmpleado;
	
	private boolean equals(THabitat a, THabitat b) {
		return a.getId().equals(b.getId()) && a.getNombre().equals(b.getNombre());
	}

	@BeforeClass
	public static void beforeClass() {
		
		daoH = FactoriaDAO.getInstance().getDAOHabitat();
		daoEmpleado = FactoriaDAO.getInstance().getDAOEmpleado();
	}
	
	@Test
	public void testAltaHabitat() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			THabitat habitat = crearTHabitat();
			Integer idHabitat = daoH.altaHabitat(habitat);
			if (idHabitat < 0) {
				t.rollback();
				fail("Error: altaHabitat() debería retornar ID > 0");
			}
			t.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBajaHabitat() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			THabitat habitat = crearTHabitat();
			Integer idHabitat = daoH.altaHabitat(habitat);
			Integer result = daoH.bajaHabitat(idHabitat);
			if (result < 0) {
				t.rollback();
				fail("Error: bajaHabitat() debería retornar un número positivo");
			}
			t.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMostrarHabitat() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			THabitat habitat = crearTHabitat();
			Integer idHabitat = daoH.altaHabitat(habitat);
			habitat.setId(idHabitat);

			if (!equals(habitat, daoH.mostarHabitat(idHabitat))) {
				t.rollback();
				fail("Error: mostrarHabitat() debería devolver un habitat idéntico");
			}
			t.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificarHabitat() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        THabitat habitat = crearTHabitat();
	        Integer idHabitat = daoH.altaHabitat(habitat);
	        habitat.setId(idHabitat);
	        
	        habitat.setNombre(generarNombreAleatorio());
	        habitat.setActivo(false);

	        Integer resultado = daoH.modificarHabitat(habitat);

	        if (resultado < 0) {
	            t.rollback();
	            fail("Error: modificarHabitat() debería retornar un numero positivo");
	        }

	        THabitat habitatModificado = daoH.mostarHabitat(habitat.getId());
	        		
	        		
	        if (!habitat.getNombre().equals(habitatModificado.getNombre())
	                || !habitat.isActivo().equals(habitatModificado.isActivo())) {
	            t.rollback();
	            fail("Error: Los atributos del habitat no coinciden después de la modificación");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void testListarHabitats() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        THabitat habitat1 = crearTHabitat();
	        THabitat habitat2 = crearTHabitat();

	        Integer idHabitat1 = daoH.altaHabitat(habitat1);
	        Integer idHabitat2 = daoH.altaHabitat(habitat2);

	        habitat1.setId(idHabitat1);
	        habitat2.setId(idHabitat2);

	        Set<THabitat> habitats = daoH.listarHabitats();

	        boolean foundHabitat1 = false;
	        boolean foundHabitat2 = false;

	        for (THabitat habitat : habitats) {
	            if (habitat.getId().equals(habitat1.getId())) {
	                foundHabitat1 = true;
	            } else if (habitat.getId().equals(habitat2.getId())) {
	                foundHabitat2 = true;
	            }
	        }

	        if (!foundHabitat1 || !foundHabitat2) {
	            t.rollback();
	            fail("Error: La lista de habitats no contiene todos los habitats creados");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void listarHabitatPorEmpleado() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        
	        TEmpleado empleado1 = crearTEmpleadoLimpieza();
	        Integer idEmpleado1 = daoEmpleado.alta(empleado1);
	        THabitat habitat1 = crearTHabitat();
	        THabitat habitat2 = crearTHabitat();

	        Integer idHabitat1 = daoH.altaHabitat(habitat1);
	        Integer idHabitat2 = daoH.altaHabitat(habitat2);

	        habitat1.setId(idHabitat1);
	        habitat2.setId(idHabitat2);
	        
	        DAOTrabajo daoT = FactoriaDAO.getInstance().getDAOTrabajo();
	        
	        TTrabajo trabajo1 = new TTrabajo();
	        trabajo1.setIdEmpleado(idEmpleado1);
	        trabajo1.setIdHabitat(idHabitat1);
	        
	        TTrabajo trabajo2 = new TTrabajo();
	        trabajo2.setIdEmpleado(idEmpleado1);
	        trabajo2.setIdHabitat(idHabitat2);
	        
	        daoT.vincularHabitatEmpleado(trabajo1);
	        daoT.vincularHabitatEmpleado(trabajo2);
	        

	        Set<THabitat> habitatsPorEmpleado = daoH.listarHabitatPorEmpleado(idEmpleado1);

	        boolean foundHabitat1 = false;
	        boolean foundHabitat2 = false;

	        for (THabitat habitat : habitatsPorEmpleado) {
	            if (habitat.getId().equals(habitat1.getId())) {
	                foundHabitat1 = true;
	            } else if (habitat.getId().equals(habitat2.getId())) {
	                foundHabitat2 = true;
	            }
	        }

	        if (!foundHabitat1 || !foundHabitat2) {
	            t.rollback();
	            fail("Error: La lista de habitats por empleado no contiene todos los habitats vinculados al empleado");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void testLeerPorNombreUnico() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        String nombreUnico = generarNombreAleatorio(); 
	        THabitat habitat = crearTHabitatConNombre(nombreUnico);

	        Integer idHabitat = daoH.altaHabitat(habitat);
	        habitat.setId(idHabitat);

	        THabitat habitatLeido = daoH.leerPorNombreUnico(nombreUnico);

	        if (!habitat.getId().equals(habitatLeido.getId())) {
	            t.rollback();
	            fail("Error: El habitat leído por nombre único no coincide con el habitat creado");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void fechaCorrecta(){
		String date = "2021/11/24";
		
		try{
			LocalDate fechaQueQuiero =  ParseStringToLocalDate(date);
			System.out.println(fechaQueQuiero);
		}
		catch (Exception e) {
			 fail("Falló a la hora de hacer la conversión");
		}
		
		
		
		
	}
	
public LocalDate ParseStringToLocalDate (String date) throws Exception {
		
		date = date.replace('/', '-');

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     
		try {
			LocalDate dateTime = LocalDate.parse(date, format);
			return dateTime;
			} 
		catch (Exception  e) {
			e.printStackTrace();
			throw e;
			}
		
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
	
	private THabitat crearTHabitatConNombre(String nombreUnico) {

		THabitat habitat = new THabitat();
		habitat.setNombre(nombreUnico);
		habitat.setActivo(true);
		habitat.setTamano(generateRandom());
	
		return habitat;
	}
	
	
	private THabitat crearTHabitat(){
		
		THabitat habitat = new THabitat();
		
		habitat.setNombre(generarNombreAleatorio());
		habitat.setActivo(true);
		habitat.setTamano(generateRandom());
		habitat.setId(generateRandom());
		
		return habitat;
		
	}
	
	private int generateRandom(){
		return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
	}
	private double generateRandomDouble()
	{
		return ThreadLocalRandom.current().nextDouble(0, 10000 + 1);
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

}
