package Integracion;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Random;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Factura.DAOFactura;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Factura.TFactura;

public class DAOFacturaTest {
	private static Random random;
	private static DAOFactura daoF;

	private boolean equals(TFactura a, TFactura b) {
		return a.GetId().equals(b.GetId()) && a.GetPrecioTotal().equals(b.GetPrecioTotal());
	}
	
	@BeforeClass
	public static void beforeClass() {
		random = new Random();
		daoF = FactoriaDAO.getInstance().getDAOFactura();
	}
	
	@Test
	public void testcrearFactura(){
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		TFactura f = crearTFactura();
		Integer idF = daoF.crearFactura(f);
		if (idF < 0) {
			t.rollback();
			fail("Error: crearFactura() deberia retornar ID > 0");
		}
		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepción");
			e.printStackTrace();
		}
	}
	

	@Test
	public void testMostrar(){
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		TFactura f = crearTFactura();
		Integer id = daoF.crearFactura(f);
		f.SetId(id);

		if (!equals(f, daoF.leerFactura(f.GetId()))) {
			t.rollback();
			fail("Error: leerFactura debería devolver una factura identica");
		}

		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepción");
			e.printStackTrace();
		}
	}
	


	@Test
	public void testModificar() throws Exception {
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		TFactura f = crearTFactura();
		
		Integer id = daoF.crearFactura(f);
		
		f.SetId(id);
		f.SetPrecioTotal(random.nextDouble());
		f.SetFechaCompra(new Date(System.currentTimeMillis()));
		
		
		if (daoF.modificarFactura(f) < 0) {
			t.rollback();
			fail("Error: modificarFactura() sin errores deberia retornar un numero positivo");
		}

		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepción");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRealizarDevolucion() {
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		
		TFactura f = crearTFactura();
		Integer id = daoF.crearFactura(f);
		f.SetId(id);
		TFactura dev = new TFactura();
		dev.SetId(f.GetId());
		dev.SetPrecioTotal(f.GetPrecioTotal() - 20);

		if (daoF.realizarDevolucion(dev) < 0) {
			t.rollback();
			fail("Error: realizarDevolucion() sin errores deberia retornar un numero positivo");
		}
		
		if (!dev.GetPrecioTotal().equals(daoF.leerFactura(dev.GetId()).GetPrecioTotal())) {
			t.rollback();
			fail("Error: realizarDevolucion() deberia actualizar el precio de la factura");
		}
		
		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepción");
			e.printStackTrace();
		}
	}

	@Test
	public void testleerTodasFactura(){
		try
		{
		Transaction t = crearTransaccion();
		t.start();
		
		TFactura f1 = crearTFactura(), f2 = crearTFactura();
		Integer id1 = daoF.crearFactura(f1);
		Integer id2 = daoF.crearFactura(f2);
		f1.SetId(id1);
		f2.SetId(id2);

		boolean found1 = false, found2 = false;
		Set<TFactura> all = daoF.leerTodasFactura();
		for (TFactura f : all) {
			if (equals(f, f1))
				found1 = true;
			else if (equals(f, f2))
				found2 = true;
		}

		if (!found1 || !found2) {
			t.rollback();
			fail("Error: ReadAll() deberia retornar lista que contiene entidades creadas");
		}

		t.commit();
		}
		catch(Exception e)
		{
			fail("Excepción");
			e.printStackTrace();
		}
	}
	
	private TFactura crearTFactura()
	{
		long time = System.currentTimeMillis() + (long) (random.nextDouble() + 1) * System.currentTimeMillis();

		TFactura f = new TFactura();
		
		f.SetId(null);
		f.SetPrecioTotal(100.00);
		f.SetFechaCompra(new Date(time));
		f.SetActivo(true);
		
		return f;
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
