package Negocio.MarcaProductoJPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Negocio.MarcaProductoJPA.MarcaProducto;
import Negocio.MarcaProductoJPA.TMarcaProducto;
import Negocio.EMFSingleton.EMFSingleton;
import Negocio.ProveedorJPA.Proveedor;


public class ASMarcaProductoImp implements ASMarcaProducto {
	
	//ALTA MARCA
	/*
	 El usuario ingresa el nombre de la nueva marca en la interfaz gráfica de usuario.
	 Se verifica si existe una marca del producto activo con el mismo nombre. 
	 Si se cumple esta condición, se muestra un mensaje de error indicando que ya existe.
	 Si la marca del producto tiene el mismo nombre, pero está inactiva se procede con la reactivación.
     Si no existe una marca del producto con el mismo nombre, se inserta un nuevo registro en la base de 
     datos con el nombre proporcionado. Se genera un mensaje de éxito que confirma que la
     nueva marca del producto se ha registrado con éxito y se muestra su ID.
	 */
	public synchronized Integer altaMarcaProducto(TMarcaProducto marca) {
		Integer id = -1;
		boolean success = false;
		MarcaProducto marcaExistente = null;
		MarcaProducto nuevaMarca = null;
		
        if (!validarNombre(marca.getNombre())) {
            // El Nombre no es correcto
            return -4;
        }
        
        //Empieza una teransaccion (a traves del entity transaction que crea una transaction a partir del entity manager)
        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //Obtenemos la MarcaProducto por nombre
		TypedQuery<MarcaProducto> query = em.createNamedQuery("Negocio.MarcaProductoJPA.MarcaProducto.findBynombre", MarcaProducto.class);
		query.setParameter("nombre", marca.getNombre());

        try {
             marcaExistente = query.getSingleResult();     
        } catch (Exception e) {
            // No existe una marca con el mismo nombre
        	System.out.println("Marca con el mismo nombre no existe");
        }
        
        if(marcaExistente != null) //MarcaProducto existe se comprueba si esta activo
        {
        	if(!marcaExistente.getActivo()) //Si no esta activo reactivamos
        	{
                // Reactivar la marca inactiva
        		marcaExistente.transferToEntity(marca);
                id = marcaExistente.getId();
                try{
                	transaction.commit();
                	em.close();
                	return id;
                }
                catch(Exception e)
                {
                	transaction.rollback();
                	em.close();
                	return id;
                }
        	}
        	else //Si esta activo hacemos rollback
        	{
        		transaction.rollback();   
        		em.close();
        		return -143;
        	}
        }
        else //MarcaProducto no existe, damos de alta
        {
            nuevaMarca = new MarcaProducto(marca);
            em.persist(nuevaMarca);
            success = true;
        }
        
        try{
        	transaction.commit();
        	if(success)
        		id = nuevaMarca.getId();
        }
        catch(Exception e){
        	transaction.rollback();
        }
        
        em.close();
        return id;
	}

	//BAJA MARCA
	/*
	 El usuario ingresa el id de la marca en la interfaz gráfica de usuario.
	 Comprueba que la marca del producto existe, está activa y no tiene productos
	  activos. Si se cumple este caso, se hará una baja lógica la marca en la BD y 
	  se desvincula automáticamente la marca del producto con los proveedores. 
	  En el caso contrario, se mostrará un mensaje mostrando la razón del error.

	 */
	public Integer bajaMarcaProducto(Integer id) {
	    int res = -1;
	    if (!validarId(id)) {
	        System.out.println("Formato incorrecto para el ID de Marca Producto.");
	        return -4;
	    }

	    EntityManager eManager = EMFSingleton.getInstance().getEMF().createEntityManager();
	    EntityTransaction transaction = eManager.getTransaction();
	    transaction.begin();

	    MarcaProducto mp = eManager.find(MarcaProducto.class, id);

	    if (mp != null && mp.getActivo() && mp.getContadorProductos() == 0) {

	    	List<Proveedor> listaProveedores = mp.getProveedores();
	    	//Desvincular de la marca sus proveedores
	    	for (Proveedor p: listaProveedores){
	    		p.getMarcas().remove(mp);
	    		//listaProveedores.remove(p);
	    	}
	    	listaProveedores.clear();
	        mp.setActivo(false);
	        try {
	            transaction.commit();
	            res = mp.getId();
	        } catch (Exception e) {
	            transaction.rollback();
	            eManager.close();
	            return res;
	        }
	    } else {
	        transaction.rollback();
			eManager.close();
	        return -142;
	    }
	    eManager.close();
	    return res;
	}

	//MODIFICAR MARCA PRODUCTO
	/*
	 En ese caso, comprueba que los datos introducidos son sintácticamente correctos. 
	 Comprueba si la marca existe y está activa, y que el nuevo nombre no esté repetido 
	 con los demás datos de la BD. Si se cumple, se modifica la marca del producto en la 
	 BD. En caso contrario, se lanza un mensaje informando del error.
	 */
	public Integer modificarMarcaProducto(TMarcaProducto marca) {
		int res = -1;
		
		if(!validarNombre(marca.getNombre()) ){
			System.out.println("Nombre de Marca Producto invalidos.");
            return -4;
		}
		
		EntityManager eManager =EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaction = eManager.getTransaction();
		transaction.begin();
		
		MarcaProducto mp = eManager.find(MarcaProducto.class, marca.getId());

		if (mp != null && mp.getActivo()) {
			TypedQuery<MarcaProducto> query = eManager.createNamedQuery("Negocio.MarcaProductoJPA.MarcaProducto.findBynombre", MarcaProducto.class);
			query.setParameter("nombre", marca.getNombre());
    		MarcaProducto mpExistente = null;
			
    		try{
    			mpExistente = query.getSingleResult();
    		}
    		catch(Exception e){
    			//No hay una Marca con el mismo nombre continuamos
        	}
        		
        		if(mpExistente == null){
    				mp.transferToEntity(marca);
    				eManager.persist(mp);
    				try {				
    					transaction.commit();
    					res = mp.getId();
    				} catch (Exception e) {
    					transaction.rollback();
    					eManager.close();
    					return res;
    				}
        		}
        		else
        		{
        			transaction.rollback();
        			eManager.close();
        			return -145;
        		}
        			
            
		} else {
			transaction.rollback();
			eManager.close();
			return -144;
		}

		eManager.close();
		return res;
	}

	//MOSTRAR MARCA PRODUCTO
	/*
	 Comprueba si el ID introducido es sintácticamente correcto y 
	 si se encuentra en la BD de Marca del Producto.En caso afirmativo,
	 mostrará en pantalla sus atributos. En caso contrario, se muestra un 
	 mensaje informando del error.
	 */
	public TMarcaProducto mostrarMarcaProducto(Integer id) {
		
		if(!validarId(id)){
			System.out.println("Formato incorrecto para el ID de Marca Producto.");
            return null;
		}
		EMFSingleton factoriaEntityManager = EMFSingleton.getInstance();
		EntityManager entityManager = factoriaEntityManager.getEMF().createEntityManager();

		MarcaProducto marca = entityManager.find(MarcaProducto.class, id);

		if (marca == null) {
			return null;
		}

		TMarcaProducto tMarca = new TMarcaProducto(id,marca.getNombre(),marca.getActivo());

		entityManager.close();
		return tMarca;
	}

	//listarMarcaProducto
	/*
	 Muestra todas las marcas registradas y sus atributos. 
	 En caso de no existir ninguna, se muestra una tabla vacía
	 */
	public List<TMarcaProducto> listarMarcaProducto() {
        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); //No hay transacción porque es un listar

		TypedQuery<MarcaProducto> query = em.createNamedQuery("Negocio.MarcaProductoJPA.MarcaProducto.findAll", MarcaProducto.class);

		List<MarcaProducto> l = query.getResultList(); //Obtenemos una lista de marcas
		List<TMarcaProducto> lista = new ArrayList<TMarcaProducto>();

		for (MarcaProducto m : l) {
			TMarcaProducto t = m.entityToTransfer();
			lista.add(t);
		}
		
		em.close();
		return lista;
		
	}

	//LISTAR MARCA POR PROVEEDOR
	/*
	 Comprueba que el ID introducido esta en el formato correcto. En caso correcto, 
	 comprueba que existe un proveedor en la BD con el ID introducido. En caso afirmativo, 
	 muestra todas las marcas vinculadas al proveedor (si no hay muestra una tabla vacía). 
	 En caso contrario, se informa del error.
	 */
	public List<TMarcaProducto> listarMarcaPorProveedor(Integer idProveedor) {
        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction transaccion = em.getTransaction();
        transaccion.begin();

        Proveedor proveedor = em.find(Proveedor.class, idProveedor);

        if (proveedor == null) {
        	em.close();
            return null;
        }

        List<MarcaProducto> marcasProveedor = proveedor.getMarcas();
        List<TMarcaProducto> marcas = new ArrayList<>();

        for (MarcaProducto marca : marcasProveedor) {
            marcas.add(new TMarcaProducto(marca));
        }

        transaccion.commit();
        em.close();

        return marcas;
	}
	
	private Boolean validarNombre(String nombre){
		return nombre != null && !nombre.isEmpty();
	}
	
    private boolean validarId(Integer id) {
        return id != null && id > 0;
    }
    
}