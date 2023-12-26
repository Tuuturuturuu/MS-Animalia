package Negocio;

import static org.junit.Assert.fail;

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
import Negocio.Animal.TAnimalNoAcuatico;
import Negocio.Especie.EspecieSA;
import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.HabitatSA;
import Negocio.Habitat.THabitat;

public class AnimalSATest {

    private static AnimalSA saAnimal;
    private static EspecieSA saEspecie;
    private static HabitatSA saHabitat;

    private boolean equals(TAnimal a, TAnimal b) {
    	boolean igualComunes = a.getId().equals(b.getId())
    			&& a.getNombre().equals(b.getNombre())
    			&& a.isActivo() == b.isActivo() && a.getTipo()==b.getTipo();
    	//boolean igualHerencia = false;
    	/*if(a.getTipo()==1 && b.getTipo()==1)
    	{
    		//TAnimalAcuatico la = (TAnimalAcuatico) a;
    		//TAnimalAcuatico lb = (TAnimalAcuatico) b;
    		igualHerencia = ((TAnimalAcuatico)a).getTipoAgua().equals(((TAnimalAcuatico)b).getTipoAgua())
    				&& ((TAnimalAcuatico)a).getTemperatura().equals(((TAnimalAcuatico)b).getTemperatura());
    	}
    	else if(a.getTipo()==0 && b.getTipo()==0)
    	{
    		TAnimalNoAcuatico za = (TAnimalNoAcuatico) a;
    		TAnimalNoAcuatico zb = (TAnimalNoAcuatico) b;
    		igualHerencia = za.getNumPatas().equals(zb.getNumPatas());
    	}*/
    	
    	return igualComunes;
    }

    @BeforeClass
    public static void beforeClass() {
    	saAnimal = FactoriaSA.getInstance().getAnimalSA();
    	saEspecie = FactoriaSA.getInstance().getEspecieSA();
    	saHabitat = FactoriaSA.getInstance().getHabitatSA();
    }

    @Test
    public void testAltaAnimal() {
        try {
        	THabitat habitat1=new THabitat(0, true,"testAnimal",10);
        	Integer idHabitat=saHabitat.AltaHabitat(habitat1);
        	
        	TEspecie especie1=new TEspecie(0, "testAnimal",idHabitat, true);
        	Integer idEspecie=saEspecie.altaEspecie(especie1);
        	
            TAnimal animal1 = crearTAnimalAcuatico();
            animal1.setId_Especie(idEspecie);
    
            Integer idAnimal1 = saAnimal.altaAnimal(animal1);
            if (idAnimal1 < 0) {
                fail("Error: altaAnimal() deberia retornar ID > 0");
            }
            TAnimal animal2 = crearTAnimalNoAcuatico();
            animal2.setId_Especie(idEspecie);
            Integer idAnimal2 = saAnimal.altaAnimal(animal2);
            if (idAnimal2 < 0) {
                fail("Error: altaAnimal() deberia retornar ID > 0");
            }
        } catch (Exception e) {
            fail("Excepci0n");
            e.printStackTrace();
        }
    }


    @Test
    public void testBajaAnimal() {
        try {
        	THabitat habitat2=new THabitat(1, true,"testAnimal2",10);
        	Integer idHabitat=saHabitat.AltaHabitat(habitat2);
        	
        	TEspecie especie2=new TEspecie(1, "testAnimal2",idHabitat, true);
        	Integer idEspecie=saEspecie.altaEspecie(especie2);
        	
            TAnimal animal1 = crearTAnimalAcuatico();
            animal1.setId_Especie(idEspecie);
            
            Integer idAnimal1 = saAnimal.altaAnimal(animal1);
            Integer result1 =saAnimal.bajaAnimal(idAnimal1);
            if (result1 < 0) {
                fail("Error: bajaAnimal() deberia retornar un numero positivo");
            }
            
            TAnimal animal2 = crearTAnimalNoAcuatico();
            animal2.setId_Especie(idEspecie);
            Integer idAnimal2 = saAnimal.altaAnimal(animal2);
            Integer result2 = saAnimal.bajaAnimal(idAnimal2);
            if (result2 < 0) {
                fail("Error: bajaAnimal() deberia retornar un numero positivo");
            }
        } catch (Exception e) {
            fail("Excepcion");
            e.printStackTrace();
        }
    }

    @Test
    public void testMostrarAnimal() {
        try {
        	THabitat habitat3=new THabitat(2, true,"testAnimal3",10);
        	Integer idHabitat=saHabitat.AltaHabitat(habitat3);
        	
        	TEspecie especie3=new TEspecie(2, "testAnimal3",idHabitat, true);
        	Integer idEspecie=saEspecie.altaEspecie(especie3);
        	
            TAnimal animal1 = crearTAnimalAcuatico();
            animal1.setId_Especie(idEspecie);
            Integer id1 = saAnimal.altaAnimal(animal1);
            animal1.setId(id1);
            
            TAnimal animalAux= saAnimal.mostrarAnimal(id1);
            if (!equals(animal1, animalAux)) {
                fail("Error: mostrarAnimal() deberia devolver un animal identico");
            }
            
            TAnimal animal2 = crearTAnimalNoAcuatico();
            animal2.setId_Especie(idEspecie);
            Integer id2 = saAnimal.altaAnimal(animal2);
            animal2.setId(id2);
            if (!equals(animal2, saAnimal.mostrarAnimal(id2))) {
                fail("Error: mostrarAnimal() deberia devolver un animal identico");
            }

        } catch (Exception e) {
            fail("Excepcion");
            e.printStackTrace();
        }
    }

    
    @Test
    public void testModificarAnimalAcuatico() {
        try {
            THabitat habitat = new THabitat(1, true, "testHabitat", 20);
            Integer idHabitat = saHabitat.AltaHabitat(habitat);

            TEspecie especie = new TEspecie(1, "testEspecie", idHabitat, true);
            Integer idEspecie = saEspecie.altaEspecie(especie);

            TAnimalAcuatico animalOriginal = crearTAnimalAcuatico();
            animalOriginal.setId_Especie(idEspecie);

            Integer idAnimal = saAnimal.altaAnimal(animalOriginal);

            TAnimalAcuatico animalModificado = crearTAnimalAcuatico();
            animalModificado.setId(idAnimal);
            animalModificado.setId_Especie(idEspecie);
            animalModificado.setNombre("NuevoNombre"); 

            Integer resultado = saAnimal.modificarAnimalAcuatico(animalModificado);

            if (resultado < 0) {
                fail("Error: modificarAnimalAcuatico() debería retornar un número positivo");
            }

            TAnimal animalObtenido = saAnimal.mostrarAnimal(idAnimal);

            if (!animalModificado.getNombre().equals(animalObtenido.getNombre())) {
                fail("Error: Los nombres no coinciden después de la modificación");
            }

        } catch (Exception e) {
            fail("Excepción: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testModificarAnimalNoAcuatico() {
        try {
            THabitat habitat = new THabitat(1, true, "testHabitat", 20);
            Integer idHabitat = saHabitat.AltaHabitat(habitat);

            TEspecie especie = new TEspecie(1, "testEspecie", idHabitat, true);
            Integer idEspecie = saEspecie.altaEspecie(especie);

            TAnimalNoAcuatico animalOriginal = crearTAnimalNoAcuatico();
            animalOriginal.setId_Especie(idEspecie);

            Integer idAnimal = saAnimal.altaAnimal(animalOriginal);

            TAnimalNoAcuatico animalModificado = crearTAnimalNoAcuatico();
            animalModificado.setId(idAnimal);
            animalModificado.setId_Especie(idEspecie);
            animalModificado.setNombre("NuevoNombre"); 

            Integer resultado = saAnimal.modificarAnimalNoAcuatico(animalModificado);

            if (resultado < 0) {
                fail("Error: modificarAnimalNoAcuatico() debería retornar un número positivo");
            }

            TAnimal animalObtenido = saAnimal.mostrarAnimal(idAnimal);

            if (!animalModificado.getNombre().equals(animalObtenido.getNombre())) {
                fail("Error: Los nombres no coinciden después de la modificación");
            }

        } catch (Exception e) {
            fail("Excepción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testListarAnimalesPorEspecie() {
        try {
        	THabitat habitat5=new THabitat(4, true,"testAnimal5",10);
        	Integer idHabitat=saHabitat.AltaHabitat(habitat5);
        	
        	TEspecie especie5=new TEspecie(4, "testAnimal5",idHabitat, true);
        	Integer idEspecie=saEspecie.altaEspecie(especie5);
        	
            TAnimal animal1 = crearTAnimalAcuatico();
            animal1.setId_Especie(idEspecie);
            TAnimal animal2 = crearTAnimalNoAcuatico();
            animal2.setId_Especie(idEspecie);

            Integer idAnimal1 = saAnimal.altaAnimal(animal1);
            Integer idAnimal2 = saAnimal.altaAnimal(animal2);

            animal1.setId(idAnimal1);
            animal2.setId(idAnimal2);

            Set<TAnimal> animales = saAnimal.listarAnimalesPorEspecie(idEspecie);

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
                fail("Error: La lista de animales no contiene todos los animales creados");
            }
        } catch (Exception e) {
            fail("Excepcion");
            e.printStackTrace();
        }
    }

    @Test
    public void testListarAnimales() {
        try {
        	THabitat habitat6=new THabitat(5, true,"testAnimal6",10);
        	Integer idHabitat=saHabitat.AltaHabitat(habitat6);
        	
        	TEspecie especie6=new TEspecie(5, "testAnimal6",idHabitat, true);
        	Integer idEspecie=saEspecie.altaEspecie(especie6);
        	
            TAnimal animal1 = crearTAnimalAcuatico();
            animal1.setId_Especie(idEspecie);
            TAnimal animal2 = crearTAnimalNoAcuatico();
            animal2.setId_Especie(idEspecie);

            Integer idAnimal1 = saAnimal.altaAnimal(animal1);
            Integer idAnimal2 = saAnimal.altaAnimal(animal2);

            animal1.setId(idAnimal1);
            animal2.setId(idAnimal2);

            Set<TAnimal> animales = saAnimal.listarAnimales();

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
                fail("Error: La lista de animales no contiene todos los animales creados");
            }
        } catch (Exception e) {
            fail("Excepcion");
            e.printStackTrace();
        }
    }

    private TAnimalAcuatico crearTAnimalAcuatico() {
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
    
    private TAnimalNoAcuatico crearTAnimalNoAcuatico() {
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
		transactionmysql.borrarDatosTabla("animal");
		transactionmysql.borrarDatosTabla("especie");
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}