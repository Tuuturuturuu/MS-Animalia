package Negocio;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Transactions.TransactionMySQL;
import Negocio.Animal.AnimalSA;
import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Negocio.Especie.EspecieSA;
import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.HabitatSA;
import Negocio.Habitat.THabitat;

public class EspecieSATest {
	
	private static EspecieSA especieSA;
	private static HabitatSA habitatSA;
	private static AnimalSA aSA;
	
	@BeforeClass
	public static void beforeClass(){
		especieSA = FactoriaSA.getInstance().getEspecieSA();
		habitatSA = FactoriaSA.getInstance().getHabitatSA();
		aSA = FactoriaSA.getInstance().getAnimalSA();
	}

	
	public boolean equals(TEspecie a, TEspecie b) {
		return a.getID().equals(b.getID()) && a.getNombreEspecie().equals(b.getNombreEspecie()) && a.getID_habitat().equals(b.getID_habitat()) && a.getActivo().equals(b.getActivo());
	}
	
	@Test
	public void altaEspecieConHabitatActivo() {
		
		try {
			THabitat hab = crearTHabitat();
			int idHab = habitatSA.AltaHabitat(hab);
			TEspecie espAlta = crearTEspecieConHabitatQueExiste(idHab);
			int idEspAlta = especieSA.altaEspecie(espAlta);
			if(idEspAlta<0){
				fail("Error: altaEspecie() debería retornar ID > 0");
			}
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void altaEspecieHabitatNoExiste() {
		
		try {
			TEspecie espAlta = crearTEspecie();
			int idEspAlta = especieSA.altaEspecie(espAlta);
			if(idEspAlta!=-20){
				fail("Error: altaEspecie() no debería permitir dar de alta una especie si no existe el habitat");
			}
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void bajaEspecieQueExisteYEstaActiva(){
		
		try{
			int idEspecieDarBaja = altaEspecieParaTest();
			int idResultado = especieSA.bajaEspecie(idEspecieDarBaja);
			if(idResultado<0){
				fail("Error: bajaEspecie() debería retornar ID > 0");
			}
		}catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void bajaEspecieQueNoExiste(){
		
		try{
			int idEspecieDarBaja = 1000;
			int idResultado = especieSA.bajaEspecie(idEspecieDarBaja);
			if(idResultado!=-11){
				fail("Error: bajaEspecie() debería fallar");
			}
		}catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void bajaEspecieInactiva(){
		
		try{
			int idEspecieDarBaja = altaEspecieParaTest();
			int idResultado = especieSA.bajaEspecie(idEspecieDarBaja);
			if(idResultado<0){
				fail("Error: bajaEspecie() la primera vez debería retornar ID > 0");
			}
			int idVolverADarDeBaja = especieSA.bajaEspecie(idEspecieDarBaja);
			if(idVolverADarDeBaja!=-12){
				fail("Error: bajaEspecie() deberia fallar porque la especie ya está inactiva");
			}
		}catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void bajaEspecieConAnimalesActivos(){
		
		try{
			
			int idEspecieDarBaja = altaEspecieParaTest();
			
			TAnimal animal = crearTAnimalAcuaticoConIdEspecie(idEspecieDarBaja);
			
			int idAnimal = aSA.altaAnimal(animal);
			
			if(idAnimal<0){
				fail("Error: altaAnimal() debería retornar un número positivo");
			}
			
			int idResultado = especieSA.bajaEspecie(idEspecieDarBaja);
			System.out.println();
			if(idResultado!=-13){
				fail("Error: bajaEspecie() debería fallar, el habitat tiene animales activos");
			}
		}catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}	
		
		
	}
	
	@Test
	public void mostrarEspecie(){
		try{
			int idEspecieMostrar = altaEspecieParaTest();
			TEspecie esp = especieSA.mostrarEspecie(idEspecieMostrar);
			if(esp.getID()!=idEspecieMostrar){
				fail("Error debería mostrar la misma especie que hemos creado");
			}
			
		}catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}		
		
	}
	
	@Test
	public void modificarEspecie(){
		try{
			int idEspecieMostrar = altaEspecieParaTest();
			TEspecie esp = especieSA.mostrarEspecie(idEspecieMostrar);
			esp.setNombreEspecie(generarNombreAleatorio());
			int resultado = especieSA.modificarEspecie(esp);
			if(resultado<0){
				fail("No se ha podido modificar la especie con los datos dados");	
			}
			
		}catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}		
	}
	
	@Test
	public void listarEspecie(){
		
		try{
			THabitat hab = crearTHabitat();
			int idHab = habitatSA.AltaHabitat(hab);
			
			TEspecie especie1 = crearTEspecieConHabitatQueExiste(idHab);
			TEspecie especie2 = crearTEspecieConHabitatQueExiste(idHab);
	
	        Integer idEspecie1 = especieSA.altaEspecie(especie1);
	        Integer idEspecie2 = especieSA.altaEspecie(especie2);
	        
	        especie1.setID(idEspecie1);
	        especie2.setID(idEspecie2);
	        
	        Set<TEspecie> especies = especieSA.listarEspecies();
		
	        boolean foundEspecie1 = false;
	        boolean foundEspecie2 = false;
	
	        for (TEspecie especie : especies) {
	            if (especie.getID()==especie1.getID()) {
	                foundEspecie1 = true;
	            } else if (especie.getID()==especie2.getID()) {
	                foundEspecie2 = true;
	            }
	        }
	
	        if (!foundEspecie1 || !foundEspecie2) {
	            fail("Error: La lista de especies no contiene todas las especies creadas");
	        }
		}catch (Exception e) {
	        fail("Excepción");
	        e.printStackTrace();
	    }
		
	}
	
	@Test
	public void listarEspeciePorHabitatQueExiste(){
		
		try{
			THabitat hab = crearTHabitat();
			int idHab = habitatSA.AltaHabitat(hab);
			TEspecie espAlta1 = crearTEspecieConHabitatQueExiste(idHab);
			int idEspAlta1 = especieSA.altaEspecie(espAlta1);
			espAlta1.setID(idEspAlta1);
			TEspecie espAlta2 = crearTEspecieConHabitatQueExiste(idHab);
			int idEspAlta2 = especieSA.altaEspecie(espAlta2);
			espAlta2.setID(idEspAlta2);
			
			 Set<TEspecie> especies = especieSA.listarEspeciePorHabitat(idHab);
			
			 boolean foundEspecie1 = false;
		     boolean foundEspecie2 = false;
		
		        for (TEspecie especie : especies) {
		            if (especie.getID()==espAlta1.getID()) {
		                foundEspecie1 = true;
		            } else if (especie.getID()==espAlta2.getID()) {
		                foundEspecie2 = true;
		            }
		        }
		
		        if (!foundEspecie1 || !foundEspecie2) {
		            fail("Error: La lista de especies no contiene todas las especies creadas");
		        }
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void listarEspeciePorHabitatQueNoExiste(){
		
		try{
				
			 Set<TEspecie> especies = especieSA.listarEspeciePorHabitat(1000);
			 int resultado = 0;
			 for (TEspecie especie : especies) {
				 
				 resultado = especie.getID(); 
		            
		        }
		        if(especies.size()>1 || resultado!=-20){
		            fail("Error: el id del habitat no existe, debería dar error");
		        }
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	 private TAnimal crearTAnimalAcuaticoConIdEspecie(int idEspecie) {
	    	TAnimalAcuatico animal = new TAnimalAcuatico();
	    	animal.setId(generateRandom());
	        animal.setNombre(generarNombreAleatorio());
	        animal.setTipoAgua(generarNombreAleatorio());
	        animal.setTemperatura((int) generateRandom());
	        animal.setId_Especie(idEspecie);
	        animal.setActivo(true);
	        animal.setTipo(1);
	        return animal;
	    }
	
	private int altaEspecieParaTest(){
		int id = -1;
		
		try {
			THabitat hab = crearTHabitat();
			int idHab = habitatSA.AltaHabitat(hab);
			TEspecie espAlta = crearTEspecieConHabitatQueExiste(idHab);
			int idEspAlta = especieSA.altaEspecie(espAlta);
			if(idEspAlta>0){
				id = idEspAlta;	
			}
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
		
		return id;
		
	}
	

	
	private TEspecie crearTEspecieConHabitatQueExiste(int idHabitat) {

		TEspecie especie = new TEspecie();
		especie.setNombreEspecie(generarNombreAleatorio());
		especie.setActivo(true);
		especie.setID_habitat(idHabitat);

		return especie;
	}
	private TEspecie crearTEspecie(){
		
		TEspecie especie = new TEspecie();
		especie.setNombreEspecie(generarNombreAleatorio());
		especie.setActivo(true);
		especie.setID_habitat(generateRandom());

		return especie;
		
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
	
	private int generateRandom(){
		return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
	}
	
	@AfterClass
	public static void borrarBD() {
	TransactionMySQL transactionmysql;
	try {
		transactionmysql = new TransactionMySQL();
		transactionmysql.borrarDatosTabla("habitat");
		transactionmysql.borrarDatosTabla("trabajo");
		transactionmysql.borrarDatosTabla("factura");
		transactionmysql.borrarDatosTabla("lineafactura");
		transactionmysql.borrarDatosTabla("animal");
		transactionmysql.borrarDatosTabla("animalacuatico");
		transactionmysql.borrarDatosTabla("animalnoacuatico");
		transactionmysql.borrarDatosTabla("empleado");
		transactionmysql.borrarDatosTabla("empleadozoologico");
		transactionmysql.borrarDatosTabla("empleadolimpieza");
		transactionmysql.borrarDatosTabla("especie");
		transactionmysql.borrarDatosTabla("pase");
		transactionmysql.cerrarConnection();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
	@After
	public void borrarBD_habitat() {
	TransactionMySQL transactionmysql;
	try {
		transactionmysql = new TransactionMySQL();
		transactionmysql.borrarDatosTabla("habitat");
		transactionmysql.borrarDatosTabla("especie");
		transactionmysql.borrarDatosTabla("animal");
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}
