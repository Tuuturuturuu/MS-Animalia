package Integracion;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Factura.TLineaFactura;
import Integracion.Pase.DAOPase;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Factura.TFactura;
import Negocio.Pase.TPase;

public class DAOLineaFacturaTest {
	private static DAOLineaFactura daoLF;

	private boolean equals(TLineaFactura a, TLineaFactura b) {
		return a.GetIdFactura().equals(b.GetIdFactura()) 
				&& a.GetIdPase().equals(b.GetIdPase())
				&& a.GetCantidad().equals(b.GetCantidad())
				&& a.GetPrecio().equals(b.GetPrecio());
	}
	
	@BeforeClass
	public static void beforeClass() {
		daoLF = FactoriaDAO.getInstance().getDAOLineaFactura();
	}
	
	@Test
	public void testcrearLineaFactura(){
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		TLineaFactura lf = crearTLineaFactura();
		Integer r = daoLF.crearLineaFactura(lf);
		if (r < 0) {
			t.rollback();
			fail("Error: crearLineaFactura() deberia retornar 0");
		}
		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepci�n");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testleerLineaFactura(){
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		TLineaFactura lf = crearTLineaFactura();
		daoLF.crearLineaFactura(lf);

		if (!equals(lf, daoLF.leerLineaFactura(lf.GetIdFactura(), lf.GetIdPase()))) {
			t.rollback();
			fail("Error: leerLineaFactura deber�a devolver una factura identica");
		}

		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepci�n");
			e.printStackTrace();
		}
	}
	


	@Test
	public void testModificarLineaFactura() throws Exception {
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		TLineaFactura lf = crearTLineaFactura();
		
		daoLF.crearLineaFactura(lf);
		
		lf.SetPrecio(100.0);
		lf.SetCantidad(100);
		
		daoLF.modificarLineaFactura(lf);
		TLineaFactura mod = daoLF.leerLineaFactura(lf.GetIdFactura(), lf.GetIdPase());
		
		if (!equals(lf, mod)) {
			t.rollback();
			fail("Error: modificarFactura() sin errores deberia modificar precio y cantidad");
		}

		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepci�n");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminarLineaFactura() {
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		
		TLineaFactura lf = crearTLineaFactura();
		daoLF.crearLineaFactura(lf);
		int r = daoLF.eliminarLineaFactura(lf.GetIdFactura(), lf.GetIdPase());

		if (r < 0) {
			t.rollback();
			fail("Error: eliminarLineaFactura() sin errores deberia retornar 0");
		}
		

		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepci�n");
			e.printStackTrace();
		}
	}

	@Test
	public void testleerTodasLineaFactura(){
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		
		TLineaFactura lf1 = crearTLineaFactura(), lf2 = crearTLineaFactura();
		daoLF.crearLineaFactura(lf1);
		daoLF.crearLineaFactura(lf2);
		
		boolean found = false;
		Set<TLineaFactura> all = daoLF.leerTodasLineaFactura(lf1.GetIdFactura());
		for (TLineaFactura lf : all) {
			if (equals(lf, lf1))
				found = true;
		}

		if (!found) {
			t.rollback();
			fail("Error: leerTodasLineaFactura() deberia retornar lista que contiene las lineas factura de f1");
		}

		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepci�n");
			e.printStackTrace();
		}
	}
	
	private TLineaFactura crearTLineaFactura()
	{
		TLineaFactura lf = new TLineaFactura();
		DAOPase daoP = FactoriaDAO.getInstance().getDAOPase();
		DAOFactura daoF = FactoriaDAO.getInstance().getDAOFactura();
		lf.SetIdFactura(daoF.crearFactura(crearTFactura()));
		lf.SetIdPase(daoP.altaPase(crearTPase()));
		lf.SetCantidad(generateRandom());
		lf.SetPrecio(generateRandomDouble());
		
		return lf;
	}
	private TFactura crearTFactura()
	{
		long time = generateRandom();

		TFactura f = new TFactura();
		
		f.SetId(null);
		f.SetPrecioTotal(100.00);
		f.SetFechaCompra(new Date(time));
		f.SetActivo(true);
		
		return f;
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
			Transaction t = tm.getTransaction();
			return t;
		} catch (Exception e) {
			fail("Error transaccional");
			return null;
		}
	}
}
