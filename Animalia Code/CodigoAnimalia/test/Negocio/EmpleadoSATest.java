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
import Negocio.Empleado.EmpleadoSA;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;
import Negocio.Especie.EspecieSA;
import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.HabitatSA;
import Negocio.Habitat.THabitat;

public class EmpleadoSATest {

	private static EmpleadoSA saEmpleado;
	private static HabitatSA saHabitat;
	private static EspecieSA saEspecie;

	private boolean equals(TEmpleado a, TEmpleado b) {
		boolean igualComunes = a.getId().equals(b.getId()) && a.getNombre().equals(b.getNombre())
				&& a.getActivo() == b.getActivo() && a.getTipo() == b.getTipo();

		return igualComunes;
	}

	@BeforeClass
	public static void beforeClass() {
		saEmpleado = FactoriaSA.getInstance().getEmpleadoSA();
	}

	@Test
	public void testAltaEmpleado() {
		try {

			TEmpleado emp1 = crearTEmpleadoLimpieza();

			Integer idEmpleado1 = saEmpleado.altaEmpleado(emp1);
			if (idEmpleado1 < 0) {
				fail("Error: altaEmpleado() deberia retornar ID > 0");
			}
			TEmpleado empleado2 = crearTEmpleadoZoologico();
			Integer idEmpleado2 = saEmpleado.altaEmpleado(empleado2);
			if (idEmpleado2 < 0) {
				fail("Error: altaEmpleado() deberia retornar ID > 0");
			}
		} catch (Exception e) {
			fail("Excepci0n");
			e.printStackTrace();
		}
	}

	@Test
	public void testBajaEmpleado() {
		try {
			TEmpleado empleado1 = crearTEmpleadoZoologico();
			Integer idEmpleado1 = saEmpleado.altaEmpleado(empleado1);
			Integer result1 = saEmpleado.bajaEmpleado(idEmpleado1);
			if (result1 < 0) {
				fail("Error: bajaEmpleado() deberia retornar un numero positivo");
			}

			TEmpleado empleado2 = crearTEmpleadoLimpieza();
			Integer idEmpleado2 = saEmpleado.altaEmpleado(empleado2);
			Integer result2 = saEmpleado.bajaEmpleado(idEmpleado2);
			if (result2 < 0) {
				fail("Error: bajaEmpleado() deberia retornar un numero positivo");
			}
		} catch (Exception e) {
			fail("Excepcion");
			e.printStackTrace();
		}
	}

	@Test
	public void testMostrarEmpleado() {
		try {
			TEmpleado empleado1 = crearTEmpleadoZoologico();
			Integer id1 = saEmpleado.altaEmpleado(empleado1);
			empleado1.setId(id1);

			TEmpleado empleadoAux = saEmpleado.mostrarEmpleado(id1);
			if (!equals(empleado1, empleadoAux)) {
				fail("Error: mostrarEmpleado() deberia devolver un empleado identico");
			}

			TEmpleado empleado2 = crearTEmpleadoLimpieza();
			Integer id2 = saEmpleado.altaEmpleado(empleado2);
			empleado2.setId(id2);
			if (!equals(empleado2, saEmpleado.mostrarEmpleado(id2))) {
				fail("Error: mostrarEmpleado() deberia devolver un empleado identico");
			}

		} catch (Exception e) {
			fail("Excepcion");
			e.printStackTrace();
		}
	}

	
	
	@Test
	public void testListarEmpleados() {
		try {
//			THabitat habitat6 = new THabitat(5, true, "testAnimal6", 10);
//			Integer idHabitat = saHabitat.AltaHabitat(habitat6);
//
//			TEspecie especie6 = new TEspecie(5, "testAnimal6", idHabitat, true);
//			Integer idEspecie = saEspecie.altaEspecie(especie6);

			TEmpleado empleado1 = crearTEmpleadoZoologico();
			TEmpleado empleado2 = crearTEmpleadoLimpieza();

			Integer idEmpleado1 = saEmpleado.altaEmpleado(empleado1);
			Integer idAnimal2 = saEmpleado.altaEmpleado(empleado2);

			empleado1.setId(idEmpleado1);
			empleado2.setId(idAnimal2);

			Set<TEmpleado> animales = saEmpleado.listarEmpleado();

			boolean foundEmpleado1 = false;
			boolean foundEmpleado2 = false;

			for (TEmpleado animal : animales) {
				if (animal.getId().equals(empleado1.getId())) {
					foundEmpleado1 = true;
				} else if (animal.getId().equals(empleado2.getId())) {
					foundEmpleado2 = true;
				}
			}

			if (!foundEmpleado1 || !foundEmpleado2) {
				fail("Error: La lista de animales no contiene todos los animales creados");
			}
		} catch (Exception e) {
			fail("Excepcion");
			e.printStackTrace();
		}
	}

	private TEmpleado crearTEmpleadoLimpieza() {
		TEmpleadoLimpieza empleado = new TEmpleadoLimpieza();
		empleado.setId(generateRandom());
		empleado.setNombre(generarNombreAleatorio());
		empleado.setDni("58634430A");
		empleado.setTelefono(345343522);
		empleado.setActivo(true);
		empleado.setTipo(0);
		empleado.setSueldoBase(2.2);
		empleado.setSuplemento(2.2);
		empleado.setZona(generarNombreAleatorio());
		return empleado;
	}

	private TEmpleado crearTEmpleadoZoologico() {
		TEmpleadoZoologico empleado = new TEmpleadoZoologico();
		empleado.setId(generateRandom());
		empleado.setNombre(generarNombreAleatorio());
		empleado.setDni("58634430B");
		empleado.setTelefono(235355455);
		empleado.setActivo(true);
		empleado.setTipo(0);
		empleado.setSueldoBase(123.3);
		empleado.setTasa(2.2);
		empleado.setExperiencia(2);
		empleado.setEspecialidad(generarNombreAleatorio());

		return empleado;
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
			transactionmysql.borrarDatosTabla("Habitat");
			transactionmysql.borrarDatosTabla("Trabajo");
			transactionmysql.borrarDatosTabla("Factura");
			transactionmysql.borrarDatosTabla("LineaFactura");
			transactionmysql.borrarDatosTabla("Animal");
			transactionmysql.borrarDatosTabla("AnimalAcuatico");
			transactionmysql.borrarDatosTabla("AnimalNoAcuatico");
			transactionmysql.borrarDatosTabla("Empleado");
			transactionmysql.borrarDatosTabla("EmpleadoZoologico");
			transactionmysql.borrarDatosTabla("EmpleadoLimpieza");
			transactionmysql.borrarDatosTabla("Especie");
			transactionmysql.borrarDatosTabla("Pase");
			transactionmysql.cerrarConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@After
	public void borrarBD_habitat() {
		TransactionMySQL transactionmysql;
		try {
			transactionmysql = new TransactionMySQL();
			transactionmysql.borrarDatosTabla("Habitat");
			transactionmysql.borrarDatosTabla("Empleado");
			transactionmysql.borrarDatosTabla("Especie");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
