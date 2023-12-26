package Negocio;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.Especie.TEspecie;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Habitat.THabitat;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Negocio.ProductoJPA.ASProducto;
import Negocio.ProductoJPA.TAlimentacion;
import Negocio.ProductoJPA.TMerchandising;
import Negocio.ProductoJPA.TProducto;

public class ProductoSATest {
	private static ASProducto asProducto;
	private static Random random;

	@BeforeClass
	public static void beforeClass() {
		asProducto = FactoriaSA.getInstance().getProductoSA();
		random = new Random();
	}

	public TAlimentacion creaTAlimentacionRandom() {
		TAlimentacion producto = new TAlimentacion();
		producto.setNombre("alimentacion");
		producto.setActivo(true);
		producto.setIdMarcaProducto(1);
		producto.setPrecio(random.nextDouble());
		producto.setStock(random.nextInt());
		producto.setTipoAl("FRUTA");
		return producto;
	}

	public TMerchandising creaTMerchandisingRandom() {
		TMerchandising producto = new TMerchandising();
		producto.setNombre("merch");
		producto.setActivo(true);
		producto.setIdMarcaProducto(1);
		producto.setPrecio(random.nextDouble());
		producto.setStock(random.nextInt());
		producto.setCategoria("helados");
		producto.setEdicionLimitada("oro");
		return producto;
	}
	@Test
	public void altaProducto() {
		TProducto producto = creaTAlimentacionRandom();
		Integer res = asProducto.altaProducto(producto);
		if (res <= 0)
			fail("Error: Alta producto sin errores deberia retornar ID > 0 y retorna " + res);
		res = asProducto.altaProducto(producto);
		
		
		TProducto producto2 = creaTMerchandisingRandom();
		Integer res2 = asProducto.altaProducto(producto2);
		if (res2 <= 0)
			fail("Error: Alta producto sin errores deberia retornar ID > 0 y retorna " + res2);
		res = asProducto.altaProducto(producto2);
		
	}
	
	
	@Test
	public void bajaProducto() {
		Integer res = asProducto.altaProducto(new TAlimentacion(3,"alimentacionBaja", 100.0, 50, "baja",true, 1));
		if (res <= 0)
			fail("Error: Alta producto sin errores deberia retornar ID > 0 y retorna " + res);
		res = asProducto.bajaProducto(res);
		if (res <= 0)
			fail("Error: Baja producto sin errores deberia retornar ID >= 0 y retorna " + res);
		res = asProducto.bajaProducto(-2154);
		if (res != -1)
			fail("Error: entidad inexistente deberia devolver -1 y devuelve: " + res);
		
		Integer res2 = asProducto.altaProducto(new TMerchandising(4,"merchBaja", 100.0, 50, "baja", "bajaMerch",true, 1));
		if (res2 <= 0)
			fail("Error: Baja producto sin errores deberia retornar ID >= 0 y retorna " + res2);
		res2 = asProducto.bajaProducto(res2);
		if (res2 <= 0)
			fail("Error: Entidad ya dada de baja deberia devolver -1 y devuelve: " + res2);
		res2 = asProducto.bajaProducto(-2154);
		if (res2 != -1)
			fail("Error: entidad inexistente deberia devolver -1 y devuelve: " + res2);
	}
	
    @Test
    public void modificarProducto() {
        Integer id = asProducto.altaProducto(new TAlimentacion(5,"alimentacionModificar", 100.0, 50, "modificar",true, 1));
		if (id <= 0)
			fail("Error: Baja producto sin errores deberia retornar ID >= 0 y retorna " + id);

        TAlimentacion mal = new TAlimentacion(id,"alimentacionModificar", 200.0, 20, "modificar2",true, 1);

        Integer result = asProducto.modificarProducto(mal);

        if (result <= 0)
            fail("Error: Modificar producto debería devolver un ID válido y devuelve: " + result);
	
    }
    
    
	@Test
	public void mostrarTest() {
		// Mostrar - producto inexistente
		TProducto res = asProducto.mostrarProducto(-1);
		if (res != null)
			fail("Error: Mostrar producto inexsitente deberia retornar null y retorna " + res);

		// Mostrar - sin error
		Integer rdo = asProducto.altaProducto(new TAlimentacion(7,"alimentacionMostrar", 100.0, 50, "mostrar",true, 1));
		res = asProducto.mostrarProducto(rdo);
		if (res == null)
			fail("Error: Mostrar producto deberia retornar una entidad sin errores.");
		Integer rdo2 = asProducto.altaProducto(new TMerchandising(8,"merchMostrar", 100.0, 50, "mostrar","mostrarMerch",true, 1));
		res = asProducto.mostrarProducto(rdo2);
		if (res == null)
			fail("Error: Mostrar producto deberia retornar una entidad sin errores.");

	}

	@Test
	public void listarTest() {
		// Listar - sin errores
		Integer prod1ID =asProducto.altaProducto(new TAlimentacion(9,"alimentacionListar", 100.0, 50, "listar",true, 1));
		if (prod1ID <= 0)
			fail("Error: Alta es un prerequisito de este test");
		Integer prod2ID = asProducto.altaProducto(new TMerchandising(10,"merchListar", 100.0, 50, "listar","listarMerch",true, 1));
		if (prod2ID <= 0)
			fail("Error: Alta es un prerequisito de este test");
		List<TProducto> lista = asProducto.listarProductos();
		boolean prod1Found = false, prod2Found = false;
		for (TProducto prods : lista) {
			if (prods.getID().equals(prod1ID)) {
				prod1Found = true;
			}
			if (prods.getID().equals(prod2ID)) {
				prod2Found = true;
			}
		}

		if (!prod1Found || !prod2Found)
			fail("Error: Listar deberia contener las entidades creadas. PROD1: " + prod1Found + ", PROD2: " + prod2Found);
	}
	
}
