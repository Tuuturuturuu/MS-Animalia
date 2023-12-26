package Integracion;

import static org.junit.Assert.fail;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Animal.DAOAnimal;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Negocio.Animal.TAnimalNoAcuatico;

public class DAOAnimalTest {

    private static DAOAnimal daoAnimal;

    private boolean equals(TAnimal a, TAnimal b) {
    	boolean igualComunes = a.getId().equals(b.getId())
    			&& a.getNombre().equals(b.getNombre())
    			&& a.isActivo() == b.isActivo();
    	boolean igualHerencia = false;
    	if(a instanceof TAnimalAcuatico && b instanceof TAnimalAcuatico)
    	{
    		TAnimalAcuatico la = (TAnimalAcuatico) a;
    		TAnimalAcuatico lb = (TAnimalAcuatico) b;
    		igualHerencia = la.getTipoAgua().equals(lb.getTipoAgua())
    				&& la.getTemperatura().equals(lb.getTemperatura());
    	}
    	else if(a instanceof TAnimalNoAcuatico && b instanceof TAnimalNoAcuatico)
    	{
    		TAnimalNoAcuatico za = (TAnimalNoAcuatico) a;
    		TAnimalNoAcuatico zb = (TAnimalNoAcuatico) b;
    		igualHerencia = za.getNumPatas().equals(zb.getNumPatas());
    	}
    	
    	return igualComunes && igualHerencia;
    }

    @BeforeClass
    public static void beforeClass() {
    	daoAnimal = FactoriaDAO.getInstance().getDAOAnimal();
    }

    @Test
    public void testAltaAnimal() {
        try {
            Transaction t = crearTransaccion();
            t.start();
            TAnimal animal1 = crearTAnimalAcuatico();
            Integer idAnimal1 = daoAnimal.altaAnimal(animal1);
            if (idAnimal1 < 0) {
                t.rollback();
                fail("Error: altaAnimal() deberia retornar ID > 0");
            }
            TAnimal animal2 = crearTAnimalNoAcuatico();
            Integer idAnimal2 = daoAnimal.altaAnimal(animal2);
            if (idAnimal2 < 0) {
                t.rollback();
                fail("Error: altaAnimal() deberia retornar ID > 0");
            }
            t.commit();
        } catch (Exception e) {
            fail("Excepci0n");
            e.printStackTrace();
        }
    }


    @Test
    public void testBajaAnimal() {
        try {
            Transaction t = crearTransaccion();
            t.start();
            TAnimal animal1 = crearTAnimalAcuatico();
            Integer idAnimal1 = daoAnimal.altaAnimal(animal1);
            Integer result1 = daoAnimal.bajaAnimal(idAnimal1);
            if (result1 < 0) {
                t.rollback();
                fail("Error: bajaAnimal() deberia retornar un numero positivo");
            }
            
            TAnimal animal2 = crearTAnimalNoAcuatico();
            Integer idAnimal2 = daoAnimal.altaAnimal(animal2);
            Integer result2 = daoAnimal.bajaAnimal(idAnimal2);
            if (result2 < 0) {
                t.rollback();
                fail("Error: bajaAnimal() deberia retornar un numero positivo");
            }
            t.commit();
        } catch (Exception e) {
            fail("Excepcion");
            e.printStackTrace();
        }
    }

    @Test
    public void testMostrarAnimal() {
        try {
            Transaction t = crearTransaccion();
            t.start();
            TAnimal animal1 = crearTAnimalAcuatico();
            Integer id1 = daoAnimal.altaAnimal(animal1);
            animal1.setId(id1);
            if (!equals(animal1, daoAnimal.mostrarAnimal(id1))) {
                t.rollback();
                fail("Error: mostrarAnimal() deberia devolver un animal identico");
            }
            
            TAnimal animal2 = crearTAnimalNoAcuatico();
            Integer id2 = daoAnimal.altaAnimal(animal2);
            animal2.setId(id2);
            if (!equals(animal2, daoAnimal.mostrarAnimal(id2))) {
                t.rollback();
                fail("Error: mostrarAnimal() deberia devolver un animal identico");
            }

            t.commit();
        } catch (Exception e) {
            fail("Excepcion");
            e.printStackTrace();
        }
    }

    
    @Test
    public void testModificarAnimal() {
        try {
            Transaction t = crearTransaccion();
            t.start();

            TAnimal Animal1 = crearTAnimalAcuatico();
            Integer id1 = daoAnimal.altaAnimal(Animal1);
            Animal1.setId(id1);
            Animal1.setNombre(generarNombreAleatorio());
            Integer resultado1 = daoAnimal.modificarAnimal(Animal1);
            if (resultado1 < 0) {
                t.rollback();
                fail("Error: modificarAnimal() deberia retornar un numero positivo");
            }
            TAnimal AnimalModificado1 = daoAnimal.mostrarAnimal(id1);
            if (!equals(Animal1, AnimalModificado1)) {
                t.rollback();
                fail("Error: Los atributos del Animal no coinciden despues de la modificacion");
            }
            
            TAnimal Animal2 = crearTAnimalNoAcuatico();
            Integer id2 = daoAnimal.altaAnimal(Animal2);
            Animal2.setId(id2);
            Animal2.setNombre(generarNombreAleatorio());
            Integer resultado2 = daoAnimal.modificarAnimal(Animal2);
            if (resultado2 < 0) {
                t.rollback();
                fail("Error: modificarAnimal() deberia retornar un numero positivo");
            }
            TAnimal AnimalModificado2 = daoAnimal.mostrarAnimal(id2);
            if (!equals(Animal2, AnimalModificado2)) {
                t.rollback();
                fail("Error: Los atributos del Animal no coinciden despues de la modificacion");
            }

            t.commit();
        } catch (Exception e) {
            fail("Excepciin");
            e.printStackTrace();
        }
    }
    

    @Test
    public void testListarAnimales() {
        try {
            Transaction t = crearTransaccion();
            t.start();

            TAnimal animal1 = crearTAnimalAcuatico();
            TAnimal animal2 = crearTAnimalNoAcuatico();

            Integer idAnimal1 = daoAnimal.altaAnimal(animal1);
            Integer idAnimal2 = daoAnimal.altaAnimal(animal2);

            animal1.setId(idAnimal1);
            animal2.setId(idAnimal2);

            Set<TAnimal> animales = daoAnimal.listarAnimales();

            boolean foundAnimal1 = false;
            boolean foundAnimal2 = false;

            for (TAnimal animal : animales) {
                if (animal.getId().equals(animal1.getId())) {
                    foundAnimal1 = true;
                } else if (animal.getId().equals(animal2.getId())) {
                    foundAnimal2 = true;
                }
            }

            if (!foundAnimal1 || !foundAnimal2) {
                t.rollback();
                fail("Error: La lista de animales no contiene todos los animales creados");
            }

            t.commit();
        } catch (Exception e) {
            fail("Excepciin");
            e.printStackTrace();
        }
    }

    @Test
    public void testLeerPorNombreUnico() {
        try {
            Transaction t = crearTransaccion();
            t.start();

            TAnimal animal1 = crearTAnimalAcuatico();
            Integer id1 = daoAnimal.altaAnimal(animal1);
            animal1.setId(id1);
            TAnimal animalLeido1 = daoAnimal.leerPorNombreUnico(animal1.getNombre());
            if (!equals(animal1, animalLeido1)) {
                t.rollback();
                fail("Error: El animal leido por Nombre unico no coincide con el animal creado");
            }
            
            TAnimal animal2 = crearTAnimalNoAcuatico();
            Integer id2 = daoAnimal.altaAnimal(animal2);
            animal2.setId(id2);
            TAnimal animalLeido2 = daoAnimal.leerPorNombreUnico(animal2.getNombre());
            if (!equals(animal2, animalLeido2)) {
                t.rollback();
                fail("Error: El animal leido por Nombre unico no coincide con el animal creado");
            }

            t.commit();
        } catch (Exception e) {
            fail("Excepcion");
            e.printStackTrace();
        }
    }

    private TAnimal crearTAnimalAcuatico() {
    	TAnimalAcuatico animal = new TAnimalAcuatico();
    	animal.setId(generateRandom());
        animal.setNombre(generarNombreAleatorio());
        animal.setTipoAgua(generarNombreAleatorio());
        animal.setTemperatura((int) generateRandom());
        animal.setId_Especie(generateRandom());
        animal.setActivo(true);
        animal.setTipo(1);
        return animal;
    }
    
    private TAnimal crearTAnimalNoAcuatico() {
    	TAnimalNoAcuatico animal = new TAnimalNoAcuatico();
    	animal.setId(generateRandom());
    	animal.setNombre(generarNombreAleatorio());
    	animal.setNumPatas((int) generateRandom());
    	animal.setId_Especie(generateRandom());
    	animal.setActivo(true);
    	animal.setTipo(0);
        return animal;
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

    private int generateRandom() {
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
