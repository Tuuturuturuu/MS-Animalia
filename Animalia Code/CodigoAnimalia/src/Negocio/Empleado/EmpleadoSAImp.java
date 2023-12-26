package Negocio.Empleado;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Integracion.Animal.DAOAnimal;
import Integracion.Empleado.DAOEmpleado;
import Integracion.Especie.DAOEspecie;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.FactoriaQuery.FactoriaQuery;
import Integracion.FactoriaQuery.ListarEmpleadoPorEspecieEnHabitat;
import Integracion.Habitat.DAOHabitat;
import Integracion.Habitat.DAOTrabajo;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Animal.TAnimal;
import Negocio.ComprobacionesRequisitosBBDD.ComprobacionesRequisitosImp;
import Negocio.Especie.TEspecie;
import Negocio.Habitat.THabitat;
import Negocio.Habitat.TTrabajo;

public class EmpleadoSAImp implements EmpleadoSA {
	private ComprobacionesRequisitosImp compr = (ComprobacionesRequisitosImp) ComprobacionesRequisitosImp.getComprobacionesRequisitosBBDD();
	
	public Integer altaEmpleado(TEmpleado empleado) {
		//Comprobamos si en el alta han puesto campos nulos

		if(empleado.getNombre().isEmpty()|| empleado.getDni().isEmpty()
				|| empleado.getSueldoBase() == 0 || empleado.getTelefono() == 0 ){
			return -3; 
		}//Comprobamos los campos nulos de cada tipo de empleado
		else if(empleado instanceof TEmpleadoLimpieza){
			if(((TEmpleadoLimpieza) empleado).getSuplemento() == 0|| ((TEmpleadoLimpieza) empleado).getZona().isEmpty())
				return -3;
		}else if(empleado instanceof TEmpleadoZoologico){
			if(((TEmpleadoZoologico) empleado).getTasa() == 0 || 
					((TEmpleadoZoologico) empleado).getEspecialidad().isEmpty() ||
					((TEmpleadoZoologico) empleado).getExperiencia() == 0)
				return -3;//Enviamos error de no se pueden dejar campos vacios en ALTA 
		}
		
		//Comprobaciones de formato de datos
		if(!compr.dniValido(empleado.getDni()))
			return -44;
		if(!compr.tlValido(Integer.toString(empleado.getTelefono())))
			return -45;
		
		int exito = -1;
				
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
					
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			TEmpleado empleadoUnico = daoEmpleado.leerPorDniUnico(empleado.getDni());
			if(empleadoUnico == null){ //No existe un empleado con el mismo DNI, por lo tanto damos de alta sin problema
				exito = daoEmpleado.alta(empleado);
				t.commit();
			}else if(empleadoUnico.getActivo() == false){ //Comprobamos si ese empleado que ya tiene el mismo DNI esta dado de baja
				//Procedemos a reactivarlo y actualizar sus valores
				empleado.setId(empleadoUnico.getId());
				//Comprobamos si el tipo es del mismo anterior o esta reactivando un empleado de otro tipo
				
				if(empleado.getTipo() == empleadoUnico.getTipo()){ //Si son del mismo tipo lo podemos reactivar
					exito = daoEmpleado.modificar(empleado);
					t.commit();
				}else{//Si no son del mismo tipo debemos mostrar un error
					exito = -46; 
					t.rollback();
				}					
				
			}else{
				//Mostramos error
				exito = -40; //Error de que ya existe un empleado con el mismo DNI y activa¡o
				t.rollback();
			}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	
	public Integer bajaEmpleado(Integer idEmpleado) {
		
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			TEmpleado empleadoUnico = daoEmpleado.mostrar(idEmpleado);
			//Comprobamos que existe un empleado con ese id y que esta activo
			if (empleadoUnico != null ) {
				
				if(empleadoUnico.getActivo() == true){
					//Desvinculamos el empleado de los habitats
					DAOTrabajo daoTrabajo = f.getDAOTrabajo();
					Set<TTrabajo> trabajos = daoTrabajo.mostrarTrabajoPorEmpleado(idEmpleado);
					for (TTrabajo tTrabajo : trabajos) {
						daoTrabajo.desvincularHabitatEmpleado(tTrabajo);
					}
					exito = daoEmpleado.baja(idEmpleado);
					t.commit();
				}
				else{
					exito = -42; //Error de que el id es el de un empleado inactivo
					t.rollback();
				}
			} else{
				exito = -41; //Error de que el id es el de un empleado que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}


	public Integer modificarEmpleado(TEmpleado empleado) {
		//Comprobamos si en el modificar han puesto campos nulos

		if(empleado.getNombre().isEmpty()|| empleado.getDni().isEmpty()
			|| empleado.getSueldoBase() == 0 || empleado.getTelefono() == 0 ){
			return -3; 
		}//Comprobamos los campos nulos de cada tipo de empleado
		else if(empleado instanceof TEmpleadoLimpieza){
			if(((TEmpleadoLimpieza) empleado).getSuplemento() == 0|| ((TEmpleadoLimpieza) empleado).getZona().isEmpty())
			return -3;
		}else if(empleado instanceof TEmpleadoZoologico){
			if(((TEmpleadoZoologico) empleado).getTasa() == 0 || 
				((TEmpleadoZoologico) empleado).getEspecialidad().isEmpty() ||
				((TEmpleadoZoologico) empleado).getExperiencia() == 0)
			return -3;//Enviamos error de no se pueden dejar campos vacios en ALTA 
		}
				
		//Comprobaciones de formato de datos
		if(!compr.dniValido(empleado.getDni()))
				return -44;
		if(!compr.tlValido(Integer.toString(empleado.getTelefono())))
				return -45;
				
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			TEmpleado empleadoBuscar = daoEmpleado.mostrar(empleado.getId());
			if (empleadoBuscar != null ) {
						
				if(empleadoBuscar.getActivo()){
					TEmpleado empleadoUnico = daoEmpleado.leerPorDniUnico(empleado.getDni());
					if(empleadoUnico == null){ //No existe un empleado con el mismo DNI, por lo tanto modificamos empleado sin problema
						exito = daoEmpleado.modificar(empleado);
						t.commit();
					}else if(empleadoUnico.getActivo() == false){ //Comprobamos si ese empleado que ya tiene el mismo DNI esta dado de baja
						exito = -43;
						t.rollback();
					}else{
						//Mostramos error
						exito = -40; //Error de que ya existe un empleado con el mismo DNI y activo
						t.rollback();
					}
							
				} else {
					exito = -42; //Error de que el id es el de un empleado inactivo
					t.rollback();
				}
						
			} else{
				exito = -41;//Error de que el id es el de un empleado que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}


	public TEmpleado mostrarEmpleado(Integer id_empleado) {
		TEmpleado empleadoMostrar = new TEmpleado(0,"","",0.0,0,false, 0);
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			TEmpleado empleadoBuscar = daoEmpleado.mostrar(id_empleado);
			if (empleadoBuscar != null ) {
				empleadoMostrar.setId(empleadoBuscar.getId());
				empleadoMostrar.setNombre(empleadoBuscar.getNombre());
				empleadoMostrar.setDni(empleadoBuscar.getDni());
				empleadoMostrar.setSueldoBase(empleadoBuscar.getSueldoBase());
				empleadoMostrar.setTelefono(empleadoBuscar.getTelefono());
				empleadoMostrar.setActivo(empleadoBuscar.getActivo());
				empleadoMostrar.setTipo(empleadoBuscar.getTipo());
				t.commit();
			} else{
				empleadoMostrar.setId(-41);//Error de que el id es el de un empleado que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleadoMostrar;
	}


	public Set<TEmpleado> listarEmpleado() {
		Set<TEmpleado> empleados = new HashSet<>();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			Set<TEmpleado> empleadosBuscar = daoEmpleado.listarEmpleados();
			for(TEmpleado empleado : empleadosBuscar){
				empleados.add(empleado);
			}
			empleadosBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleados;
	}


	public Set<TEmpleado> listarEmpleadoHabitat(Integer idHabitat) {
		Set<TEmpleado> Empleados = new HashSet<TEmpleado>();
		TEmpleado empleado = new TEmpleado();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			
			DAOHabitat daoHabitat = f.getDAOHabitat();
			THabitat habitat = daoHabitat.mostarHabitat(idHabitat);
			if(habitat != null){
				DAOEmpleado daoEmpleado = f.getDAOEmpleado();
				DAOTrabajo daoTrabajo = f.getDAOTrabajo();
				Set<TTrabajo> empleadosBuscar = daoTrabajo.mostrarTrabajoPorHabitat(idHabitat);	
				for(TTrabajo trabajoFind : empleadosBuscar){
					TEmpleado empleadoEncontrado = daoEmpleado.mostrar(trabajoFind.getIdEmpleado());
					Empleados.add(empleadoEncontrado);
				}
				empleadosBuscar = null; //Liberamos memoria
				t.commit();
			}else{
				empleado.setId(-20); //Error de que el id Habitat no existe 
				Empleados.add(empleado);
				t.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Empleados;
	}


	public Set<TEmpleado> ListarEmpleadosPorEspecieEnHabitat(Integer idEspecie) {
		
		Set<TEmpleado> Empleados = new HashSet<TEmpleado>();
		TEmpleado empleado = new TEmpleado();
		
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaQuery q = FactoriaQuery.getInstance();

			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEspecie daoEspecie = f.getDAOEspecie();
			TEspecie especie = daoEspecie.mostrarEspecie(idEspecie);
			if(especie != null) {
				
				ListarEmpleadoPorEspecieEnHabitat listar = (ListarEmpleadoPorEspecieEnHabitat) q.getNewQuery("ListarEmpleadoPorEspecieEnHabitat");
				HashMap<String, Object> params = new HashMap<>();
				params.put("idEspecie", idEspecie);
				
				Empleados = (Set<TEmpleado>) listar.execute(params);
				
			} else {
				empleado.setId(-11); //Error de que el id Especie no existe 
				Empleados.add(empleado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Empleados;
	}


	@Override
	public Set<TEmpleado> listarEmpleadosLimpieza() {
		Set<TEmpleado> empleados = new HashSet<>();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			Set<TEmpleado> empleadosBuscar = daoEmpleado.listarEmpleadosLimpieza();
			for(TEmpleado empleado : empleadosBuscar){
				empleados.add(empleado);
			}
			empleadosBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleados;
	}


	@Override
	public Set<TEmpleado> listarEmpleadosZoologico() {
		Set<TEmpleado> empleados = new HashSet<>();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			Set<TEmpleado> empleadosBuscar = daoEmpleado.listarEmpleadosZoologico();
			for(TEmpleado empleado : empleadosBuscar){
				empleados.add(empleado);
			}
			empleadosBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleados;
	}
}