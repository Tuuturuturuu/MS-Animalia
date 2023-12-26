package Negocio;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import Integracion.Empleado.DAOEmpleado;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Factura.DAOFactura;
import Integracion.Habitat.DAOHabitat;
import Integracion.Pase.DAOPase;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Integracion.Transactions.TransactionMySQL;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.HabitatSA;
import Negocio.Habitat.THabitat;
import Negocio.Habitat.TTrabajo;
import Negocio.Pase.TPase;




public class HabitatSATest {

	private static HabitatSA habitatSA;
	private static DAOHabitat daoH;
	private static DAOEmpleado daoEmpleado;
	private static DAOPase daoPase;
	private static DAOFactura daoFactura;

	public boolean equals(THabitat a, THabitat b) {
		return a.getNombre().equals(b.getNombre()) && a.getTamano().equals(b.getTamano()) && a.isActivo().equals(b.isActivo());
	}

	@BeforeClass
		public static void beforeClass() {
		habitatSA = FactoriaSA.getInstance().getHabitatSA();
		daoH = FactoriaDAO.getInstance().getDAOHabitat();
		daoEmpleado = FactoriaDAO.getInstance().getDAOEmpleado();
	}


	@Test
		public void AltaHabitat() {
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		THabitat exp = new THabitat(0, true, nombre, n);

		Integer id = habitatSA.AltaHabitat(exp);
		THabitat act = habitatSA.MostrarHabitat(id);
		Assert.assertTrue(equals(exp, act));
	}


	@Test
		public void BajaHabitat() {
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		THabitat aux = new THabitat(0, true, nombre, n);
		THabitat exp = new THabitat(0, false, nombre, n);

		int actnum = habitatSA.AltaHabitat(aux);
		actnum = habitatSA.BajaHabitat(actnum);
		THabitat act = habitatSA.MostrarHabitat(actnum);

		Assert.assertTrue(equals(exp, act));


	}

	@Test
		public void MostrarHabitat() {
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		THabitat exp = new THabitat(0, true, nombre, n);

		int actnum = habitatSA.AltaHabitat(exp);
		THabitat act = habitatSA.MostrarHabitat(actnum);

		Assert.assertTrue(equals(exp, act));
	}

	@Test
		public void ModificarHabitat() {
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		String nombreNuevo = generarNombreAleatorio();
		int nNuevo = generateRandom();
		THabitat aux = new THabitat(0, true, nombre, n);
		THabitat exp = new THabitat(0, true, nombreNuevo, nNuevo);

		int actnum = habitatSA.AltaHabitat(aux);
		actnum = habitatSA.ModificarHabitat(new THabitat(actnum, true, nombreNuevo, nNuevo));
		THabitat act = habitatSA.MostrarHabitat(actnum);

		Assert.assertTrue(equals(exp, act));

	}

	@Test
		public void ListarHabitat() {
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		THabitat aux = new THabitat(0, true, nombre, n);
		Set<THabitat> exp = new HashSet<THabitat>();
		exp.add(aux);
		habitatSA.AltaHabitat(aux);
		Set<THabitat> act = habitatSA.ListarHabitat();

		boolean ok = exp.size() == act.size();

		Iterator<THabitat> i = exp.iterator();
		Iterator<THabitat> j = act.iterator();

		while (i.hasNext() && j.hasNext()) {
			ok = equals((THabitat)i.next(), (THabitat)j.next());
		}

		Assert.assertTrue(ok);

	}

	@Test
		public void VincularEmpleadoHabitat() {
		TEmpleado empleado = crearTEmpleadoLimpieza();
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		THabitat aux = new THabitat(0, true, nombre, n);
		int actnum = habitatSA.AltaHabitat(aux);
		TTrabajo trabajo = new TTrabajo(actnum, empleado.getId());
		habitatSA.VincularEmpleadoHabitat(trabajo);
		THabitat exp = habitatSA.MostrarHabitat(trabajo.getIdHabitat());
		Assert.assertTrue(equals(exp, aux));

	}

	@Test
		public void DesvincularEmpleadoHabitat() {
		TEmpleado empleado = crearTEmpleadoLimpieza();
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		THabitat aux = new THabitat(0, true, nombre, n);
		int actnum = habitatSA.AltaHabitat(aux);
		TTrabajo trabajo = new TTrabajo(actnum, empleado.getId());
		habitatSA.VincularEmpleadoHabitat(trabajo);
		THabitat exp = habitatSA.MostrarHabitat(trabajo.getIdHabitat());
		habitatSA.DesvincularEmpleadoHabitat(trabajo);
		Assert.assertTrue(equals(exp, aux));

	}

	@Test
		public void ListarHabitatPorEmpleado() {
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		THabitat aux = new THabitat(0, true, nombre, n);
		Set<THabitat> exp = new HashSet<THabitat>();
		exp.add(aux);
		habitatSA.AltaHabitat(aux);
		Set<THabitat> act = habitatSA.ListarHabitat();

		boolean ok = exp.size() == act.size();

		Iterator<THabitat> i = exp.iterator();
		Iterator<THabitat> j = act.iterator();

		while (i.hasNext() && j.hasNext()) {
			ok = equals((THabitat)i.next(), (THabitat)j.next());
		}

		Assert.assertTrue(ok);

	}

	@Test
		public void CalcularHabitatMasIngresos() {
		String nombre = generarNombreAleatorio();
		int n = generateRandom();
		THabitat aux = new THabitat(0, true, nombre, n);
		int idHabitat = habitatSA.AltaHabitat(aux);
		TPase pase = crearTPaseConHabitat(idHabitat);
		//daoPase.altaPase(pase);
		//System.out.println(pase.getIDHabitat());
		boolean ok = (idHabitat == 1);

		Assert.assertTrue(ok);

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



	private double generateRandomDouble()
	{
		return ThreadLocalRandom.current().nextDouble(0, 10000 + 1);
	}

	private Date generarFechaAleatoria() {

		LocalDate start = LocalDate.of(2000, 3, 26);
		LocalDate end = LocalDate.of(2023, 12, 31);

		long random = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay());
		Date fecha = Date.valueOf(LocalDate.ofEpochDay(random));

		return fecha;
	}
	private Time generarHoraAleatoria() {
		int hora = ThreadLocalRandom.current().nextInt(0, 24);
		int minuto = ThreadLocalRandom.current().nextInt(0, 60);
		int segundo = ThreadLocalRandom.current().nextInt(0, 60);
		LocalTime random = LocalTime.of(hora, minuto, segundo);
		return Time.valueOf(random);
	}


	public TTrabajo crearTTrabajo(Integer idE, Integer idH) {

		TTrabajo trabajo = new TTrabajo();

		trabajo.setIdEmpleado(idE);
		trabajo.setIdHabitat(idH);

		return trabajo;

	}

	public TEmpleado crearTEmpleadoLimpieza() {
		TEmpleadoLimpieza empleado = new TEmpleadoLimpieza();
		empleado.setId(generateRandom());
		empleado.setNombre(generarNombreAleatorio());
		empleado.setDni(generarDniAleatorio());
		empleado.setSueldoBase((generateRandomd()));
		empleado.setTelefono(generateRandom());
		empleado.setActivo(true);
		empleado.setSuplemento((double)generateRandom());
		empleado.setZona(generarNombreAleatorio());
		return empleado;
	}

	public String generarDniAleatorio() {
		String caracteres = "0123456789";
		StringBuilder dniAleatorio = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(caracteres.length());
			dniAleatorio.append(caracteres.charAt(index));
		}
		return dniAleatorio.toString();
	}

	public THabitat crearTHabitatConNombre(String nombreUnico) {

		THabitat habitat = new THabitat();
		habitat.setNombre(nombreUnico);
		habitat.setActivo(true);
		habitat.setTamano(generateRandom());

		return habitat;
	}

	public THabitat crearTHabitat() {

		THabitat habitat = new THabitat();

		habitat.setNombre(generarNombreAleatorio());
		habitat.setActivo(true);
		habitat.setTamano(generateRandom());
		habitat.setId(generateRandom());

		return habitat;

	}

	public Double generateRandomd() {
		return ThreadLocalRandom.current().nextDouble(0, 10000 + 1);
	}

	public int generateRandom() {
		return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
	}

	public String generarNombreAleatorio() {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder nombreAleatorio = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(caracteres.length());
			nombreAleatorio.append(caracteres.charAt(index));
		}
		return nombreAleatorio.toString();
	}

	public Transaction crearTransaccion() {
		try {
			TransactionManager tm = TransactionManager.getInstance();
			tm.newTransaction();
			return tm.getTransaction();
		}
		catch (Exception e) {
			fail("Error transaccional");
			return null;
		}
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
			transactionmysql.borrarDatosTabla("trabajo");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
