package Presentacion.FactoriaVistas;

import java.util.List;
import java.util.Set;

import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Negocio.Animal.TAnimalNoAcuatico;
import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;
import Negocio.Especie.TEspecie;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConPases;
import Negocio.Habitat.THabitat;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Negocio.Pase.TPase;
import Negocio.ProductoJPA.TProducto;
import Negocio.ProveedorJPA.TProveedor;
import Negocio.TrabajadorJPA.TTrabajador;
import Negocio.VentaJPA.TCarritoJPA;
import Negocio.VentaJPA.TVenta;
import Negocio.VentaJPA.TVentaConProductos;
import Presentacion.Controller.Command.Context;
import Presentacion.Departamento.VAltaDepartamento;
import Presentacion.Departamento.VBajaDepartamento;
import Presentacion.Departamento.VCalcularNominaDepartamento;
import Presentacion.Departamento.VDepartamento;
import Presentacion.Departamento.VListarDepartamento;
import Presentacion.Departamento.VModificarDepartamento;
import Presentacion.Departamento.VMostrarDepartamento;
import Presentacion.Empleado.VAltaEmpleado;
import Presentacion.Empleado.VAltaEmpleadoLimpieza;
import Presentacion.Empleado.VAltaEmpleadoZoologico;
import Presentacion.Empleado.VBajaEmpleado;
import Presentacion.Empleado.VEmpleado;
import Presentacion.Empleado.VListarEmpleado;
import Presentacion.Empleado.VListarEmpleadosLimpieza;
import Presentacion.Empleado.VListarEmpleadosPorEspecieEnHabitat;
import Presentacion.Empleado.VListarEmpleadosZoologico;
import Presentacion.Empleado.VListarPorHabitatEmpleado;
import Presentacion.Empleado.VModificarEmpleado;
import Presentacion.Empleado.VMostrarEmpleado;
import Presentacion.Empleado.VMostrarEmpleadoPorId;
import Presentacion.Evento;
import Presentacion.MainView;
import Presentacion.MainViewJPA;
import Presentacion.Animal.VMostrarAnimalPorIdEspecie;
import Presentacion.Especie.VMostrarEspeciePorIdHabitat;
import Presentacion.Pase.VMostrarPasePorIdHabitat;
import Presentacion.VMostrarUno;
import Presentacion.vConfirmDialog;
import Presentacion.vConfirmDouble;
import Presentacion.vFailureDialog;
import Presentacion.Animal.VAltaAnimal;
import Presentacion.Animal.VAltaAnimalAcuatico;
import Presentacion.Animal.VAltaAnimalNoAcuatico;
import Presentacion.Animal.VAnimal;
import Presentacion.Animal.VBajaAnimal;
import Presentacion.Animal.VListarAnimales;
import Presentacion.Animal.VListarAnimalesAcuaticos;
import Presentacion.Animal.VListarAnimalesNoAcuaticos;
import Presentacion.Animal.VListarAnimalesPorEspecie;
import Presentacion.Animal.VModificarAnimal;
import Presentacion.Animal.VModificarAnimalAcuatico;
import Presentacion.Animal.VModificarAnimalNoAcuatico;
import Presentacion.Animal.VMostrarAnimal;
import Presentacion.Especie.VAltaEspecie;
import Presentacion.Especie.VBajaEspecie;
import Presentacion.Especie.VEspecie;
import Presentacion.Especie.VListarEspecies;
import Presentacion.Especie.VListarEspeciesPorHabitat;
import Presentacion.Especie.VModificarEspecie;
import Presentacion.Especie.VMostrarEspecie;
import Presentacion.Factura.VAbrirFactura;
import Presentacion.Factura.VAnadirPaseAFactura;
import Presentacion.Factura.VCerrarFactura;
import Presentacion.Factura.VConfirmDevolverFactura;
import Presentacion.Factura.VDevolverFactura;
import Presentacion.Factura.VFactura;
import Presentacion.Factura.VFailureDialogFactura;
import Presentacion.Factura.VListarFacturas;
import Presentacion.Factura.VModificarFactura;
import Presentacion.Factura.VMostrarFactura;
import Presentacion.Factura.VMostrarFacturaConPases;
import Presentacion.Factura.VQuitarPaseDeFactura;
import Presentacion.Factura.claseContenedorErroresFactura;
import Presentacion.Habitat.VAltaHabitat;
import Presentacion.Habitat.VBajaHabitat;
import Presentacion.Habitat.VCalcularHabitatMasIngresos;
import Presentacion.Habitat.VDesvincularEmpleadoHabitat;
import Presentacion.Habitat.VHabitat;
import Presentacion.Habitat.VListarHabitat;
import Presentacion.Habitat.VListarHabitatPorEmpleado;
import Presentacion.Habitat.VModificarHabitat;
import Presentacion.Habitat.VMostrarHabitat;
import Presentacion.Habitat.VMostrarHabitatPorIdEmpleado;
import Presentacion.Habitat.VVincularEmpleadoHabitat;
import Presentacion.MarcaProductoJPA.VAltaMarcaProductoJPA;
import Presentacion.MarcaProductoJPA.VBajaMarcaProductoJPA;
import Presentacion.MarcaProductoJPA.VListarMarcaProductoJPA;
import Presentacion.MarcaProductoJPA.VListarMarcasPorProveedorJPA;
import Presentacion.MarcaProductoJPA.VMarcaProducto;
import Presentacion.MarcaProductoJPA.VModificarMarcaProductoJPA;
import Presentacion.MarcaProductoJPA.VMostrarMarcaProductoJPA;
import Presentacion.Pase.VAltaPase;
import Presentacion.Pase.VBajaPase;
import Presentacion.Pase.VListarPases;
import Presentacion.Pase.VListarPasesPorHabitat;
import Presentacion.Pase.VModificarPase;
import Presentacion.Pase.VMostrarPase;
import Presentacion.Pase.VPase;
import Presentacion.ProductoJPA.VAltaProductoJPA;
import Presentacion.ProductoJPA.VProducto;
import Presentacion.ProveedorJPA.VAltaProveedorJPA;
import Presentacion.ProveedorJPA.VBajaProveedorJPA;
import Presentacion.ProveedorJPA.VDesvincularProveedorConMarcaDeProducto;
import Presentacion.ProveedorJPA.VListarProveedoresJPA;
import Presentacion.ProveedorJPA.VListarProveedoresPorMarcaDeProducto;
import Presentacion.ProveedorJPA.VModificarProveedorJPA;
import Presentacion.ProveedorJPA.VMostrarProveedorJPA;
import Presentacion.ProveedorJPA.VProveedorJPA;
import Presentacion.ProveedorJPA.VVincularProveedorConMarcaDeProducto;
import Presentacion.TrabajadorJPA.VAltaTrabajadorJPA;
import Presentacion.TrabajadorJPA.VBajaTrabajadorJPA;
import Presentacion.TrabajadorJPA.VListarTrabajadoresJPA;
import Presentacion.TrabajadorJPA.VListarTrabajadoresPorDepartamentoJPA;
import Presentacion.TrabajadorJPA.VModificarTrabajadorJPA;
import Presentacion.TrabajadorJPA.VMostrarTrabajadorJPA;
import Presentacion.TrabajadorJPA.VTrabajador;
import Presentacion.VentaJPA.VAbrirVentaJPA;
import Presentacion.VentaJPA.VAnadirProductoAVentaJPA;
import Presentacion.VentaJPA.VCerrarVentaIDTrabajador;
import Presentacion.VentaJPA.VCerrarVentaJPA;
import Presentacion.VentaJPA.VConfirmDevolverVenta;
import Presentacion.VentaJPA.VDevolucionVentaJPA;
import Presentacion.VentaJPA.VListarVentasJPA;
import Presentacion.VentaJPA.VListarVentasPorTrabajadorJPA;
import Presentacion.VentaJPA.VModificarVentaJPA;
import Presentacion.VentaJPA.VMostrarVentaConProductos;
import Presentacion.VentaJPA.VMostrarVentaJPA;
import Presentacion.VentaJPA.VQuitarProductoDeVentaJPA;
import Presentacion.VentaJPA.VVenta;
import Presentacion.TrabajadorJPA.VCalcularSueldoTrabajadorJPA;
import Presentacion.ProductoJPA.VModificarProductoJPA;
import Presentacion.ProductoJPA.VMostrarProductoJPA;
import Presentacion.ProductoJPA.VBajaProductoJPA;
import Presentacion.ProductoJPA.VListarProductosJPA;
import Presentacion.ProductoJPA.VListarProductosPorMarcaJPA;


public class FactoriaVistasImp extends FactoriaVistas {
	IGUI curIGUI = null;

	@SuppressWarnings("unchecked")
	public IGUI generarVista(Context context) {
		switch(context.getEvento())
		{
		//VISTA GENERAL
			case Evento.CREAR_MAIN_VIEW:
				curIGUI = new MainView();
				return curIGUI;
		//VISTA GENERAL JPA
			case Evento.CREAR_VTIENDA:
				return new MainViewJPA();
		//VISTAS PASE
			case Evento.CREAR_VPASE:
				curIGUI = new VPase();
				return curIGUI;
			case Evento.VALTA_PASE:
				curIGUI = new VAltaPase();
				return curIGUI;
			case Evento.VBAJA_PASE:
				curIGUI = new VBajaPase();
				return curIGUI;
			case Evento.VMODIFICAR_PASE:
				curIGUI = new VModificarPase();
				return curIGUI;
			case Evento.VMOSTRAR_PASE:
				curIGUI = new VMostrarPase();
				return curIGUI;
			case Evento.LISTAR_PASES:
                curIGUI = new VListarPases((Set<TPase>) context.getDatos());
				return curIGUI;
			case Evento.VLISTAR_PASE_POR_HABITAT:
				curIGUI = new VListarPasesPorHabitat();
				return curIGUI;
			case Evento.V_LISTAR_PASE_POR_ID_HABITAT:
                curIGUI = new VMostrarPasePorIdHabitat((Set<TPase>)context.getDatos());
                return curIGUI;


		// VISTAS ANIMAL
		case Evento.CREAR_VANIMAL:
			curIGUI = new VAnimal();
			return curIGUI;
		case Evento.VALTA_ANIMAL:
			curIGUI = new VAltaAnimal();
			return curIGUI;
		case Evento.VALTA_ANIMAL_ACUATICO:
			curIGUI = new VAltaAnimalAcuatico((TAnimalAcuatico) context.getDatos());
			return curIGUI;
		case Evento.VALTA_ANIMAL_NO_ACUATICO:
			curIGUI = new VAltaAnimalNoAcuatico((TAnimalNoAcuatico) context.getDatos());
			return curIGUI;
		case Evento.VBAJA_ANIMAL:
			curIGUI = new VBajaAnimal();
			return curIGUI;
		case Evento.VMODIFICAR_ANIMAL:
			curIGUI = new VModificarAnimal();
			return curIGUI;
		case Evento.VMODIFICAR_ANIMAL_ACUATICO:
			curIGUI = new VModificarAnimalAcuatico((TAnimalAcuatico) context.getDatos());
			return curIGUI;
		case Evento.VMODIFICAR_ANIMAL_NO_ACUATICO:
			curIGUI = new VModificarAnimalNoAcuatico((TAnimalNoAcuatico) context.getDatos());
			return curIGUI;
		case Evento.VMOSTRAR_ANIMAL:
			curIGUI = new VMostrarAnimal();
			return curIGUI;
		case Evento.VLISTAR_ANIMALES:
			curIGUI = new VListarAnimales((Set<TAnimal>) context.getDatos());
			return curIGUI;
		case Evento.VLISTAR_ANIMALES_NO_ACUATICOS:
			curIGUI = new VListarAnimalesNoAcuaticos((Set<TAnimalNoAcuatico>) context.getDatos());
			return curIGUI;
		case Evento.VLISTAR_ANIMALES_ACUATICOS:
			curIGUI = new VListarAnimalesAcuaticos((Set<TAnimalAcuatico>) context.getDatos());
			return curIGUI;
		case Evento.VLISTAR_ANIMALES_POR_ESPECIE:
			curIGUI = new VListarAnimalesPorEspecie();
			return curIGUI;
		case Evento.V_LISTAR_ANIMAL_POR_ID_ESPECIE:
			curIGUI = new VMostrarAnimalPorIdEspecie((Set<TAnimal>) context.getDatos());
			return curIGUI;
			
	
			
		// VISTAS HABITAT
		case Evento.CREAR_VHABITAT:
			curIGUI = new VHabitat();
			return curIGUI;
		case Evento.VALTA_HABITAT:
			curIGUI = new VAltaHabitat();
			return curIGUI;
		case Evento.VBAJA_HABITAT:
			curIGUI = new VBajaHabitat();
			return curIGUI;
		case Evento.VMODIFICAR_HABITAT:
			curIGUI = new VModificarHabitat();
			return curIGUI;
		case Evento.VMOSTRAR_HABITAT:
			curIGUI = new VMostrarHabitat();
			return curIGUI;
		case Evento.VLISTAR_HABITAT:
			curIGUI = new VListarHabitat((Set<THabitat>) context.getDatos());
			return curIGUI;
		case Evento.VVINCULAR_EMPLEADO_A_HABITAT:
			curIGUI = new VVincularEmpleadoHabitat();
			return curIGUI;
		case Evento.VDESVINCULAR_EMPLEADO_DE_HABITAT:
			curIGUI = new VDesvincularEmpleadoHabitat();
			return curIGUI;
		case Evento.VLISTAR_HABITATS_POR_EMPLEADO:
			curIGUI = new VListarHabitatPorEmpleado();
			return curIGUI;
		case Evento.VCALCULAR_HABITAT_CON_MAS_INGRESOS:
			curIGUI = new VCalcularHabitatMasIngresos();
			return curIGUI;
		case Evento.V_LISTAR_HABITAT_POR_ID_EMPLEADO:
			curIGUI = new VMostrarHabitatPorIdEmpleado((Set<THabitat>) context.getDatos());
			return curIGUI;
			
		// VISTAS ESPECIE
		case Evento.CREAR_VESPECIE:
			curIGUI = new VEspecie();
			return curIGUI;
		case Evento.VALTA_ESPECIE:
			curIGUI = new VAltaEspecie();
			return curIGUI;
		case Evento.VBAJA_ESPECIE:
			curIGUI = new VBajaEspecie();
			return curIGUI;
		case Evento.VMODIFICAR_ESPECIE:
			curIGUI = new VModificarEspecie();
			return curIGUI;
		case Evento.VMOSTRAR_ESPECIE:
			curIGUI = new VMostrarEspecie();
			return curIGUI;
		case Evento.LISTAR_ESPECIES:
			curIGUI = new VListarEspecies((Set<TEspecie>) context.getDatos());
			return curIGUI;
		case Evento.VLISTAR_ESPECIE_POR_HABITAT:
			curIGUI = new VListarEspeciesPorHabitat();
			return curIGUI;

		// VISTAS FACTURA
		case Evento.CREAR_VFACTURA:
			curIGUI = new VFactura();
			return curIGUI;
		case Evento.ABRIR_FACTURA:
			curIGUI = new VAbrirFactura();
			return curIGUI;
		case Evento.VDEVOLVER_FACTURA:
			curIGUI = new VDevolverFactura();
			return curIGUI;
		case Evento.VMODIFICAR_FACTURA:
			curIGUI = new VModificarFactura();
			return curIGUI;
		case Evento.VMOSTRAR_FACTURA:
			curIGUI = new VMostrarFactura();
			return curIGUI;
		case Evento.LISTAR_FACTURAS:
			curIGUI = new VListarFacturas((Set<TFactura>) context.getDatos());
			return curIGUI;
		case Evento.ANADIR_PASE_A_FACTURA:
			curIGUI = new VAnadirPaseAFactura();
			return curIGUI;
		case Evento.QUITAR_PASE_DE_FACTURA:
			curIGUI = new VQuitarPaseDeFactura();
			return curIGUI;
		case Evento.VCERRAR_FACTURA:
			curIGUI = new VCerrarFactura((TCarrito) context.getDatos());
			return curIGUI;
			
			
			
		// VISTA EMPLEADO
			case Evento.CREAR_VEMPLEADO:
				curIGUI = new VEmpleado();
				return curIGUI;
			case Evento.VALTA_EMPLEADO:
		        curIGUI = new VAltaEmpleado();
		        return curIGUI;
			case Evento.VALTA_EMPLEADO_LIMPIEZA:
				curIGUI = new VAltaEmpleadoLimpieza((TEmpleadoLimpieza) context.getDatos());
				return curIGUI;
			case Evento.VALTA_EMPLEADO_ZOOLOGICO:
				curIGUI = new VAltaEmpleadoZoologico((TEmpleadoZoologico) context.getDatos());
				return curIGUI;
		    case Evento.VBAJA_EMPLEADO:
		        curIGUI = new VBajaEmpleado();
		        return curIGUI;
		    case Evento.VMOSTRAR_EMPLEADO:
		        curIGUI = new VMostrarEmpleado();
		        return curIGUI;
		    case Evento.VMODIFICAR_EMPLEADO:
		        curIGUI = new VModificarEmpleado();
		        return curIGUI;
		    case Evento.VLISTAR_EMPLEADO:
		        curIGUI = new VListarEmpleado((Set<TEmpleado>) context.getDatos());
		        return curIGUI;
		    case Evento.VLISTAR_EMPLEADOS_LIMPIEZA:
		        curIGUI = new VListarEmpleadosLimpieza((Set<TEmpleadoLimpieza>) context.getDatos());
		        return curIGUI;
		    case Evento.VLISTAR_EMPLEADOS_ZOOLOGICO:
		        curIGUI = new VListarEmpleadosZoologico((Set<TEmpleadoZoologico>) context.getDatos());
		        return curIGUI;
		    case Evento.VLISTAR_EMPLEADO_POR_HABITAT:
		        curIGUI = new VListarPorHabitatEmpleado();
		        return curIGUI;
		    case Evento.VLISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT:
		        curIGUI = new VListarEmpleadosPorEspecieEnHabitat();
		        return curIGUI;
			case Evento.V_LISTAR_EMPLEADO_POR_ID:
				curIGUI = new VMostrarEmpleadoPorId((Set<TEmpleado>) context.getDatos());
				return curIGUI;
				
				//VISTAS JPA
				
				// VISTAS PROVEEDOR
			case Evento.CREAR_VPROVEEDOR:
				return new VProveedorJPA();
			case Evento.VALTA_PROVEEDOR:
				return new VAltaProveedorJPA();
			case Evento.VBAJA_PROVEEDOR:
				return new VBajaProveedorJPA();
			case Evento.VMODIFICAR_PROVEEDOR:
				return new VModificarProveedorJPA();
			case Evento.VMOSTRAR_PROVEEDOR:
				return new VMostrarProveedorJPA();
			case Evento.VLISTAR_PROVEEDORES:
				return new VListarProveedoresJPA((List<TProveedor>) context.getDatos());
			case Evento.VVINCULAR_PROVEEDOR_MARCA:
				return new VVincularProveedorConMarcaDeProducto();
			case Evento.VDESVINCULAR_PROVEEDOR_MARCA:
				return new VDesvincularProveedorConMarcaDeProducto();
			case Evento.VLISTAR_PROVEEDORES_POR_MARCA:
				return new VListarProveedoresPorMarcaDeProducto();
			case Evento.V_LISTAR_PROVEEDOR_POR_ID_MARCA:
				return new VListarProveedoresJPA((List<TProveedor>) context.getDatos());
				
			// VISTAS PRODUCTO
		case Evento.CREAR_VPRODUCTO:
			return new VProducto();
		case Evento.VALTA_PRODUCTO:
			return new VAltaProductoJPA();
		case Evento.VBAJA_PRODUCTO:
			return new VBajaProductoJPA();
		case Evento.VMODIFICAR_PRODUCTO:
			return new VModificarProductoJPA();
		case Evento.VMOSTRAR_PRODUCTO:
			return new VMostrarProductoJPA();
		case Evento.VLISTAR_PRODUCTO:
			return new VListarProductosJPA((List<TProducto>) context.getDatos());
		case Evento.VLISTAR_PRODUCTOS_POR_MARCA:
			return new VListarProductosPorMarcaJPA();
		case Evento.VLISTAR_PRODUCTO_POR_ID_MARCA:
			return new VListarProductosJPA((List<TProducto>) context.getDatos());

			
			//VISTAS MARCA
		case Evento.CREAR_VMARCA:
			return new VMarcaProducto();
		case Evento.VALTA_MARCA:
			return new VAltaMarcaProductoJPA();
		case Evento.VBAJA_MARCA:
			return new VBajaMarcaProductoJPA();
		case Evento.VMODIFICAR_MARCA:
			return new VModificarMarcaProductoJPA();
		case Evento.VMOSTRAR_MARCA:
			return new VMostrarMarcaProductoJPA();
		case Evento.VLISTAR_MARCA:
			return new VListarMarcaProductoJPA((List<TMarcaProducto>) context.getDatos());
		case Evento.VLISTAR_MARCAS_POR_PROVEEDOR:
			return new VListarMarcasPorProveedorJPA();
			
			
		
			
			// VISTAS VENTA
		case Evento.CREAR_VVENTA:
			return new VVenta();
			
		case Evento.ABRIR_VENTA:
			return new VAbrirVentaJPA();
		case Evento.VCERRAR_VENTA:
			curIGUI =  new VCerrarVentaJPA((TCarritoJPA) context.getDatos());
			return curIGUI;
		case Evento.VCERRAR_VENTA_IDTRABAJADOR:
			return new VCerrarVentaIDTrabajador();
		case Evento.VMOSTRAR_VENTA:
			return new VMostrarVentaJPA();
		case Evento.MOSTRAR_VENTA_COMPLETA:
			curIGUI = new VMostrarVentaConProductos((TVentaConProductos) context.getDatos());
			return curIGUI;
		case Evento.LISTAR_VENTAS:
			return new VListarVentasJPA((List<TVenta>) context.getDatos());
		case Evento.VDEVOLVER_VENTA:
			return new VDevolucionVentaJPA();
		case Evento.ANADIR_PRODUCTO_A_VENTA:
			return new VAnadirProductoAVentaJPA();
		case Evento.QUITAR_PRODUCTO_DE_VENTA:
			return new VQuitarProductoDeVentaJPA();
		case Evento.VMODIFICAR_VENTA:
			return new VModificarVentaJPA();
		case Evento.V_LISTAR_VENTAS_POR_TRABAJADOR:
			return new VListarVentasPorTrabajadorJPA();
		case Evento.V_LISTAR_VENTA_POR_IDTRABAJADOR:
			return new VListarVentasJPA((List<TVenta>) context.getDatos());
		
		//VISTAS TRABAJADOR
		case Evento.CREAR_VTRABAJADOR:
		    return new VTrabajador();
		case Evento.VALTA_TRABAJADOR:
		    return new VAltaTrabajadorJPA();
		case Evento.VBAJA_TRABAJADOR:
		    return new VBajaTrabajadorJPA();
		case Evento.VMODIFICAR_TRABAJADOR:
		    return new VModificarTrabajadorJPA();
		case Evento.VMOSTRAR_TRABAJADOR:
		    return new VMostrarTrabajadorJPA();
		case Evento.VLISTAR_TRABAJADOR:
		    return new VListarTrabajadoresJPA((List<TTrabajador>) context.getDatos());
		case Evento.VLISTAR_TRABAJADORES_POR_DEPARTAMENTO:
		    return new VListarTrabajadoresPorDepartamentoJPA();
		case Evento.VCALCULAR_SUELDO_TRABAJADOR:
		    return new VCalcularSueldoTrabajadorJPA();

			
	    //VISTAS DEPARTAMENTO
	     case Evento.CREAR_VDEPARTAMENTO:
	      	return new VDepartamento();
	     case Evento.VALTA_DEPARTAMENTO:
	      	return new VAltaDepartamento();
	     case Evento.VBAJA_DEPARTAMENTO:
	    	return new VBajaDepartamento();
      	 case Evento.VMODIFICAR_DEPARTAMENTO:
      	    return new VModificarDepartamento();
      	 case Evento.VMOSTRAR_DEPARTAMENTO:
      	    return new VMostrarDepartamento();
      	 case Evento.VLISTAR_DEPARTAMENTO:
      	    return new VListarDepartamento((List<TDepartamento>) context.getDatos());
      	 case Evento.VCALCULAR_NOMINA_DEPARTAMENTO:
      	    return new VCalcularNominaDepartamento(); 
				
		// VISTA ERRORES
		// VISTAS GENERALES
		case Evento.V_ERRORES:
			curIGUI = new vFailureDialog((int) context.getDatos());
			return curIGUI;
		case Evento.V_CORRECTO:
			curIGUI = new vConfirmDialog((int) context.getDatos());
			return curIGUI;
		case Evento.V_MOSTRAR_UNO:
			curIGUI = new VMostrarUno((String) context.getDatos());
			return curIGUI;
		case Evento.V_LISTAR_ESPECIE_POR_ID_HABITAT:
			curIGUI = new VMostrarEspeciePorIdHabitat((Set<TEspecie>) context.getDatos());
			return curIGUI;
		case Evento.V_ERRORES_FACTURA_VISTA:
			curIGUI = new VFailureDialogFactura((claseContenedorErroresFactura) context.getDatos());
			return curIGUI;
		case Evento.V_MOSTRAR_FACTURA_COMPLETA:
			curIGUI = new VMostrarFacturaConPases((TFacturaConPases) context.getDatos());
			return curIGUI;
		case Evento.V_DEVOLVER_FACTURA_CORRECT:
			curIGUI = new VConfirmDevolverFactura();
			return curIGUI;
		case Evento.V_DEVOLVER_VENTA_CORRECT:
			curIGUI = new VConfirmDevolverVenta();
			return curIGUI;
			
		case Evento.V_CORRECT_RESULTADO:
			curIGUI = new vConfirmDouble((double) context.getDatos());
			return curIGUI;

		}
		return null;
	}
}