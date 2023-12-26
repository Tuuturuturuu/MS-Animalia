package Negocio.TrabajadorJPA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.DepartamentoJPA.Departamento;


public class ASTrabajadorImp implements ASTrabajador {

	//ALTA TRABAJADOR
	/*
	 Se comprueba que los datos sean sintácticamente correctos.
	Se verifica que el departamento con id dado exista y esté activo.
	Se verifica si existe un trabajador activo con el mismo DNI. Si es así, 
	se muestra un mensaje de error indicando que el trabajador ya existe.
	Si existe un trabajador inactivo con dicho DNI, este se reactiva. 
	Si no existe un trabajador con el DNI, se inserta un nuevo registro de trabajador . 
	Se genera un mensaje de éxito que confirma que el nuevo trabajador se ha registrado 
	con éxito y se muestra el ID del trabajador.
	 */
	public synchronized Integer altaTrabajador(TTrabajador trabajador) {

		Integer id = -1;
		boolean success = false;
		Trabajador trabajadorExistente = null;
		Trabajador nuevoTrabajador = null;
		Departamento departamentoExistente = null;

		if (!validarNombre(trabajador.getNombre())) {
			// El nombre no es sintácticamente correcto
			return -4;
		}

		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		// Obtenemos el departamento por id
		TypedQuery<Departamento> query2 = em.createNamedQuery("Negocio.DepartamentoJPA.Departamento.findByid", Departamento.class);
		query2.setParameter("id", trabajador.getIdDepartamento());
		
		//Se comprueba si existe
		try {
			departamentoExistente = query2.getSingleResult();
		} catch (Exception e) {
			System.out.println("Departamento no existe, terminamos");
			transaction.rollback();
            em.close();
			return -115;
		}
		
		//Se comprueba si esta activo
		if(!departamentoExistente.getActivo())
		{
			transaction.rollback();
            em.close();
			return -116;
		}
		

		TypedQuery<Trabajador> query = em.createNamedQuery("Negocio.TrabajadorJPA.Trabajador.findBydni", Trabajador.class);
		query.setParameter("dni", trabajador.getDni());
		
		try {
			trabajadorExistente = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Trabajador con el mismo dni no existe");
		}
		
		if(trabajadorExistente != null) //Trabajador existe se comprueba si esta activo
        {
        	if(!trabajadorExistente.getActivo()) //Si no esta activo reactivamos
        	{
                // Reactivar el trabajador inactivo
        		trabajadorExistente.transferToEntity(trabajador);
				departamentoExistente.setNumTrabajadores(departamentoExistente.getNumTrabajadores() + 1);
				trabajadorExistente.getDepartamento().getTrabajadores().remove(trabajadorExistente);
				trabajadorExistente.setDepartamento(departamentoExistente);
				departamentoExistente.getTrabajadores().add(trabajadorExistente);
                id = trabajadorExistente.getId();
                transaction.commit();
                em.close();
                return id;
        	}
        	else //Si esta activo hacemos rollback
        	{
        		transaction.rollback();   
                em.close();
        		return -120;
        	}
        }
		 else //Trabajador no existe, damos de alta
	        {
				if(trabajador instanceof TTrabajadorCompleto){
					nuevoTrabajador = new TrabajadorCompleto(trabajador);
				}
				else if(trabajador instanceof TTrabajadorParcial){
					nuevoTrabajador = new TrabajadorParcial(trabajador);
				}
				//No hace falta bloquear departamento, se modifica aqui
				departamentoExistente.setNumTrabajadores(departamentoExistente.getNumTrabajadores() + 1);
				nuevoTrabajador.setDepartamento(departamentoExistente);
				departamentoExistente.getTrabajadores().add(nuevoTrabajador);
	            em.persist(nuevoTrabajador);
	            success = true;
	        }
		try{
        	transaction.commit();
        	if(success)
        		id = nuevoTrabajador.getId();
        }
        catch(Exception e){
        	transaction.rollback();
        }
        
        em.close();
        return id;
	}

	
	//BAJA TRABAJADOR
	/*
	 Comprueba que el id está en formato correcto. Comprueba si existe un 
	 trabajador activo en la base de datos con el ID introducido. En caso 
	 afirmativo, pasa a estar inactivo. En caso contrario, se muestra un 
	 mensaje informando del error.
	 */
	public Integer bajaTrabajador(Integer idTrabajador) {
		
		int resultado = -1;
    	
        if (!validarId(idTrabajador)) {
            // El ID no es sintácticamente correcto
            return -4;
        }

        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
		
		Trabajador trabajador = em.find(Trabajador.class, idTrabajador);
		
		if(trabajador != null && trabajador.getActivo()){ 
			trabajador.setActivo(false);
			// Obtenemos el departamento por id
			TypedQuery<Departamento> query2 = em.createNamedQuery("Negocio.DepartamentoJPA.Departamento.findByid", Departamento.class);
			query2.setParameter("id", trabajador.getDepartamento().getId());
			
			Departamento dep = null;
			try {
				dep = query2.getSingleResult();
			} catch (Exception e) {
				System.out.println("Error al buscar el departamento");
				transaction.rollback();
		        em.close();
				return -115;
			}
			dep.setNumTrabajadores(dep.getNumTrabajadores() - 1);
			dep.getTrabajadores().remove(trabajador);
			
			
			try {				
				transaction.commit();
				resultado = trabajador.getId();
			} catch (Exception e) {
				transaction.rollback();
		        em.close();
				return resultado;
			}
			
		}
		else{
			transaction.rollback();
	        em.close();
			return -122;
		}
		
		em.close();
		return resultado;
	}

	//MODIFICAR TRABAJADOR
	/*
	 Comprueba que el trabajador existe y se encuentra activo. 
	 En ese caso, comprueba que los datos introducidos son sintácticamente 
	 correctos. Si el campo a modificar es el DNI, se comprueba que no 
	 pertenezca a un trabajador en la BD, y si es el id_departamento, 
	 se comprueba que éste pertenezca a un departamento en la BD. 
	 Si esto se cumple, en base al tipo de Trabajador se actualizan 
	 los datos del trabajador correspondiente y se muestra un mensaje de éxito.
	  En caso de que alguna condición no se cumpla, se muestra un mensaje informando del error.
	 */
	public Integer modificarTrabajador(TTrabajador trabajador) {

		Integer resultado = -1;
	    EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

	        if (!validarNombre(trabajador.getNombre()) || !validarId(trabajador.getId())) {
	        System.out.println("Id o nombre de trabajador no válido");
	        return -4;
	    }
	    EntityTransaction transaction = em.getTransaction();

	    transaction.begin();

	    Trabajador trab = em.find(Trabajador.class, trabajador.getId());

	    if (trab != null && trab.getActivo()) { //Existe y esta activo
	    		TypedQuery<Trabajador> query = em.createNamedQuery("Negocio.TrabajadorJPA.Trabajador.findBydni", Trabajador.class);
	    		query.setParameter("dni", trabajador.getNombre());
	    		Trabajador trabExistente = null;
	    		Departamento departamentoExistente = null;
	    		
	    		try{
	    			trabExistente = query.getSingleResult();
	    		}
	    		catch(Exception e){
	    			//No hay un trabajador con el mismo dni continuamos
	        		}
	        		
	        		if(trabExistente == null){
	        			
	        			// Obtenemos el departamento por id
	        			TypedQuery<Departamento> query2 = em.createNamedQuery("Negocio.DepartamentoJPA.Departamento.findByid", Departamento.class);
	        			query2.setParameter("id", trabajador.getIdDepartamento());
	        			
	        			//Se comprueba si existe
	        			try {
	        				departamentoExistente = query2.getSingleResult();
	        			} catch (Exception e) {
	        				System.out.println("Departamento no existe, terminamos");
	        				transaction.rollback();
	        	            em.close();
	        				return -115;
	        			}
	        			
        			if(departamentoExistente.getActivo())
        			{
        				trab.getDepartamento().setNumTrabajadores(departamentoExistente.getNumTrabajadores() - 1);
        				trab.getDepartamento().getTrabajadores().remove(trab);
        				trab.transferToEntity(trabajador);
        				trab.setDepartamento(departamentoExistente);
        				departamentoExistente.setNumTrabajadores(departamentoExistente.getNumTrabajadores() + 1);
        				departamentoExistente.getTrabajadores().add(trab);
    					try {				
    						transaction.commit();
    						resultado = trab.getId();
    					} catch (Exception e) {
    						transaction.rollback();
    				        em.close();
    						return resultado;
    					}
        			}
	        	}
	        		else
	        		{
	        			transaction.rollback();
	        	        em.close();
	        			return -123;
	        		}	
	            }
	        else
	        {
	        	transaction.rollback();
	            em.close();
	        	return -122;
	        }
	        em.close();

	    return resultado;
	}

	//MOSTRAR TRABAJADOR
	/*
	 Comprueba si el ID introducido está en el formato correcto, corresponde 
	 con algún trabajador existente en la base de datos, ya esté activo o inactivo. 
	 En caso afirmativo, mostrarán por pantalla sus atributos. En caso contrario, 
	 se muestra un mensaje informando del error.
	 */
	public TTrabajador mostrarTrabajador(Integer idTrabajador) {

		if (!validarId(idTrabajador)) {
            System.out.println("Formato incorrecto para el ID del trabajador.");
            return null;
        }

        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); //No usamos transacción al ser un read

        Trabajador trabajador = em.find(Trabajador.class, idTrabajador);

		if (trabajador == null)
		{
	        em.close();
	        return null;
		}

		TTrabajador TTrabajador = trabajador.entityToTransfer();
		
		em.close();
		
		return TTrabajador;
	}

	
	//LISTAR TRABAJADORES
	/*
	 Se consulta en la BD y se muestran todos los trabajadores registrados 
	 junto con sus atributos.Se muestra además si están activos o inactivos. 
	 En caso de no existir ninguno, no muestra nada.
	 */
	public List<TTrabajador> listarTrabajadores() {

		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); //No hay transacción porque es un listar

		TypedQuery<Trabajador> query = em.createNamedQuery("Negocio.TrabajadorJPA.Trabajador.findAll", Trabajador.class);

		List<Trabajador> l = query.getResultList(); //Obtenemos una lista de trabajadores activos
		List<TTrabajador> lista = new ArrayList<TTrabajador>(l.size());

		for (Trabajador e : l) {
			TTrabajador t = e.entityToTransfer();
			lista.add(t);
		}
		
		em.close();
		return lista;
	}

	//LISTAR TRABAJADORES POR DEPARTAMENTO
	/*
	 Se verifica que el formato y validez del ID introducido y que este sea >0. 
	 Muestra todos los trabajadores que trabajan en el departamento introducido 
	 y están registrados, junto con sus atributos, ya estén activos o inactivos. 
	 En caso de no existir ninguno, no muestra nada. En caso de que alguna de las 
	 verificaciones vaya mal se muestra un mensaje de error.
	 */
	public List<TTrabajador> listarTrabajadoresPorDepartamento(Integer idDepartamento) {
		

		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

		Departamento dpt = em.find(Departamento.class, idDepartamento);

		if(dpt == null){
			em.close();
			return null;
		}
			TypedQuery<Trabajador> query = em.createNamedQuery("Negocio.TrabajadorJPA.Trabajador.findBydepartamento", Trabajador.class);
			query.setParameter("departamento_id", idDepartamento);
			
			List<Trabajador> l = query.getResultList(); //Obtenemos una lista de trabajadores en dicho departamento
			List<TTrabajador> lista = new ArrayList<TTrabajador>(l.size());

			for (Trabajador e : l) {
				TTrabajador t = e.entityToTransfer();
				lista.add(t);
			}
			

		em.close();
		return lista;
	}

	//CALCULAR SUELDO TRABAJADOR
	/*
	Se consulta en la base de datos el trabajador con el id_trabajador proporcionado. 
	Luego, se obtienen los siguientes datos del trabajador: tipo (completo/parcial), 
	sueldo, h_extra y euros/h_extra (si es completo), horas y euros/h (si es parcial). 
	Dependiendo del tipo de trabajador (completo o parcial), se procede a calcular el sueldo:
	*Si el trabajador es de tipo completo:
	Sueldo total = sueldo + (h_extra * euros/h_extra)
	*Si el trabajador es de tipo parcial:
	Sueldo total = horas * euros/h
	Finalmente, el sueldo total calculado se modificara en el campo sueldo del trabajador y a 
	continuación se muestra en la GUI. En caso de error en el cálculo o si el trabajador no existe, 
	se muestra un mensaje de error.

	 */
	public Double calcularSueldoTrabajador(Integer idTrabajador) {

		double sueldo = 0.0;
	    EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
	        Trabajador trabajador = em.find(Trabajador.class, idTrabajador, LockModeType.OPTIMISTIC);

	        if (trabajador == null || !trabajador.getActivo()) {
	            System.out.println("El trabajador con ID " + idTrabajador + " no existe o no está activo.");
	            transaction.rollback();
	            em.close();
	            return -122.0;
	        }

	        sueldo = trabajador.calcularSueldo(); 

	    try{
	    	transaction.commit();
	    }
	    catch(Exception e)
	    {
	    	transaction.rollback();
	    }
	    
	    em.close();
	    
	    return sueldo;
	}
	
	
	///////////////// VALIDACION
	// Método para validar la sintaxis del ID
	private boolean validarId(Integer id) {
		return id != null && id > 0;
	}
	
    // Método para validar la sintaxis del nombre
    private boolean validarNombre(String nombre) {
        return nombre != null && !nombre.isEmpty();
    }
}