package Negocio.DepartamentoJPA;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.TrabajadorJPA.Trabajador;

public class ASDepartamentoImp implements ASDepartamento {

	//////////////////ALTA
	/*
	Se comprueba que el nombre introducido es sintácticamente correcto. 
	Se verifica si existe un departamento activo con el mismo nombre. 
	Si es así, se muestra un mensaje de error indicando que el departamento ya existe.
    Si existe un departamento inactivo con dicho nombre, este se reactiva. Si no existe
    un departamento con el nombre introducido, se inserta un nuevo registro de departamento 
    en la base de datos con el nombre proporcionado. Se genera un mensaje de éxito que 
    confirma que el nuevo departamento se ha registrado con éxito y se muestra el ID del departamento.
	 */
	public synchronized Integer altaDepartamento(TDepartamento departamento) {
		Integer id = -1;
		boolean success = false;
		Departamento departamentoExistente = null;
		Departamento nuevoDepartamento = null;
		
        if (!validarNombre(departamento.getNombre())) {
            // El nombre no es sintácticamente correcto
            return -4;
        }

        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //Obtenemos el departamento por nombre
		TypedQuery<Departamento> query = em.createNamedQuery("Negocio.DepartamentoJPA.Departamento.findBynombre", Departamento.class);
		query.setParameter("nombre", departamento.getNombre());

        try {
             departamentoExistente = query.getSingleResult();     
        } catch (Exception e) {
            // No existe un departamento activo con el mismo nombre, continuamos
        	//DEBUG
        	System.out.println("Departamento con el mismo nombre no existe");
        }
        
        if(departamentoExistente != null) //Departamento existe se comprueba si esta activo
        {
        	if(!departamentoExistente.getActivo()) //Si no esta activo reactivamos
        	{
                // Reactivar el departamento inactivo
        		departamentoExistente.transferToEntity(departamento);
                id = departamentoExistente.getId();
                transaction.commit();
                em.close();
                return id;
        	}
        	else //Si esta activo hacemos rollback
        	{
        		transaction.rollback();   
                em.close();
        		return -110;
        	}
        }
        else //Departamento no existe, damos de alta
        {
            nuevoDepartamento = new Departamento(departamento);
            em.persist(nuevoDepartamento);
            success = true;
        }
        
        try{
        	transaction.commit();
        	if(success)
        		id = nuevoDepartamento.getId();
        }
        catch(Exception e){
        	transaction.rollback();
        }
        
        em.close();
        return id;
    }

	//////////////////BAJA
	/*
	Se verifica que el id introducido sea sintácticamente correcto. 
	Se comprueba si el departamento con el ID proporcionado existe 
	en la base de datos, está marcado como activo y no tiene trabajadores 
	asociados activos. Si no cumple con estas condiciones, se muestra un 
	mensaje de error indicando que la operación no puede ser realizada
    Si las cumple, se marca el departamento como inactivo en la base de datos. 
    Se muestra un mensaje de éxito que confirma que el departamento se ha dado de baja con éxito.
	 */
	
    public Integer bajaDepartamento(Integer id) {
    	int resultado = -1;
    	
        if (!validarId(id)) {
            // El ID no es sintácticamente correcto
            return -4;
        }

        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
		
		Departamento departamento = em.find(Departamento.class, id);
		
		if(departamento != null && departamento.getActivo() && departamento.getNumTrabajadores() == 0){
			departamento.setActivo(false);
			try {				
				transaction.commit();
				resultado = departamento.getId();
			} catch (Exception e) {
				transaction.rollback();
                em.close();
				return resultado;
			}
			
		}
		else{
			transaction.rollback();
            em.close();
			return -112;
		}
		
		em.close();
		return resultado;
    }

	//////////////////MOSTRAR
    /*
      Se comprueba que el id tenga formato correcto. Se verifica si el departamento
      con el ID proporcionado existe en la base de datos. Si no se cumple esta condición, 
      se muestra un mensaje de error indicando que el departamento no existe. En caso contrario, 
      se muestra por pantalla el nombre del departamento.
     */
    public TDepartamento mostrarDepartamento(Integer id) {
        TDepartamento error = new TDepartamento();
    	
    	if (!validarId(id)) {
            // DEBUG
            System.out.println("Formato incorrecto para el ID del departamento.");
            error.setId(-4);
            return error;
        }

        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); //No usamos transacción al ser un read

        Departamento departamento = em.find(Departamento.class, id);

		if (departamento == null)
		{
			error.setId(-115);
			return error;
		}
		TDepartamento TDepartamento = departamento.entityToTransfer();
		
		em.close();
		
		return TDepartamento;
    }

    
	//////////////////MODIFICAR
    /*
     Se comprueba que los datos sean sintácticamente correctos. Se verifica si el departamento 
     con el ID proporcionado existe en la base de datos y está activo. Si no se cumple esta 
     condición, se muestra un mensaje de error indicando que la operación no puede ser realizada. 
     Si se cumple, se comprueba si el nuevo nombre pertenece a un departamento en la base de datos. 
     Si no es así, se actualiza el nombre del departamento en la base de datos con el nuevo nombre 
     proporcionado. Se genera un mensaje de éxito que confirma que el departamento se ha modificado con éxito.  En caso de que alguna condición no se cumpla, se muestra un mensaje informando del error.
     */
public Integer modificarDepartamento(TDepartamento departamento) {
    Integer resultado = -1;
    EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

        if (!validarNombre(departamento.getNombre()) || !validarId(departamento.getId())) {
        	//DEBUG
        System.out.println("Id o nombre de departamento no válido");
        return -4;
    }
    EntityTransaction transaction = em.getTransaction();

    transaction.begin();

    Departamento dep = em.find(Departamento.class, departamento.getId());

    if (dep != null && dep.getActivo()) { //Existe y esta activo
    		TypedQuery<Departamento> query = em.createNamedQuery("Negocio.DepartamentoJPA.Departamento.findBynombre", Departamento.class);
    		query.setParameter("nombre", departamento.getNombre());
    		Departamento depExistente = null;
    		
    		try{
    			depExistente = query.getSingleResult();
    		}
    		catch(Exception e){
    			//No hay un departamento con el mismo nombre continuamos
        		}
        		
        		if(depExistente == null){
    				dep.transferToEntity(departamento);
    				try {				
    					transaction.commit();
    					resultado = dep.getId();
    				} catch (Exception e) {
    					transaction.rollback();
    	                em.close();
    					return resultado;
    				}
        		}
        		else
        		{
        			transaction.rollback();
                    em.close();
        			return -113;
        		}
        			
            }
        else
        {
        	transaction.rollback();
            em.close();
        	return -114;
        }
        em.close();

    return resultado;
}

	//////////////////LISTAR
    /*
     Se muestra una lista con todos los departamentos almacenados en la base de datos.
     */
    public List<TDepartamento> listarDepartamento() {
    	
        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); //No hay transacción porque es un listar

		TypedQuery<Departamento> query = em.createNamedQuery("Negocio.DepartamentoJPA.Departamento.findAll", Departamento.class);

		List<Departamento> l = query.getResultList(); //Obtenemos una lista de departamentos
		List<TDepartamento> lista = new ArrayList<TDepartamento>();

		for (Departamento d : l) {
			TDepartamento t = d.entityToTransfer();
			lista.add(t);
		}
		
		em.close();
		return lista;
    }


	//////////////////CALCULARNOMINA
    /*
     Se verifica la existencia y si está activo el departamento con el id_departamento proporcionado 
     en la base de datos. Luego, se obtienen todos los trabajadores vinculados a dicho departamento. 
     Una vez obtenidos, se comprueba que los trabajadores existen y están activos y posteriormente se 
     suman todos los salarios de esos trabajadores.Para calcularlo se calculará: Nómina total del 
     departamento = sum (salario individual de cada trabajador activo del departamento)
	 Finalmente, la suma total calculada se muestra en la GUI. En caso de error en el cálculo o si el 
	 departamento está inactivo o no existe, se muestra un mensaje de error.

     */
public double calcularNominaDepartamento(Integer id_departamento) {
    double nominaTotal = 0.0;
    EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	transaction.begin();
	
        Departamento departamento = em.find(Departamento.class, id_departamento, LockModeType.OPTIMISTIC);

        if (departamento == null || !departamento.getActivo()) {
        	//DEBUG
            System.out.println("El departamento con ID " + id_departamento + " no existe o no está activo.");
            transaction.rollback();
            em.close();
            return -111;
        }

        for (Trabajador trabajador : departamento.getTrabajadores()) {
            if (trabajador.getActivo()) {
				em.lock(trabajador, LockModeType.OPTIMISTIC);
				nominaTotal += trabajador.calcularSueldo(); 
            }
        }

    transaction.commit();
    em.close();
    
    return nominaTotal;
}
	
	/////////////////VALIDACION
    // Método para validar la sintaxis del ID
    private boolean validarId(Integer id) {
        return id != null && id > 0;
    }
    

    // Método para validar la sintaxis del nombre
    private boolean validarNombre(String nombre) {
        return nombre != null && !nombre.isEmpty();
    }
    
    
}
