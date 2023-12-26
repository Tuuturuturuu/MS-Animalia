package Negocio; 


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Transactions.TransactionMySQL;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import Negocio.FactoriaSA.FactoriaSAImp;
import Negocio.Habitat.HabitatSA;
import Negocio.Habitat.THabitat;
import Negocio.Pase.PaseSA;
import Negocio.Pase.TPase;

public class PaseSATest {

	private boolean compPases(TPase a, TPase b) {
		boolean comp=true;
		Date fecha = a.getFecha(); // Obtener la fecha
		Date fecha2 = b.getFecha();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // Definir el formato deseado
		String fechaFormateada1= formato.format(fecha);
		String fechaFormateada2= formato.format(fecha2);
		comp= fechaFormateada1.equals(fechaFormateada2);
		
		return a.getActivo() == b.getActivo() && a.getCantidadDisponible() == b.getCantidadDisponible()
				&& a.getPrecio().equals(b.getPrecio()) && a.getIDHabitat() == b.getIDHabitat()
				&& comp && a.getHora().equals(b.getHora());
	}

	@Test
	public void crearPaseOK() {
		PaseSA saPase = FactoriaSAImp.getInstance().getPaseSA();
		HabitatSA saHabitatAux=FactoriaSAImp.getInstance().getHabitatSA();
		
		THabitat habitat=new THabitat(0, true, "prueba", 10);
		Integer idHabitat= saHabitatAux.AltaHabitat(habitat);
		
		TPase exp = new TPase(0, Date.valueOf("1111-12-12"), Time.valueOf("10:50:50"),Double.parseDouble("5"), 10,idHabitat, true);
		Integer id=saPase.Alta(exp);
		TPase pase = saPase.Mostrar(id);
		Assert.assertTrue(compPases(exp, pase));
	}

	@Test
	public void eliminarPaseOK() {
		PaseSA saPase = FactoriaSAImp.getInstance().getPaseSA();	
		HabitatSA saHabitatAux=FactoriaSAImp.getInstance().getHabitatSA();
		
		THabitat habitat=new THabitat(1, true, "prueba2", 10);
		Integer idHabitat= saHabitatAux.AltaHabitat(habitat);
		
		TPase aux = new TPase(0, Date.valueOf("2222-12-12"), Time.valueOf("11:50:50"),Double.parseDouble("5"), 10,idHabitat, true);
		TPase exp = new TPase(1, Date.valueOf("2222-12-12"), Time.valueOf("11:50:50"),Double.parseDouble("5"), 10,idHabitat, false);

		Integer id=saPase.Alta(aux);
		Integer id2=saPase.Baja(id);
		TPase pase = saPase.Mostrar(id);
		
		Assert.assertTrue(compPases(exp, pase));
	}

	@Test
	public void modificarPaseOK() {
		PaseSA saPase = FactoriaSAImp.getInstance().getPaseSA();	
		HabitatSA saHabitatAux=FactoriaSAImp.getInstance().getHabitatSA();
		
		THabitat habitat=new THabitat(2, true, "prueba3", 10);
		Integer idHabitat= saHabitatAux.AltaHabitat(habitat);
		
		TPase aux = new TPase(0, Date.valueOf("1000-12-12"), Time.valueOf("12:50:50"),Double.parseDouble("5"), 10,idHabitat, true);	
		TPase exp = new TPase(1, Date.valueOf("2000-11-11"), Time.valueOf("20:40:40"),Double.parseDouble("10"), 20,idHabitat, true);
		
		Integer id=saPase.Alta(aux);
		Integer id2=saPase.Modificar(new TPase(id, Date.valueOf("2000-11-11"), Time.valueOf("20:40:40"),Double.parseDouble("10"), 20,idHabitat, true));
		TPase pase = saPase.Mostrar(id2);
		
		Assert.assertTrue(compPases(exp, pase));
	}

	@Test
	public void mostrarPaseOK() {
		PaseSA saPase = FactoriaSAImp.getInstance().getPaseSA();	
		HabitatSA saHabitatAux=FactoriaSAImp.getInstance().getHabitatSA();
		
		THabitat habitat=new THabitat(3, true, "prueba4", 10);
		Integer idHabitat= saHabitatAux.AltaHabitat(habitat);
		TPase aux = new TPase(0, Date.valueOf("1002-12-12"), Time.valueOf("13:50:50"),Double.parseDouble("5"), 10,idHabitat, true);	

		Integer id=saPase.Alta(aux);
		TPase pase = saPase.Mostrar(id);
		Assert.assertTrue(compPases(aux, pase));
	}

	@Test
	public void mostrarTodosPasesOK() {
		PaseSA saPase = FactoriaSAImp.getInstance().getPaseSA();	
		HabitatSA saHabitatAux=FactoriaSAImp.getInstance().getHabitatSA();
		
		THabitat habitat=new THabitat(4, true, "prueba5", 10);
		Integer idHabitat= saHabitatAux.AltaHabitat(habitat);

		TPase aux1 = new TPase(1, Date.valueOf("1004-12-12"), Time.valueOf("14:50:50"),Double.parseDouble("5"), 10,idHabitat, true);	
		Set<TPase> exp = new HashSet<TPase>();
		exp.add(aux1);

		saPase.Alta(aux1);
		Set<TPase> pase = saPase.Listar();

		boolean ok = exp.size() == pase.size();

		Iterator<TPase> i = exp.iterator();
		Iterator<TPase> j = pase.iterator();

		while (i.hasNext() && j.hasNext()) {
			ok = compPases((TPase) i.next(), (TPase) j.next());
		}

		Assert.assertTrue(ok);
	}

	@Test
	public void mostrarPasesPorHabitatOK() {
		PaseSA saPase = FactoriaSAImp.getInstance().getPaseSA();		

		HabitatSA saHabitatAux=FactoriaSAImp.getInstance().getHabitatSA();
		
		THabitat habitat=new THabitat(5, true, "prueba6", 10);
		Integer idPers= saHabitatAux.AltaHabitat(habitat);
	
		TPase aux1 = new TPase(1, Date.valueOf("1110-12-12"), Time.valueOf("17:50:50"),Double.parseDouble("5"), 10,idPers, true);	
		Set<TPase> exp = new HashSet<TPase>();
		exp.add(aux1);

		saPase.Alta(aux1);
		Set<TPase> pase = saPase.ListarPasePorHabitat(idPers);
		
		boolean ok = exp.size() == pase.size();

		Iterator<TPase> i = exp.iterator();
		Iterator<TPase> j = pase.iterator();

		while (i.hasNext() && j.hasNext()) {
			ok = compPases((TPase) i.next(), (TPase) j.next());
		}

		Assert.assertTrue(ok);
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
	public void borrarBD_pase() {
	TransactionMySQL transactionmysql;
	try {
		transactionmysql = new TransactionMySQL();
		transactionmysql.borrarDatosTabla("habitat");
		transactionmysql.borrarDatosTabla("pase");
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}


}