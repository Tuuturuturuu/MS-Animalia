package Integracion;

import static org.junit.Assert.fail;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Pase.DAOPase;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Pase.TPase;

public class DAOPaseTest {
	private static DAOPase daoP;

	private boolean equals(TPase a, TPase b) {
		return a.getID().equals(b.getID()) && a.getIDHabitat().equals(b.getIDHabitat());
	}

	@BeforeClass
	public static void beforeClass() {
		daoP = FactoriaDAO.getInstance().getDAOPase();
	}

	@Test
	public void testAltaPase() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			TPase pase = crearTPase();
			Integer idPase = daoP.altaPase(pase);
			if (idPase < 0) {
				t.rollback();
				fail("Error: altaPase() deberia retornar ID > 0");
			}
			t.commit();
		} catch (Exception e) {
			fail("Excepcion");
			e.printStackTrace();
		}
	}

	@Test
	public void testBajaPase() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			TPase pase = crearTPase();
			Integer idPase = daoP.altaPase(pase);
			Integer result = daoP.bajaPase(idPase);
			if (result < 0) {
				t.rollback();
				fail("Error: bajaPase() deberia retornar un numero positivo");
			}
			t.commit();
		} catch (Exception e) {
			fail("Excepcion");
			e.printStackTrace();
		}
	}

	@Test
	public void testMostrarPase() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			TPase pase = crearTPase();
			Integer idPase = daoP.altaPase(pase);
			pase.setID(idPase);
			
			

			if (!equals(pase, daoP.mostrarPase(pase.getID()))) {
				t.rollback();
				fail("Error: mostrarPase() deberia devolver una especie identica");
			}

			t.commit();
		} catch (Exception e) {
			fail("Excepcion");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificarPase() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        TPase pase = crearTPase();
			Integer idPase = daoP.altaPase(pase);
	        pase.setID(idPase);
	        
	        pase.setFecha(generarFechaAleatoria());
	        pase.setHora(generarHoraAleatoria());
	        pase.setActivo(false);

	        Integer resultado = daoP.modificarPase(pase);

	        if (resultado < 0) {
	            t.rollback();
	            fail("Error: modificarPase() deberia un numero positivo");
	        }

	        TPase paseModificado = daoP.mostrarPase(pase.getID());
	        if (!pase.getFecha().equals(paseModificado.getFecha()) && !pase.getHora().equals(paseModificado.getHora())
	                || !pase.getActivo().equals(paseModificado.getActivo())) {
	            t.rollback();
	            fail("Error: Los atributos del pase no coinciden despues de la modificacion");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepcion");
	        e.printStackTrace();
	    }
	}
	

	@Test
	public void testListarEspecie() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        TPase pase1 = crearTPase();
	        TPase pase2 = crearTPase();

	        Integer idPase1 = daoP.altaPase(pase1);
	        Integer idPase2 = daoP.altaPase(pase2);

	        pase1.setID(idPase1);
	        pase2.setID(idPase2);

	        Set<TPase> pases = daoP.listarPases();

	        boolean foundPase1 = false;
	        boolean foundPase2 = false;

	        for (TPase pase : pases) {
	            if (pase.getID().equals(pase1.getID())) {
	                foundPase1 = true;
	            } else if (pase.getID().equals(pase2.getID())) {
	                foundPase2 = true;
	            }
	        }

	        if (!foundPase1 || !foundPase2) {
	            t.rollback();
	            fail("Error: La lista de pases no contiene todas las pases creadas");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepcion");
	        e.printStackTrace();
	    }
	}
	

	@Test
	public void testListarPasesPorHabitat() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        Integer idHabitat = generateRandom(); 
	        TPase pase1 = crearTPaseConHabitat(idHabitat);
	        TPase pase2 = crearTPaseConHabitat(idHabitat);

	        Integer idPase1 = daoP.altaPase(pase1);
	        Integer idPase2 = daoP.altaPase(pase2);

	        pase1.setID(idPase1);
	        pase2.setID(idPase2);

	        Set<TPase> pasePorHabitat = daoP.listarPasesPorHabitat(idHabitat);

	        boolean foundP1 = false;
	        boolean foundP2 = false;

	        for (TPase pase : pasePorHabitat) {
	            if (pase.getID().equals(pase1.getID())) {
	                foundP1 = true;
	            } else if (pase.getID().equals(pase2.getID())) {
	                foundP2 = true;
	            }
	        }

	        if (!foundP1 || !foundP2) {
	            t.rollback();
	            fail("Error: La lista de pases por habitat no contiene todos los pases asociados al habitat");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepcion");
	        e.printStackTrace();
	    }
	}

	@Test
	public void testListarEspeciePorHabitatActivos() {
		try {
	        Transaction t = crearTransaccion();
	        t.start();

	        Integer idHabitat = generateRandom(); 
	        TPase pase1 = crearTPaseConHabitat(idHabitat);
	        TPase pase2 = crearTPaseConHabitat(idHabitat);

	        Integer idPase1 = daoP.altaPase(pase1);
	        Integer idPase2 = daoP.altaPase(pase2);

	        pase1.setID(idPase1);
	        pase2.setID(idPase2);
	        
	        pase2.setActivo(false);
	        daoP.modificarPase(pase2);
	        
	        Set<TPase> pasePorHabitat = daoP.listarPasesPorHabitatActivos(idHabitat);

	        boolean foundP1 = false;
	        boolean foundP2 = false;

	        for (TPase pase : pasePorHabitat) {
	            if (pase.getID().equals(pase1.getID())) {
	                foundP1 = true;
	            } else if (pase.getID().equals(pase2.getID())) {
	                foundP2 = true;
	            }
	        }
	        
	        if (!foundP1) {
	            t.rollback();
	            fail("Error: La lista de pases por habitat Activos no contiene el pase activo");
	        }

	        if (foundP2) {
	            t.rollback();
	            fail("Error: La lista de pases por habitat Activos contiene un pase desactivado");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepcion");
	        e.printStackTrace();
	    }
	}

	@Test
	public void testLeerPorCampoUnico() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        Date fechaUnico = generarFechaAleatoria(); 
	        Time horaUnico = generarHoraAleatoria();
	        Integer idHabitatUnico = generateRandom();
	        TPase pase = crearTPaseConCampo(fechaUnico,horaUnico,idHabitatUnico);

	        Integer idPase = daoP.altaPase(pase);
	        pase.setID(idPase);

	        TPase paseLeida = daoP.leerPorCampoUnico(fechaUnico,horaUnico,idHabitatUnico);

	        if (!pase.getID().equals(paseLeida.getID())) {
	            t.rollback();
	            fail("Error: el Pase leido por campo unico no coincide con el pase creado");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepcion");
	        e.printStackTrace();
	    }
	}
	

	private TPase crearTPaseConHabitat(Integer idHabitat) {

		TPase pase = new TPase();
		pase.setFecha(generarFechaAleatoria());
		pase.setCantidadDisponible(generateRandom());
		pase.setHora(generarHoraAleatoria());
		pase.setPrecio(generateRandomDouble());
		pase.setActivo(true);
		pase.setIDHabitat(idHabitat);

		return pase;
	}

	private TPase crearTPaseConCampo(Date fecha, Time hora,Integer habitat){
		TPase pase = new TPase();
		pase.setFecha(fecha);
		pase.setCantidadDisponible(generateRandom());
		pase.setHora(hora);
		pase.setPrecio(generateRandomDouble());
		pase.setIDHabitat(habitat);
		pase.setActivo(true);

		return pase;
	}
	private TPase crearTPase() {

		TPase pase = new TPase();
		pase.setFecha(generarFechaAleatoria());
		pase.setCantidadDisponible(generateRandom());
		pase.setHora(generarHoraAleatoria());
		pase.setPrecio(generateRandomDouble());
		pase.setIDHabitat(generateRandom());
		pase.setActivo(true);

		return pase;
	}


	private Date generarFechaAleatoria() {
		
		LocalDate start = LocalDate.of(2000,3, 26);
		LocalDate end = LocalDate.of(2023,12,31);
		
		long random = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay());
		Date fecha = Date.valueOf(LocalDate.ofEpochDay(random));
	    
		return fecha;
	}
	private Time generarHoraAleatoria(){
		int hora = ThreadLocalRandom.current().nextInt(0, 24);
        int minuto = ThreadLocalRandom.current().nextInt(0, 60);
        int segundo = ThreadLocalRandom.current().nextInt(0, 60);
        LocalTime random = LocalTime.of(hora, minuto, segundo);
		return Time.valueOf(random);
	}

	private int generateRandom()
	{
		return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
	}
	
	private double generateRandomDouble()
	{
		return ThreadLocalRandom.current().nextDouble(0, 10000 + 1);
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
