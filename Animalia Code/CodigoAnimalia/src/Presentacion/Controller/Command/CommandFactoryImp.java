package Presentacion.Controller.Command;

import Presentacion.Evento;
import Presentacion.Controller.Command.CommandAnimal.AltaAnimalAcuaticoCommand;
import Presentacion.Controller.Command.CommandAnimal.AltaAnimalNoAquaticoCommand;
import Presentacion.Controller.Command.CommandAnimal.BajaAnimalCommand;
import Presentacion.Controller.Command.CommandAnimal.ListarAnimalesAcuaticosCommand;
import Presentacion.Controller.Command.CommandAnimal.ListarAnimalesCommand;
import Presentacion.Controller.Command.CommandAnimal.ListarAnimalesNoAcuaticosCommand;
import Presentacion.Controller.Command.CommandAnimal.ListarAnimalesPorEspecieCommand;
import Presentacion.Controller.Command.CommandAnimal.ModificarAnimalAcuaticoCommand;
import Presentacion.Controller.Command.CommandAnimal.ModificarAnimalCommand;
import Presentacion.Controller.Command.CommandAnimal.ModificarAnimalNoAcuaticoCommand;
import Presentacion.Controller.Command.CommandAnimal.ModificarAnimalSelectCommand;
import Presentacion.Controller.Command.CommandAnimal.MostrarAnimalCommand;
import Presentacion.Controller.Command.CommandDepartamento.AltaDepartamentoCommand;
import Presentacion.Controller.Command.CommandDepartamento.BajaDepartamentoCommand;
import Presentacion.Controller.Command.CommandDepartamento.CalcularNominaDepartamentoCommand;
import Presentacion.Controller.Command.CommandDepartamento.ListarDepartamentoCommand;
import Presentacion.Controller.Command.CommandDepartamento.ModificarDepartamentoCommand;
import Presentacion.Controller.Command.CommandDepartamento.MostrarDepartamentoCommand;
import Presentacion.Controller.Command.CommandEmpleado.AltaEmpleadoLimpiezaCommand;
import Presentacion.Controller.Command.CommandEmpleado.AltaEmpleadoZoologicoCommand;
import Presentacion.Controller.Command.CommandEmpleado.BajaEmpleadoCommand;
import Presentacion.Controller.Command.CommandEmpleado.ListarEmpleadosCommand;
import Presentacion.Controller.Command.CommandEmpleado.ListarEmpleadosLimpiezaCommand;
import Presentacion.Controller.Command.CommandEmpleado.ListarEmpleadosPorEspecieEnHabitat;
import Presentacion.Controller.Command.CommandEmpleado.ListarEmpleadosPorHabitat;
import Presentacion.Controller.Command.CommandEmpleado.ListarEmpleadosZoologicoCommand;
import Presentacion.Controller.Command.CommandEmpleado.ModificarEmpleadoCommand;
import Presentacion.Controller.Command.CommandEmpleado.MostrarEmpleadoCommand;
import Presentacion.Controller.Command.CommandEspecie.AltaEspecieCommand;
import Presentacion.Controller.Command.CommandEspecie.BajaEspecieCommand;
import Presentacion.Controller.Command.CommandEspecie.ListarEspeciesCommand;
import Presentacion.Controller.Command.CommandEspecie.ListarEspeciesPorHabitatCommand;
import Presentacion.Controller.Command.CommandEspecie.ModificarEspecieCommand;
import Presentacion.Controller.Command.CommandEspecie.MostrarEspecieCommand;
import Presentacion.Controller.Command.CommandFactura.CerrarFacturaCommand;
import Presentacion.Controller.Command.CommandFactura.DevolverFacturaCommand;
import Presentacion.Controller.Command.CommandFactura.ListarFacturasCommand;
import Presentacion.Controller.Command.CommandFactura.ModificarFacturaCommand;
import Presentacion.Controller.Command.CommandFactura.MostrarFacturaCommand;
import Presentacion.Controller.Command.CommandHabitat.AltaHabitatCommand;
import Presentacion.Controller.Command.CommandHabitat.BajaHabitatCommand;
import Presentacion.Controller.Command.CommandHabitat.CalcularHabitatMasIngresosCommand;
import Presentacion.Controller.Command.CommandHabitat.DesvincularEmpleadoHabitatCommand;
import Presentacion.Controller.Command.CommandHabitat.ListarHabitatCommand;
import Presentacion.Controller.Command.CommandHabitat.ListarHabitatPorEmpleadoCommand;
import Presentacion.Controller.Command.CommandHabitat.ModificarHabitatCommand;
import Presentacion.Controller.Command.CommandHabitat.MostrarHabitatCommand;
import Presentacion.Controller.Command.CommandHabitat.VincularEmpleadoHabitatCommand;
import Presentacion.Controller.Command.CommandMarcaProductoJpa.AltaMarcaProducto;
import Presentacion.Controller.Command.CommandMarcaProductoJpa.BajaMarcaProducto;
import Presentacion.Controller.Command.CommandMarcaProductoJpa.ListarMarcaProducto;
import Presentacion.Controller.Command.CommandMarcaProductoJpa.ListarMarcasProveedor;
import Presentacion.Controller.Command.CommandMarcaProductoJpa.ModificarMarcaProducto;
import Presentacion.Controller.Command.CommandMarcaProductoJpa.MostrarMarcaProducto;
import Presentacion.Controller.Command.CommandPase.AltaPaseCommand;
import Presentacion.Controller.Command.CommandPase.BajePaseCommand;
import Presentacion.Controller.Command.CommandPase.ListarPasesCommand;
import Presentacion.Controller.Command.CommandPase.ListarPasesPorHabitat;
import Presentacion.Controller.Command.CommandPase.ModificarPaseCommand;
import Presentacion.Controller.Command.CommandPase.MostrarPaseCommand;
import Presentacion.Controller.Command.CommandProductoJPA.AltaProductoCommand;
import Presentacion.Controller.Command.CommandProductoJPA.BajaProductoCommand;
import Presentacion.Controller.Command.CommandProductoJPA.ListarProductoCommand;
import Presentacion.Controller.Command.CommandProductoJPA.ListarProductosPorMarcaCommand;
import Presentacion.Controller.Command.CommandProductoJPA.ModificarProductoCommand;
import Presentacion.Controller.Command.CommandProductoJPA.MostrarProductoCommand;
import Presentacion.Controller.Command.CommandProveedorJPA.AltaProveedorCommand;
import Presentacion.Controller.Command.CommandProveedorJPA.BajaProveedorCommand;
import Presentacion.Controller.Command.CommandProveedorJPA.DesvincularProveedorConMarcaDeProductoCommand;
import Presentacion.Controller.Command.CommandProveedorJPA.ListarProveedoresCommand;
import Presentacion.Controller.Command.CommandProveedorJPA.ListarProveedoresPorMarcaDeProductoCommand;
import Presentacion.Controller.Command.CommandProveedorJPA.ModificarProveedorCommand;
import Presentacion.Controller.Command.CommandProveedorJPA.MostrarProveedorCommand;
import Presentacion.Controller.Command.CommandProveedorJPA.VincularProveedorConMarcaDeProductoCommand;
import Presentacion.Controller.Command.CommandTrabajador.AltaTrabajadorCommand;
import Presentacion.Controller.Command.CommandTrabajador.BajaTrabajadorCommand;
import Presentacion.Controller.Command.CommandVentaJPA.CerrarVentaCommand;
import Presentacion.Controller.Command.CommandVentaJPA.CerrarVentaIDTrabajadorCommand;
import Presentacion.Controller.Command.CommandVentaJPA.DevolucionVentaCommand;
import Presentacion.Controller.Command.CommandVentaJPA.ListarVentaCommand;
import Presentacion.Controller.Command.CommandVentaJPA.ListarVentasPorTrabajadorCommand;
import Presentacion.Controller.Command.CommandVentaJPA.ModificarVentaCommand;
import Presentacion.Controller.Command.CommandVentaJPA.MostrarVentaCommand;
import Presentacion.Controller.Command.CommandTrabajador.CalcularSueldoTrabajadorCommand;
import Presentacion.Controller.Command.CommandTrabajador.ListarTrabajadoresCommand;
import Presentacion.Controller.Command.CommandTrabajador.ListarTrabajadoresPorDepartamentoCommand;
import Presentacion.Controller.Command.CommandTrabajador.ModificarTrabajadorCommand;
import Presentacion.Controller.Command.CommandTrabajador.MostrarTrabajadorCommand;

public class CommandFactoryImp extends CommandFactory {

	public Command GetCommand(Integer event) {
		switch (event) {
			// Eventos de Animal
			case Evento.ALTA_ANIMAL_ACUATICO:
				return new AltaAnimalAcuaticoCommand();
			case Evento.ALTA_ANIMAL_NO_ACUATICO:
				return new AltaAnimalNoAquaticoCommand();
			case Evento.BAJA_ANIMAL:
				return new BajaAnimalCommand();
			case Evento.MODIFICAR_ANIMAL:
				return new ModificarAnimalCommand();
			case Evento.MOSTRAR_ANIMAL:
				return new MostrarAnimalCommand();
			case Evento.VLISTAR_ANIMALES:
				return new ListarAnimalesCommand();
			case Evento.VLISTAR_ANIMALES_NO_ACUATICOS:
				return new ListarAnimalesNoAcuaticosCommand();
			case Evento.VLISTAR_ANIMALES_ACUATICOS:
				return new ListarAnimalesAcuaticosCommand();
			case Evento.LISTAR_ANIMALES_POR_ESPECIE:
				return new ListarAnimalesPorEspecieCommand();
			case Evento.VMODIFICAR_ANIMAL_SELECTOR:
				return new ModificarAnimalSelectCommand();
			case Evento.MODIFICAR_ANIMAL_ACUATICO:
				return new ModificarAnimalAcuaticoCommand();
			case Evento.MODIFICAR_ANIMAL_NO_ACUATICO:
				return new ModificarAnimalNoAcuaticoCommand();
	
			// Eventos de Especie
			case Evento.ALTA_ESPECIE:
				return new AltaEspecieCommand();
			case Evento.BAJA_ESPECIE:
				return new BajaEspecieCommand();
			case Evento.MODIFICAR_ESPECIE:
				return new ModificarEspecieCommand();
			case Evento.MOSTRAR_ESPECIE:
				return new MostrarEspecieCommand();
			case Evento.LISTAR_ESPECIES:
				return new ListarEspeciesCommand();
			case Evento.LISTAR_ESPECIE_POR_HABITAT:
				return new ListarEspeciesPorHabitatCommand();
	
			// Eventos de Empleado
			case Evento.ALTA_EMPLEADO_LIMPIEZA:
				return new AltaEmpleadoLimpiezaCommand();
			case Evento.ALTA_EMPLEADO_ZOOLOGICO:
				return new AltaEmpleadoZoologicoCommand();
			case Evento.BAJA_EMPLEADO:
				return new BajaEmpleadoCommand();
			case Evento.MODIFICAR_EMPLEADO:
				return new ModificarEmpleadoCommand();
			case Evento.MOSTRAR_EMPLEADO:
				return new MostrarEmpleadoCommand();
			case Evento.VLISTAR_EMPLEADO:
				return new ListarEmpleadosCommand();
			case Evento.VLISTAR_EMPLEADOS_LIMPIEZA:
				return new ListarEmpleadosLimpiezaCommand();
			case Evento.VLISTAR_EMPLEADOS_ZOOLOGICO:
				return new ListarEmpleadosZoologicoCommand();
			case Evento.LISTAR_EMPLEADOS_POR_HABITAT:
				return new ListarEmpleadosPorHabitat();
			case Evento.LISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT:
				return new ListarEmpleadosPorEspecieEnHabitat();
	
			// Eventos de Habitat
			case Evento.ALTA_HABITAT:
				return new AltaHabitatCommand();
			case Evento.BAJA_HABITAT:
				return new BajaHabitatCommand();
			case Evento.MODIFICAR_HABITAT:
				return new ModificarHabitatCommand();
			case Evento.MOSTRAR_HABITAT:
				return new MostrarHabitatCommand();
			case Evento.LISTAR_HABITAT:
				return new ListarHabitatCommand();
			case Evento.LISTAR_HABITATS_POR_EMPLEADO:
				return new ListarHabitatPorEmpleadoCommand();
			case Evento.CALCULAR_HABITAT_CON_MAS_INGRESOS:
				return new CalcularHabitatMasIngresosCommand();
			case Evento.VINCULAR_EMPLEADO_A_HABITAT:
				return new VincularEmpleadoHabitatCommand();
			case Evento.DESVINCULAR_EMPLEADO_DE_HABITAT:
				return new DesvincularEmpleadoHabitatCommand();
	
			// Eventos de Pase
			case Evento.ALTA_PASE:
				return new AltaPaseCommand();
			case Evento.BAJA_PASE:
				return new BajePaseCommand();
			case Evento.MODIFICAR_PASE:
				return new ModificarPaseCommand();
			case Evento.MOSTRAR_PASE:
				return new MostrarPaseCommand();
			case Evento.LISTAR_PASES:
				return new ListarPasesCommand();
			case Evento.LISTAR_PASES_POR_HABITAT:
				return new ListarPasesPorHabitat();
	
			// Eventos de Factura
			case Evento.CERRAR_FACTURA:
				return new CerrarFacturaCommand();
			case Evento.LISTAR_FACTURAS:
				return new ListarFacturasCommand();
			case Evento.MOSTRAR_FACTURA:
				return new MostrarFacturaCommand();
			case Evento.DEVOLVER_FACTURA:
				return new DevolverFacturaCommand();
			case Evento.MODIFICAR_FACTURA:
				return new ModificarFacturaCommand();
	
			// Eventos de Producto
			case Evento.ALTA_PRODUCTO:
				return new AltaProductoCommand();
			case Evento.BAJA_PRODUCTO:
				return new BajaProductoCommand();
			case Evento.MODIFICAR_PRODUCTO:
				return new ModificarProductoCommand();
			case Evento.MOSTRAR_PRODUCTO:
				return new MostrarProductoCommand();
			case Evento.LISTAR_PRODUCTOS:
				return new ListarProductoCommand();
			case Evento.LISTAR_PRODUCTOS_POR_MARCA:
				return new ListarProductosPorMarcaCommand();
				
			// Eventos de Producto
			case Evento.ALTA_DEPARTAMENTO:
				return new AltaDepartamentoCommand();
			case Evento.BAJA_DEPARTAMENTO:
				return new BajaDepartamentoCommand();
			case Evento.MODIFICAR_DEPARTAMENTO:
				return new ModificarDepartamentoCommand();
			case Evento.MOSTRAR_DEPARTAMENTO:
				return new MostrarDepartamentoCommand();
			case Evento.LISTAR_DEPARTAMENTO:
				return new ListarDepartamentoCommand();
			case Evento.CALCULAR_NOMINA_DEPARTAMENTO:
				return new CalcularNominaDepartamentoCommand();	
				
			// Eventos de Venta
			case Evento.CERRAR_VENTA:
				return new CerrarVentaCommand();
			case Evento.CERRAR_VENTA_IDTRABAJADOR:
				return new CerrarVentaIDTrabajadorCommand();
			case Evento.LISTAR_VENTAS:
				return new ListarVentaCommand();
			case Evento.MOSTRAR_VENTA:
				return new MostrarVentaCommand();
			case Evento.DEVOLVER_VENTA:
				return new DevolucionVentaCommand();
			case Evento.MODIFICAR_VENTA:
				return new ModificarVentaCommand();
			case Evento.LISTAR_VENTAS_POR_TRABAJADOR:
				return new ListarVentasPorTrabajadorCommand();
				
			// Eventos de Trabajador
		    case Evento.ALTA_TRABAJADOR:
		        return new AltaTrabajadorCommand();
		    case Evento.BAJA_TRABAJADOR:
		        return new BajaTrabajadorCommand();
		    case Evento.CALCULAR_SUELDO_TRABAJADOR:
		        return new CalcularSueldoTrabajadorCommand();
		    case Evento.LISTAR_TRABAJADORES:
		        return new ListarTrabajadoresCommand();
		    case Evento.LISTAR_TRABAJADORES_POR_DEPARTAMENTO:
		        return new ListarTrabajadoresPorDepartamentoCommand();
		    case Evento.MODIFICAR_TRABAJADOR:
		        return new ModificarTrabajadorCommand();
		    case Evento.MOSTRAR_TRABAJADOR:
		        return new MostrarTrabajadorCommand();
		        
		     // Eventos de Proveedor
		    case Evento.ALTA_PROVEEDOR:
		        return new AltaProveedorCommand();
		    case Evento.BAJA_PROVEEDOR:
		        return new BajaProveedorCommand();
		    case Evento.LISTAR_PROVEEDORES:
		        return new ListarProveedoresCommand();
		    case Evento.LISTAR_PROVEEDOR_POR_MARCA:
		        return new ListarProveedoresPorMarcaDeProductoCommand();
		    case Evento.MODIFICAR_PROVEEDOR:
		        return new ModificarProveedorCommand();
		    case Evento.MOSTRAR_PROVEEDOR:
		        return new MostrarProveedorCommand();
		    case Evento.VINCULAR_PROVEEDOR_A_MARCA:
		        return new VincularProveedorConMarcaDeProductoCommand();
		    case Evento.DESVINCULAR_PROVEEDOR_DE_MARCA:
		        return new DesvincularProveedorConMarcaDeProductoCommand();
		        
		    // Eventos Marca
		    case Evento.ALTA_MARCA:
		        return new AltaMarcaProducto();
		    case Evento.BAJA_MARCA:
		        return new BajaMarcaProducto();
		    case Evento.MODIFICAR_MARCA:
		        return new ModificarMarcaProducto();
		    case Evento.MOSTRAR_MARCA:
		        return new MostrarMarcaProducto();
		    case Evento.LISTAR_MARCA:
		        return new ListarMarcaProducto();
		    case Evento.LISTAR_MARCAS_POR_PROVEEDOR:
		        return new ListarMarcasProveedor();

		}
		return null;
	}
}