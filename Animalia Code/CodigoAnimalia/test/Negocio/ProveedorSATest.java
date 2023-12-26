package Negocio;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Transactions.TransactionMySQL;
import Negocio.ProveedorJPA.ASProveedor;
import Negocio.ProveedorJPA.TProveedor;
import Negocio.ProveedorJPA.TProveedorConMarcas;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.MarcaProductoJPA.ASMarcaProducto;
import Negocio.MarcaProductoJPA.TMarcaProducto;

public class ProveedorSATest {

	private static ASProveedor asProveedor;
	private static Random random;

	@BeforeClass
	public static void beforeClass() {
		asProveedor = FactoriaSA.getInstance().getProveedorSA();
		random = new Random();
	}

	public TProveedor creaTProveedorRandom() {
		TProveedor Proveedor = new TProveedor();
		Proveedor.setNombre("ASProveedor Proveedor " + random.nextInt());
		Proveedor.setActivo(true);
		Proveedor.setCIF("" + random.nextInt(3000));
		Proveedor.setTelefono(random.nextInt(40000));
		Proveedor.setId(random.nextInt(50000));

		return Proveedor;
	}

	@Test
	public void altaProveedor() {
		TProveedor prov = creaTProveedorRandom();
		Integer res = asProveedor.altaProveedor(prov);
		prov.setId(res);
		if (res <= 0)
			fail("Error: Alta Proveedor sin errores deberia retornar ID > 0 y retorna " + res);
		res = asProveedor.altaProveedor(prov);
		if (res != -76)
			fail("Error: Entidad repetida activa, deberia devolver -76 y devuelve: " + res);
		
		asProveedor.bajaProveedor(prov.getId());
		res = asProveedor.altaProveedor(prov);
		
		if(res <= 0)
			fail("Error: Reactivar un proveedor debería devolver ID > 0");
	}

	@Test
	public void bajaProveedor() {
		Integer res = asProveedor.altaProveedor(creaTProveedorRandom());
		Integer sol = asProveedor.bajaProveedor(res);
		if (sol < 0)
			fail("Error: Baja Proveedor sin errores deberia retornar ID >= 0 y retorna " + res);
		sol = asProveedor.bajaProveedor(res);
		if (sol != -77)
			fail("Error: Entidad ya dada de baja deberia devolver -77 y devuelve: " + res);
	}

	@Test
	public void mostrarTest() {
		// Mostrar - Proveedor inexistente
		TProveedor res = asProveedor.mostrarProveedor(-1);
		if (res != null)
			fail("Error: Mostrar Proveedor inexsitente deberia retornar null y retorna " + res);

		// Mostrar - sin error
		TProveedor dep = creaTProveedorRandom();
		Integer rdo = asProveedor.altaProveedor(dep);
		res = asProveedor.mostrarProveedor(rdo);
		if (res == null)
			fail("Error: Mostrar Proveedor deberia retornar una entidad sin errores.");

	}

	@Test
	public void modificarProveedor() {
		TProveedor dep = creaTProveedorRandom();
		Integer pr = asProveedor.altaProveedor(dep);
		dep.setId(pr);
		dep.setCIF(dep.getCIF()+" TESTMODIFICAR");
		dep.setNombre(dep.getNombre()+ " TESTMODIFICAR");
		Integer res = asProveedor.modificarProveedor(dep);
		if (res <= 0)
			fail("Error: Modificar Proveedor sin errores deberia retornar ID > 0 y retorna " + res);
		res = asProveedor.altaProveedor(dep);
		if (res != -76)
			fail("Error: Entidad repetida activa, deberia devolver -1 y devuelve: " + res);
	}

	@Test
	public void listarTest() {
		// Listar - sin errores
		TProveedor dep1 = creaTProveedorRandom();
		TProveedor dep2 = creaTProveedorRandom();
		Integer dep1ID = asProveedor.altaProveedor(dep1);
		if (dep1ID <= 0)
			fail("Error: Alta es un prerequisito de este test");
		Integer dep2ID = asProveedor.altaProveedor(dep2);
		if (dep2ID <= 0)
			fail("Error: Alta es un prerequisito de este test");
		List<TProveedor> lista = asProveedor.listarProveedores();
		boolean dep1Found = false, dep2Found = false;
		for (TProveedor deps : lista) {
			if (deps.getId().equals(dep1ID)) {
				dep1Found = true;
			}
			if (deps.getId().equals(dep2ID)) {
				dep2Found = true;
			}
		}

		if (!dep1Found || !dep2Found)
			fail("Error: Listar deberia contener las entidades creadas. DEP1: " + dep1Found + ", DEP2: " + dep2Found);
	}
	
    @Test
    public void vincularTest() {
        // Crear proveedor
        TProveedor proveedor = creaTProveedorRandom();
        Integer idProveedor = asProveedor.altaProveedor(proveedor);
        if (idProveedor <= 0) {
            fail("Error: No se pudo dar de alta el proveedor");
        }

        // Crear marca de producto
        TMarcaProducto marcaProducto = new TMarcaProducto();
        marcaProducto.setNombre("ASMarcaProducto Marca " + random.nextInt());
        marcaProducto.setActivo(true);
        ASMarcaProducto asMarcaProducto = FactoriaSA.getInstance().getASMarcaProducto();
		Integer idMarca = asMarcaProducto.altaMarcaProducto(marcaProducto);
        if (idMarca <= 0) {
            fail("Error: No se pudo dar de alta la marca de producto");
        }

        // Vincular proveedor con marca de producto
        Integer resultado = asProveedor.vincularProveedorConMarcaDeProducto(new TProveedorConMarcas(idProveedor, idMarca));
        if (resultado <= 0) {
            fail("Error: No se pudo vincular el proveedor con la marca de producto");
        }

        // Verificar la vinculación
        List<TProveedor> proveedoresMarca = asProveedor.listarProveedoresPorMarcaDeProducto(idMarca);
        boolean proveedorEncontrado = false;
        for (TProveedor prov : proveedoresMarca) {
            if (prov.getId().equals(idProveedor)) {
                proveedorEncontrado = true;
                break;
            }
        }

        if (!proveedorEncontrado) {
            fail("Error: El proveedor no se vinculó correctamente con la marca de producto");
        }

        // Desvincular proveedor de la marca de producto
        Integer resultadoDesvinculacion = asProveedor.desvincularProveedorConMarcaDeProducto(new TProveedorConMarcas(idProveedor, idMarca));
        if (resultadoDesvinculacion <= 0) {
            fail("Error: No se pudo desvincular el proveedor de la marca de producto");
        }

        // Verificar la desvinculación
        proveedoresMarca = asProveedor.listarProveedoresPorMarcaDeProducto(idMarca);
        proveedorEncontrado = false;
        for (TProveedor prov : proveedoresMarca) {
            if (prov.getId().equals(idProveedor)) {
                proveedorEncontrado = true;
                break;
            }
        }

        if (proveedorEncontrado) {
            fail("Error: El proveedor no se desvinculó correctamente de la marca de producto");
        }

    }

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}