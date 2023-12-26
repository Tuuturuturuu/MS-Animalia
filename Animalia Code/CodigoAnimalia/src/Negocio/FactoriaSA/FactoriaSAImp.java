package Negocio.FactoriaSA;

import Negocio.Animal.AnimalSA;
import Negocio.Animal.AnimalSAImp;
import Negocio.DepartamentoJPA.ASDepartamento;
import Negocio.DepartamentoJPA.ASDepartamentoImp;
import Negocio.Empleado.EmpleadoSA;
import Negocio.Empleado.EmpleadoSAImp;
import Negocio.Especie.EspecieSA;
import Negocio.Especie.EspecieSAImp;
import Negocio.Factura.FacturaSA;
import Negocio.Factura.FacturaSAImp;
import Negocio.Habitat.HabitatSA;
import Negocio.Habitat.HabitatSAImp;
import Negocio.MarcaProductoJPA.ASMarcaProducto;
import Negocio.MarcaProductoJPA.ASMarcaProductoImp;
import Negocio.Pase.PaseSA;
import Negocio.Pase.PaseSAImp;
import Negocio.ProductoJPA.ASProducto;
import Negocio.ProductoJPA.ASProductoImp;
import Negocio.ProveedorJPA.ASProveedor;
import Negocio.ProveedorJPA.ASProveedorImp;
import Negocio.TrabajadorJPA.ASTrabajador;
import Negocio.TrabajadorJPA.ASTrabajadorImp;
import Negocio.VentaJPA.ASVenta;
import Negocio.VentaJPA.ASVentaImp;

public class FactoriaSAImp extends FactoriaSA {

	public EmpleadoSA getEmpleadoSA() {
		return new EmpleadoSAImp();
	}

	public HabitatSA getHabitatSA() {
		return new HabitatSAImp();
	}

	public PaseSA getPaseSA() {
		return new PaseSAImp();
	}

	public FacturaSA getFacturaSA() {
		return new FacturaSAImp();
	}

	public EspecieSA getEspecieSA() {
		return new EspecieSAImp();
	}

	public AnimalSA getAnimalSA() {
		return new AnimalSAImp();
	}
	
	public ASVenta getASVenta() {
		return new ASVentaImp();
	}

	public ASDepartamento getASDepartamento() {
		return new ASDepartamentoImp();
	}
	
	@Override
	public ASTrabajador getTrabajadorSA() {
		return new ASTrabajadorImp();
	}

	public ASProveedor getProveedorSA() {
		return new ASProveedorImp();
	}
	
	public ASMarcaProducto getASMarcaProducto(){
		return new ASMarcaProductoImp();
	}
	public ASProducto getProductoSA(){
		return new ASProductoImp();
	}
	
}