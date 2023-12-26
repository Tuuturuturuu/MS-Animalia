package Negocio.Habitat;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Integracion.Empleado.DAOEmpleado;
import Integracion.Especie.DAOEspecie;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.FactoriaQuery.CalcularHabitatConMasIngresos;
import Integracion.FactoriaQuery.FactoriaQuery;
import Integracion.Habitat.DAOHabitat;
import Integracion.Habitat.DAOTrabajo;
import Integracion.Pase.DAOPase;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

import Negocio.Empleado.TEmpleado;
import Negocio.Especie.TEspecie;
import Negocio.Pase.TPase;


public class HabitatSAImp implements HabitatSA {
	
	public Integer AltaHabitat(THabitat habitat) {
                //Comprobamos si en el alta han puesto campos nulos
                if(habitat.getNombre().isEmpty() || habitat.getTamano() == 0 ){
                    return -3; //Enviamos error de no se pueden dejar campos vacios en ALTA 
                }
                int exito = -1;
                Transaction t = null;
                try {
                    TransactionManager transaction = TransactionManager.getInstance();
                    t = transaction.newTransaction();
                    t.start();
                    FactoriaDAO f = FactoriaDAO.getInstance();

                    DAOHabitat daoHabitat = f.getDAOHabitat();
                    THabitat habitatUnico = daoHabitat.leerPorNombreUnico(habitat.getNombre());
                    if(habitatUnico == null){ //No existe un habitat con el mismo nombre, por lo tanto damos de alta sin problema
                        exito = daoHabitat.altaHabitat(habitat);
                        t.commit();
                    } else if(!habitatUnico.isActivo()){ //Comprobamos si ese habitat que ya tiene el mismo nombre esta dado de baja
                        //Procedemos a reactivarlo y actualizar sus valores
                    	habitat.setId(habitatUnico.getId());
                        exito = daoHabitat.modificarHabitat(habitat);
                        t.commit();
                    } else {
                        //Mostramos error
                        exito = -23; //Error de que ya existe un habitat con el mismo nombre y activo
                        t.rollback();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return exito;
    }


	public Integer BajaHabitat(Integer idHabitat) {
		int exito = -1;
		Transaction t = null;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOHabitat daoHabitat = f.getDAOHabitat();
			THabitat habitat = daoHabitat.mostarHabitat(idHabitat);
		
			//Comprobamos que existe un Habitat con ese id y que esta activo
			if (habitat != null ) {
				
				if(habitat.isActivo()){
					
					DAOEspecie daoEspecie = f.getDAOEspecie();
					Set <TEspecie> especies = daoEspecie.listarEspeciePorHabitatActivos(idHabitat);
					DAOTrabajo daoTrabajo = f.getDAOTrabajo();
					
					DAOPase daoPase = f.getDAOPase(); 
					Set<TPase> pases = daoPase.listarPasesPorHabitatActivos(idHabitat);
					
					if(especies.size() == 0 && pases.size()== 0){
						Set<TTrabajo> trabajos = daoTrabajo.mostrarTrabajoPorHabitat(idHabitat);
						for (TTrabajo tTrabajo : trabajos) {
							daoTrabajo.desvincularHabitatEmpleado(tTrabajo);
						}
						exito = daoHabitat.bajaHabitat(idHabitat);
						t.commit();
					}else{
						exito = -24; //Error: no puedes dar de baja a un habitat con especies o pases  activos
						t.rollback();
					}
				}
				else{
					exito = -21; //Error de que el id es el de un Habitat inactivo
					t.rollback();
				}
			} else{
				exito = -20; //Error de que el id es el de un Habitat que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	
	public THabitat MostrarHabitat(Integer idHabitat) {
		THabitat habitatMostrar = new THabitat(0, false, "", 0);
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOHabitat daoHabitat = f.getDAOHabitat();
			THabitat existe = daoHabitat.mostarHabitat(idHabitat);
			if (existe != null ) {
				habitatMostrar.setId(existe.getId());
				habitatMostrar.setNombre(existe.getNombre());
				habitatMostrar.setTamano(existe.getTamano());
				habitatMostrar.setActivo(existe.isActivo());
				t.commit();
			} else{
				habitatMostrar.setId(-20);//Error de que el id es el de un habitat que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return habitatMostrar;
	}

	
	public Integer ModificarHabitat(THabitat habitat) {
		//Comprobamos si en el alta han puesto campos nulos
				if(habitat.getId()==0 || habitat.getNombre().isEmpty() || habitat.getTamano()==0 ){
					return -3; //Enviamos error de no se pueden dejar campos vacios 
				}
				
				int exito = -1;
				try {
					TransactionManager transaction = TransactionManager.getInstance();
					Transaction t = transaction.newTransaction();
					t.start();
					FactoriaDAO f = FactoriaDAO.getInstance();
					DAOHabitat daoHabitat = f.getDAOHabitat();
					THabitat existe = daoHabitat.mostarHabitat(habitat.getId());
					if (existe != null ) {
						
						if(existe.isActivo()){
							//Comprobamos que  Habitat existe y esta activo
							THabitat habitatUnico = daoHabitat.leerPorNombreUnico(habitat.getNombre());
							if(habitatUnico == null){ //No existe un habitat con el mismo nombre, por lo tanto modificamos habitat sin problema
								exito = daoHabitat.modificarHabitat(habitat);
								t.commit();
							}else if(!habitatUnico.isActivo()){ //Comprobamos si ese habitat que ya tiene el mismo nombre esta dado de baja
								exito = -22;
								t.rollback();
							} else {
								//Mostramos error
								exito = -23; //Error de que ya existe un habitat con el mismo nombre y activo
								t.rollback();
							}
							
							
						}else{
							exito = -21; //Error de que el id es el de un Habitat inactivo
							t.rollback();
						}
						
					} else{
						exito = -20;//Error de que el id es el de una Habitat que no existe 
						t.rollback();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return exito;
	}

	
	public Set<THabitat> ListarHabitat() {
		
		Set<THabitat> habitats = new HashSet<>();
		
		try{
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOHabitat daoHabitat = f.getDAOHabitat();
			Set<THabitat> habitatsBuscar = daoHabitat.listarHabitats();
			for(THabitat habitat : habitatsBuscar){
				habitats.add(habitat);
			}
			habitatsBuscar = null; //Liberamos memoria
			t.commit();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return habitats;
	}

	
	public Integer VincularEmpleadoHabitat(TTrabajo trabajo) {
		//hay que hacer que trabajo devuelva los ID's
		int exito = -1;
		if(trabajo.getIdEmpleado()==0 || trabajo.getIdHabitat()==0){
			exito= -3;
		}
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			
			//Supervisamos si los id's existen y estan activos
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			DAOHabitat daoHabitat = f.getDAOHabitat();
			TEmpleado E = daoEmpleado.mostrar(trabajo.getIdEmpleado());
			THabitat  H = daoHabitat.mostarHabitat(trabajo.getIdHabitat());
			if(E!= null && H!= null){
				if(E.getActivo() && H.isActivo()){
					
					DAOTrabajo daoTrabajo = f.getDAOTrabajo();
					TTrabajo existe = daoTrabajo.mostrarTrabajo(trabajo);
					if(existe != null){
						exito = -25; // ya estan vinculados
						t.rollback();
					}
					else{
						exito = daoTrabajo.vincularHabitatEmpleado(trabajo);
						t.commit();
					}
				}
				else{
					exito=-2; //uno de los 2 esta inactivo
					t.rollback();
				}
			}
			else{
				exito= -5; //uno de los 2 posee un id incorrecto
				t.rollback();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	
	public Integer DesvincularEmpleadoHabitat(TTrabajo trabajo) {
		//hay que hacer que trabajo devuelva los ID's
		int exito = -1;
		if(trabajo.getIdEmpleado()==0 || trabajo.getIdHabitat()==0){
			exito= -3;
		}
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			
			//Supervisamos si los id's existen y estÃ¡n activos
			DAOEmpleado daoEmpleado = f.getDAOEmpleado();
			DAOHabitat daoHabitat = f.getDAOHabitat();
			TEmpleado E = daoEmpleado.mostrar(trabajo.getIdEmpleado());
			THabitat  H = daoHabitat.mostarHabitat(trabajo.getIdHabitat());
			
			if(E!= null && H!= null){
				if(E.getActivo() && H.isActivo()){
					
					DAOTrabajo daoTrabajo = f.getDAOTrabajo();
					TTrabajo existe = daoTrabajo.mostrarTrabajo(trabajo);
					if(existe != null){
						exito = daoTrabajo.desvincularHabitatEmpleado(trabajo);
						t.commit();
						
					}
					else{
						exito = -26; // ya estan desvinculados
						t.rollback();
					}
				}
				else{
					exito=-2; //uno de los 2 esta inactivo
					t.rollback();
				}
			}
			else{
				t.rollback();
				exito= -5; //uno de los 2 posee un id incorrecto
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	
	public Set<THabitat> ListarHabitatPorEmpleado(Integer idEmpleado) {
		Set<THabitat> habitats = new HashSet<THabitat>();
		THabitat tHabitat = new THabitat();
		
		if(idEmpleado>0){
			
			try {
				TransactionManager transaction = TransactionManager.getInstance();
				Transaction t = transaction.newTransaction();
				t.start();
				FactoriaDAO f = FactoriaDAO.getInstance();
				DAOEmpleado daoEmpleado = f.getDAOEmpleado();
				TEmpleado empleado = daoEmpleado.mostrar(idEmpleado);
				
				if(empleado != null){
					DAOTrabajo daoTrabajo = f.getDAOTrabajo();
					Set<TTrabajo> trabajosBuscar = daoTrabajo.mostrarTrabajoPorEmpleado(idEmpleado);
					DAOHabitat daoHabitat = f.getDAOHabitat();
					
					for(TTrabajo trabajoEncontrado : trabajosBuscar){
						tHabitat = daoHabitat.mostarHabitat(trabajoEncontrado.getIdHabitat());
						habitats.add(tHabitat);
					}
					trabajosBuscar = null;//Liberamos memoria
					t.commit();				
				}else{
					tHabitat.setId(-41);; //Error de que el id Empleado no existe 
					habitats.add(tHabitat);
					t.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return habitats;
	}

	
	public THabitat CalcularHabitatMasIngresos(LocalDate inicio, LocalDate fin) {
		THabitat HabitatMasRentable = new THabitat();
		LocalDate fechaEspecifica = LocalDate.of(0001, 1, 1); // Crear una fecha específica para comparar
		if(inicio.equals(fechaEspecifica) && fin.equals(fechaEspecifica)){
			HabitatMasRentable.setId(-3);
		}else{
			try {
				TransactionManager transaction = TransactionManager.getInstance();
				Transaction t = transaction.newTransaction();
				t.start();
				FactoriaQuery q = FactoriaQuery.getInstance();
				
				CalcularHabitatConMasIngresos calcular = (CalcularHabitatConMasIngresos) q.getNewQuery("CalcularHabitatConMasIngresos");
				
				 HashMap<String, Object> params = new HashMap<>();
				 params.put("desdeFecha", inicio);
				 params.put("hastaFecha", fin);
				HabitatMasRentable = (THabitat) calcular.execute(params);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return HabitatMasRentable;
	}
}
