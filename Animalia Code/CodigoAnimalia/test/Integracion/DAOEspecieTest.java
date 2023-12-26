package Integracion;

import Negocio.Especie.TEspecie;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import Integracion.Especie.DAOEspecie;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class DAOEspecieTest {
	private static DAOEspecie daoE;

	private boolean equals(TEspecie a, TEspecie b) {
		return a.getID().equals(b.getID()) && a.getNombreEspecie().equals(b.getNombreEspecie());
	}

	@BeforeClass
	public static void beforeClass() {
		daoE = FactoriaDAO.getInstance().getDAOEspecie();
	}

	@Test
	public void testAltaEspecie() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			TEspecie especie = crearTEspecie();
			Integer idEspecie = daoE.altaEspecie(especie);
			if (idEspecie < 0) {
				t.rollback();
				fail("Error: altaEspecie() debería retornar ID > 0");
			}
			t.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}

	@Test
	public void testBajaEspecie() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			TEspecie especie = crearTEspecie();
			Integer idEspecie = daoE.altaEspecie(especie);
			Integer result = daoE.bajaEspecie(idEspecie);
			if (result < 0) {
				t.rollback();
				fail("Error: bajaEspecie() debería retornar un número positivo");
			}
			t.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}

	@Test
	public void testMostrarEspecie() {
		try {
			Transaction t = crearTransaccion();
			t.start();
			TEspecie especie = crearTEspecie();
			Integer id = daoE.altaEspecie(especie);
			especie.setID(id);

			if (!equals(especie, daoE.mostrarEspecie(especie.getID()))) {
				t.rollback();
				fail("Error: mostrarEspecie() debería devolver una especie idéntica");
			}

			t.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificarEspecie() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        TEspecie especie = crearTEspecie();
	        Integer idEspecie = daoE.altaEspecie(especie);
	        especie.setID(idEspecie);
	        
	        especie.setNombreEspecie(generarNombreAleatorio());
	        especie.setActivo(false);

	        Integer resultado = daoE.modificarEspecie(especie);

	        if (resultado < 0) {
	            t.rollback();
	            fail("Error: modificarEspecie() debería un numero positivo");
	        }

	        TEspecie especieModificada = daoE.mostrarEspecie(especie.getID());
	        if (!especie.getNombreEspecie().equals(especieModificada.getNombreEspecie())
	                || !especie.getActivo().equals(especieModificada.getActivo())) {
	            t.rollback();
	            fail("Error: Los atributos de la especie no coinciden después de la modificación");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
	}
	

	@Test
	public void testListarEspecie() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        TEspecie especie1 = crearTEspecie();
	        TEspecie especie2 = crearTEspecie();

	        Integer idEspecie1 = daoE.altaEspecie(especie1);
	        Integer idEspecie2 = daoE.altaEspecie(especie2);

	        especie1.setID(idEspecie1);
	        especie2.setID(idEspecie2);

	        Set<TEspecie> especies = daoE.listarEspecie();

	        boolean foundEspecie1 = false;
	        boolean foundEspecie2 = false;

	        for (TEspecie especie : especies) {
	            if (especie.getID().equals(especie1.getID())) {
	                foundEspecie1 = true;
	            } else if (especie.getID().equals(especie2.getID())) {
	                foundEspecie2 = true;
	            }
	        }

	        if (!foundEspecie1 || !foundEspecie2) {
	            t.rollback();
	            fail("Error: La lista de especies no contiene todas las especies creadas");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
	}
	

	@Test
	public void testListarEspeciePorHabitat() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        Integer idHabitat = generateRandom(); 
	        TEspecie especie1 = crearTEspecieConHabitat(idHabitat);
	        TEspecie especie2 = crearTEspecieConHabitat(idHabitat);

	        Integer idEspecie1 = daoE.altaEspecie(especie1);
	        Integer idEspecie2 = daoE.altaEspecie(especie2);

	        especie1.setID(idEspecie1);
	        especie2.setID(idEspecie2);

	        Set<TEspecie> especiesPorHabitat = daoE.listarEspeciePorHabitat(idHabitat);

	        boolean foundEspecie1 = false;
	        boolean foundEspecie2 = false;

	        for (TEspecie especie : especiesPorHabitat) {
	            if (especie.getID().equals(especie1.getID())) {
	                foundEspecie1 = true;
	            } else if (especie.getID().equals(especie2.getID())) {
	                foundEspecie2 = true;
	            }
	        }

	        if (!foundEspecie1 || !foundEspecie2) {
	            t.rollback();
	            fail("Error: La lista de especies por hábitat no contiene todas las especies asociadas al hábitat");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
	}

	@Test
	public void testListarEspeciePorHabitatActivos() {
	    try {
	        Transaction t = crearTransaccion();
	        t.start();

	        Integer idHabitat = generateRandom(); 
	        TEspecie especie1 = crearTEspecieConHabitat(idHabitat);
	        TEspecie especie2 = crearTEspecieConHabitat(idHabitat);

	        Integer idEspecie1 = daoE.altaEspecie(especie1);
	        Integer idEspecie2 = daoE.altaEspecie(especie2);

	        especie1.setID(idEspecie1);
	        especie2.setID(idEspecie2);

	        especie2.setActivo(false);
	        daoE.modificarEspecie(especie2);

	        Set<TEspecie> especiesActivasPorHabitat = daoE.listarEspeciePorHabitatActivos(idHabitat);

	        boolean foundEspecie1 = false;
	        boolean foundEspecie2 = false;

	        for (TEspecie especie : especiesActivasPorHabitat) {
	            if (especie.getID().equals(especie1.getID())) {
	                foundEspecie1 = true;
	            } else if (especie.getID().equals(especie2.getID())) {
	                foundEspecie2 = true;
	            }
	        }
	        
	        if (!foundEspecie1) {
	            t.rollback();
	            fail("Error: La lista de especies activas por hábitat no contiene la especie activa");
	        }

	        if (foundEspecie2) {
	            t.rollback();
	            fail("Error: La lista de especies activas por hábitat contiene una especie desactivada");
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
	        TEspecie especie = crearTEspecieConNombre(nombreUnico);

	        Integer idEspecie = daoE.altaEspecie(especie);
	        especie.setID(idEspecie);

	        TEspecie especieLeida = daoE.leerPorNombreUnico(nombreUnico);

	        if (!especie.getID().equals(especieLeida.getID())) {
	            t.rollback();
	            fail("Error: La especie leída por nombre único no coincide con la especie creada");
	        }

	        t.commit();
	    } catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
	}
	
	private TEspecie crearTEspecieConNombre(String nombreUnico) {

		TEspecie especie = new TEspecie();
		especie.setNombreEspecie(nombreUnico);
		especie.setActivo(true);
		especie.setID_habitat(generateRandom());

		return especie;
	}

	private TEspecie crearTEspecieConHabitat(Integer idHabitat) {

		TEspecie especie = new TEspecie();
		especie.setNombreEspecie(generarNombreAleatorio());
		especie.setActivo(true);
		especie.setID_habitat(idHabitat);

		return especie;
	}

	private TEspecie crearTEspecie() {

		TEspecie especie = new TEspecie();
		especie.setNombreEspecie(generarNombreAleatorio());
		especie.setActivo(true);
		especie.setID_habitat(generateRandom());

		return especie;
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

	private int generateRandom()
	{
		return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
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
