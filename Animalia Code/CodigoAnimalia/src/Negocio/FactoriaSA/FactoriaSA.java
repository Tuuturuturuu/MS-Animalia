package Negocio.FactoriaSA;

import Negocio.Empleado.EmpleadoSA;
import Negocio.Habitat.HabitatSA;
import Negocio.MarcaProductoJPA.ASMarcaProducto;
import Negocio.Pase.PaseSA;
import Negocio.ProductoJPA.ASProducto;
import Negocio.ProveedorJPA.ASProveedor;
import Negocio.ProveedorJPA.ASProveedorImp;
import Negocio.TrabajadorJPA.ASTrabajador;
import Negocio.VentaJPA.ASVenta;
import Negocio.Factura.FacturaSA;
import Negocio.Especie.EspecieSA;
import Negocio.Animal.AnimalSA;
import Negocio.DepartamentoJPA.ASDepartamento;

public abstract class FactoriaSA {

	private static FactoriaSA instance;
	
	public static synchronized FactoriaSA getInstance() {
		if(instance == null) 
			instance = new FactoriaSAImp();
		return instance;
	}

	public abstract EmpleadoSA getEmpleadoSA();

	public abstract HabitatSA getHabitatSA();

	public abstract PaseSA getPaseSA();

	public abstract FacturaSA getFacturaSA();

	public abstract EspecieSA getEspecieSA();

	public abstract AnimalSA getAnimalSA();
	
	public abstract ASVenta getASVenta();
	
	public abstract ASDepartamento getASDepartamento();
	
	public abstract ASTrabajador getTrabajadorSA();
	
	public abstract ASProveedor getProveedorSA();

	public abstract ASMarcaProducto getASMarcaProducto();
	
	public abstract ASProducto getProductoSA();

}