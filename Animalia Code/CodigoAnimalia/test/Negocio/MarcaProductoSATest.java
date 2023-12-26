package Negocio;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.MarcaProductoJPA.ASMarcaProducto;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Negocio.ProveedorJPA.ASProveedor;
import Negocio.ProveedorJPA.TProveedor;
import Negocio.ProveedorJPA.TProveedorConMarcas;

public class MarcaProductoSATest {

    private static ASMarcaProducto asMarcaProducto;
    private static Random random;

    @BeforeClass
    public static void beforeClass() {
        asMarcaProducto = FactoriaSA.getInstance().getASMarcaProducto();
        random = new Random();
    }

    public TMarcaProducto creaTMarcaProductoRandom() {
        TMarcaProducto marcaProducto = new TMarcaProducto();
        marcaProducto.setNombre("ASMarcaProducto marca " + random.nextInt());
        marcaProducto.setActivo(true);
        return marcaProducto;
    }

    @Test
    public void altaMarcaProducto() {
        TMarcaProducto marca = creaTMarcaProductoRandom();
        Integer res = asMarcaProducto.altaMarcaProducto(marca);
        marca.setId(res);
        if (res <= 0) // Alta marca producto correcto
            fail("Error: Alta marca producto sin errores debería retornar ID > 0 y retorna " + res);
        res = asMarcaProducto.altaMarcaProducto(marca);
        if (res != -143) // Alta marca producto entidad repetida activa
            fail("Error: Entidad repetida activa, debería devolver -1 y devuelve: " + res);
        asMarcaProducto.bajaMarcaProducto(marca.getId());
        res = asMarcaProducto.altaMarcaProducto(marca);
        if (res <= 0) // Alta marca producto reactivación
            fail("Error: Reactivar una entidad debería devolver ID > 0 y retorna " + res);
    }

    @Test
    public void bajaMarcaProducto() {
        TMarcaProducto marca = creaTMarcaProductoRandom();
        Integer res = asMarcaProducto.altaMarcaProducto(marca);
        Integer sol = asMarcaProducto.bajaMarcaProducto(res);
        if (sol < 0)
            fail("Error: Baja marca producto sin errores debería retornar ID >= 0 y retorna " + res);
        sol = asMarcaProducto.bajaMarcaProducto(res);
        if (sol != -142)
            fail("Error: Entidad ya dada de baja debería devolver -1 y devuelve: " + res);
        sol = asMarcaProducto.bajaMarcaProducto(-20);
        if (sol != -4)
            fail("Error: entidad inexistente debería devolver -1 y devuelve: " + res);
    }

    @Test
    public void mostrarMarcaProducto() {
        // Mostrar - marca producto inexistente
        TMarcaProducto res = asMarcaProducto.mostrarMarcaProducto(-1);
        if (res != null)
            fail("Error: Mostrar marca producto inexistente debería retornar null y retorna " + res);

        // Mostrar - sin error
        TMarcaProducto marca = creaTMarcaProductoRandom();
        Integer rdo = asMarcaProducto.altaMarcaProducto(marca);
        marca.setId(rdo);
        res = asMarcaProducto.mostrarMarcaProducto(rdo);
        if (res == null)
            fail("Error: Mostrar marca producto debería retornar una entidad sin errores.");
        
        if(!equals(res, marca))
        {
        	fail("Error: Mostrar marca producto debería devolver una entidad identica");
        }
    }

    @Test
    public void listarMarcaProducto() {
        // Listar - sin errores
        TMarcaProducto marca1 = creaTMarcaProductoRandom();
        TMarcaProducto marca2 = creaTMarcaProductoRandom();
        Integer marca1ID = asMarcaProducto.altaMarcaProducto(marca1);
        if (marca1ID <= 0)
            fail("Error: Alta es un prerequisito de este test");
        Integer marca2ID = asMarcaProducto.altaMarcaProducto(marca2);
        if (marca2ID <= 0)
            fail("Error: Alta es un prerequisito de este test");
        List<TMarcaProducto> lista = asMarcaProducto.listarMarcaProducto();
        boolean marca1Found = false, marca2Found = false;
        for (TMarcaProducto marcas : lista) {
            if (marcas.getId().equals(marca1ID)) {
                marca1Found = true;
            }
            if (marcas.getId().equals(marca2ID)) {
                marca2Found = true;
            }
        }

        if (!marca1Found || !marca2Found)
            fail("Error: Listar debería contener las entidades creadas. MARCA1: " + marca1Found + ", MARCA2: " + marca2Found);
    }

    @Test
    public void modificarMarcaProducto() {
        TMarcaProducto marca = creaTMarcaProductoRandom();
        Integer id = asMarcaProducto.altaMarcaProducto(marca);
        marca.setId(id);

        TMarcaProducto mmarca = creaTMarcaProductoRandom();
        mmarca.setId(id);
        marca.setNombre("ASMarcaProducto marca " + random.nextInt());

        Integer result = asMarcaProducto.modificarMarcaProducto(marca);

        if (result <= 0)
            fail("Error: Modificar marca producto debería devolver un ID válido y devuelve: " + result);
        
        TMarcaProducto modmarca = asMarcaProducto.mostrarMarcaProducto(id);
        
        if(!equals(marca, modmarca))
        {
        	fail("Error: Marca no ha sido modificada");
        }
    }
    

    @Test
    public void listarMarcaPorProveedorTest() {
        // Crear un proveedor con al menos una marca
        TProveedor proveedor = creaTProveedorRandom();
        ASProveedor asProveedor = FactoriaSA.getInstance().getProveedorSA();
        Integer idProveedor = asProveedor.altaProveedor(proveedor);
        if (idProveedor <= 0) {
            fail("Error: Alta proveedor sin errores debería retornar un ID válido y retorna " + idProveedor);
        }

        Integer idMarca = asMarcaProducto.altaMarcaProducto(creaTMarcaProductoRandom());
        
        asProveedor.vincularProveedorConMarcaDeProducto(new TProveedorConMarcas(idProveedor, idMarca));
        
        // Listar marcas por proveedor
        List<TMarcaProducto> marcas = asMarcaProducto.listarMarcaPorProveedor(idProveedor);

        // Verificar que la lista no es nula y contiene al menos una marca
        if (marcas == null) {
            fail("Error: La lista de marcas no debería ser nula");
        }
        if (marcas.isEmpty()) {
            fail("Error: Debería haber al menos una marca asociada al proveedor");
        }
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
    
    private boolean equals(TMarcaProducto a, TMarcaProducto b) {
        boolean igual = a.getId().equals(b.getId()) &&
                a.getNombre().equals(b.getNombre()) &&
                a.getActivo() == b.getActivo();

        return igual;
    }
}
