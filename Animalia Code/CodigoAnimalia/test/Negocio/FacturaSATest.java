package Negocio;


import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Empleado.DAOEmpleado;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Factura.TLineaFactura;
import Integracion.Habitat.DAOHabitat;
import Integracion.Pase.DAOPase;
import Integracion.Transactions.TransactionMySQL;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Factura.FacturaSA;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConPases;
import Negocio.Habitat.HabitatSA;
import Negocio.Habitat.THabitat;
import Negocio.Pase.PaseSA;
import Negocio.Pase.TPase;

public class FacturaSATest {

	private static HabitatSA saH;
	private static PaseSA saPase;
	private static DAOFactura daoFactura;
	private static FacturaSA saFactura;
	private static DAOLineaFactura daoLineaFactura;

	public boolean equals(TFactura a, TFactura b) {
		return a.GetPrecioTotal().equals(b.GetPrecioTotal()) && a.GetFechaCompra().equals(b.GetFechaCompra()) && a.GetActivo().equals(b.GetActivo());
	}


	@BeforeClass
		public static void beforeClass() {
		saFactura = FactoriaSA.getInstance().getFacturaSA();
		daoFactura = FactoriaDAO.getInstance().getDAOFactura();
		saH = FactoriaSA.getInstance().getHabitatSA();
		saPase = FactoriaSA.getInstance().getPaseSA();
		daoLineaFactura = FactoriaDAO.getInstance().getDAOLineaFactura();

	}

	@Test
		public void cerrarFactura() {
		THabitat habitat = new THabitat(0, true, "test", 10);
		int numhab = saH.AltaHabitat(habitat);
		TPase pase = new TPase(0, Date.valueOf("2022-02-25"), Time.valueOf("19:30:25"), 25.5, 13, numhab, true);
		int numpase = saPase.Alta(pase);
		TFactura factura = new TFactura();
		TCarrito carrito = new TCarrito();
		int numFactura = saFactura.cerrarFactura(factura, carrito);
		Assert.assertTrue(numFactura < 0);
	}

	@Test
		public void mostrarFactura() {
		THabitat habitat = new THabitat(0, true, "test", 10);
		int numhab = saH.AltaHabitat(habitat);
		TPase pase = new TPase(0, Date.valueOf("2022-02-25"), Time.valueOf("19:30:25"), 25.5, 13, numhab, true);
		int numpase = saPase.Alta(pase);
		TFactura factura = new TFactura();
		TCarrito carrito = new TCarrito();
		int numFactura = saFactura.cerrarFactura(factura, carrito);
		TFacturaConPases facturaConPases = saFactura.mostrarFactura(numFactura);
		Assert.assertTrue(numFactura <= 0);
	}

	@Test
		public void listarFacturas() {
		THabitat habitat = new THabitat(0, true, "test", 10);
		int numhab = saH.AltaHabitat(habitat);
		TPase pase = new TPase(0, Date.valueOf("2022-02-25"), Time.valueOf("19:30:25"), 25.5, 13, numhab, true);
		saPase.Alta(pase);
		TFactura factura = new TFactura();
		TCarrito carrito = new TCarrito();
		saFactura.cerrarFactura(factura, carrito);
		Set<TFactura> facturas = new HashSet<TFactura>();
		facturas.add(factura);
		Set<TFactura> facturas1 = saFactura.listarFacturas();
		Assert.assertTrue(facturas.size() != facturas1.size());
	}

	@Test
		public void modificarFactura() {
		THabitat habitat = new THabitat(0, true, "test", 10);
		int numhab = saH.AltaHabitat(habitat);
		TPase pase = new TPase(0, Date.valueOf("2022-02-25"), Time.valueOf("19:30:25"), 25.5, 13, numhab, true);
		saPase.Alta(pase);
		TFactura factura = new TFactura();
		TCarrito carrito = new TCarrito();
		int factura2 = saFactura.cerrarFactura(factura, carrito);
		Assert.assertTrue(factura2 < 0);
	}

	@Test
		public void devolverFactura() {
		THabitat habitat = new THabitat(0, true, "test", 10);
		int numhab = saH.AltaHabitat(habitat);
		TPase pase = new TPase(0, Date.valueOf("2022-02-25"), Time.valueOf("19:30:25"), 25.5, 13, numhab, true);
		saPase.Alta(pase);
		TFactura factura = new TFactura();
		TCarrito carrito = new TCarrito();
		TLineaFactura lineaFactura = new TLineaFactura();
		int factura2 = saFactura.cerrarFactura(factura, carrito);
		Assert.assertTrue(factura2 < 0);
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
		public void borrarBD_Factura() {
		TransactionMySQL transactionmysql;
		try {
			transactionmysql = new TransactionMySQL();
			transactionmysql.borrarDatosTabla("habitat");
			transactionmysql.borrarDatosTabla("trabajo");
			transactionmysql.borrarDatosTabla("pase");
			transactionmysql.borrarDatosTabla("factura");
			transactionmysql.borrarDatosTabla("lineafactura");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

