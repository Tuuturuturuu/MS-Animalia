package Negocio.ProveedorJPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.MarcaProductoJPA.MarcaProducto;

public class ASProveedorImp implements ASProveedor {
	
	//ALTA

	public Integer altaProveedor(TProveedor proveedor) {
		Integer id = -1;
		boolean success = false;
		Proveedor proveedorExistente = null;
		Proveedor nuevoProveedor = null;
		
        if (!validarCIF(proveedor.getCIF())) {
            // El CIF no es sintacticamente correcto
            return id;
        }
        
        //Empieza una teransaccion (a traves del entity transaction que crea una transaction a partir del entity manager)
        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //Obtenemos el proveedor por CIF
		TypedQuery<Proveedor> query = em.createNamedQuery("Negocio.ProveedorJPA.Proveedor.findByCIF", Proveedor.class);
		query.setParameter("CIF", proveedor.getCIF());

        try {
             proveedorExistente = query.getSingleResult();     
        } catch (Exception e) {
            // No existe un proveedor activo con el mismo CIF, continuamos
        	//DEBUG
        	System.out.println("Proveedor con el mismo CIF no existe");
        }
        
        if(proveedorExistente != null) //Proveedor existe se comprueba si esta activo
        {
        	if(!proveedorExistente.getActivo()) //Si no esta activo reactivamos
        	{
                // Reactivar el proveedor inactivo
        		id = proveedorExistente.getId();
        		proveedorExistente.transferToEntity(proveedor);;
        		proveedorExistente.setId(id);
                em.persist(proveedorExistente);
        	}
        	else //Si esta activo hacemos rollback
        	{
        		transaction.rollback();   
        		id = -76;
        		return id;
        	}
        }
        else //Proveedor no existe, damos de alta
        {
            nuevoProveedor = new Proveedor(proveedor);
            em.persist(nuevoProveedor);
            success = true;
        }
        
        try{
        	transaction.commit();
        	if(success)
        		id = nuevoProveedor.getId();
        }
        catch(Exception e){
        	transaction.rollback();
        }
        
        em.close();
        return id;
	}

	//BAJA

	public Integer bajaProveedor(Integer idProveedor) {
		int res = -1;
		
		if (!validarId(idProveedor)) {
            // El ID no es sintacticamente correcto
			res = -4;
            return res;
        }

		EntityManager eManager = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaction = eManager.getTransaction();
		transaction.begin();

		Proveedor pr = eManager.find(Proveedor.class, idProveedor);

		if (pr != null && pr.getActivo()) {//existe el proveedor y no esta dado de baja
			List<MarcaProducto> listaMarcas = pr.getMarcas();
			for(MarcaProducto m: listaMarcas){
				m.getProveedores().remove(pr);
			}
			pr.setActivo(false);	
			pr.getMarcas().clear();
			try{
				transaction.commit();
				res = pr.getId();
			}
			catch(Exception e){
				transaction.rollback();
				return res;
			}
		} else {//esta ya dado de baja el proovedor o no existe
			transaction.rollback();
			res = -77;
			return res;
		}
		eManager.close();

		return res;
	}
	
	//MODIFICAR

	public Integer modificarProveedor(TProveedor proveedor) {
		int res = -1;
		
		 if (!validarCIF(proveedor.getCIF()) || !validarId(proveedor.getId())) {
	        	//DEBUG
	        //System.out.println("Id o CIF de proveedor no valido");
	        return res;
	    }
		 
		EntityManager eManager = (EntityManager) EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaction = eManager.getTransaction();
		transaction.begin();
		
		Proveedor pr = eManager.find(Proveedor.class, proveedor.getId());

		if (pr != null && pr.getActivo()) {//Existe y esta activo
			TypedQuery<Proveedor> query = eManager.createNamedQuery("Negocio.ProveedorJPA.Proveedor.findByCIF", Proveedor.class);
    		query.setParameter("CIF", proveedor.getCIF());
    		Proveedor provExistente = null;
    		
    		try{
    			provExistente = query.getSingleResult();
    		}
    		catch(Exception e){
    			//No hay un proveedor con el mismo nombre continuamos
        		}
        		
        		if(provExistente == null){
    				pr.transferToEntity(proveedor);
    				eManager.persist(pr);
    				try {				
    					transaction.commit();
    					res = pr.getId();
    				} catch (Exception e) {
    					transaction.rollback();
    					return res;
    				}
        		}
        		else
        		{
        			transaction.rollback();
        			return res;
        		}
        			
            }
        else
        {
        	transaction.rollback();
        	return res;
        }
        eManager.close();

    return res;
	}

		//MOSTRAR
	
	public TProveedor mostrarProveedor(Integer idProveedor) {
		if (!validarId(idProveedor)) {
            // DEBUG
            System.out.println("Formato incorrecto para el ID del proveedor.");
            return null;
        }
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); //No usamos transaccion al ser un read

        Proveedor proveedor = em.find(Proveedor.class, idProveedor);

		if (proveedor == null)
			return null;

		TProveedor TProveedor = proveedor.entityToTransfer();
		
		em.close();
		
		return TProveedor;
	}

		//LISTAR
	
	public List<TProveedor> listarProveedores() {
	    EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); // No hay transacción porque es un listar

	    TypedQuery<Proveedor> query = em.createNamedQuery("Negocio.ProveedorJPA.Proveedor.findAll", Proveedor.class);

	    List<Proveedor> proveedores = query.getResultList(); // Obtenemos una lista de proveedores
	    List<TProveedor> lista = new ArrayList<TProveedor>();

	    for (Proveedor p : proveedores) {
	        TProveedor t = p.entityToTransfer();
	        lista.add(t);
	    }

	    em.close();
	    return lista;
	}

	//LISTAR PROVEEDORES POR MARCA
	
	public List<TProveedor> listarProveedoresPorMarcaDeProducto(Integer idMarca) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		
		MarcaProducto marca = em.find(MarcaProducto.class, idMarca);
		
		if (marca == null){ 
			em.close();
			return null;
		}
		
		List<Proveedor> provMarca = marca.getProveedores();
		List<TProveedor> proveedores = new ArrayList<>();
		
		for (Proveedor p : provMarca){
			proveedores.add(new TProveedor(p));
		}
		
		return proveedores;
		

	}


	//VINCULAR PROVEEDOR MARCA
	
	public Integer vincularProveedorConMarcaDeProducto(TProveedorConMarcas proveedorConMarcas) {
		
		int res = -1;
		int id_marca = proveedorConMarcas.getIMarca();
		int id_proveedor = proveedorConMarcas.getIdProveedor();
		
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		Proveedor prov = em.find(Proveedor.class, id_proveedor);
		MarcaProducto marca = em.find(MarcaProducto.class, id_marca, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

		if (prov != null && marca != null && prov.getActivo() && marca.getActivo()) {
			List<MarcaProducto> marcasList = prov.getMarcas();

			if (!marcasList.contains(marca)) {
				marcasList.add(marca);
				marca.getProveedores().add(prov);

				try{
					transaccion.commit();
					res = id_proveedor;
					em.close();
					return res;
				}
				catch(Exception e){
					transaccion.rollback();
					em.close();
					return res;
				}
			}
			
		} else {
			transaccion.rollback();
			return res;
		}

		em.close();

		return res;
		
	}

		//DESVINCULAR 
	
	public Integer desvincularProveedorConMarcaDeProducto(TProveedorConMarcas proveedorConMarcas) {
		int res = -1;
		int id_marca = proveedorConMarcas.getIMarca();
		int id_proveedor = proveedorConMarcas.getIdProveedor();
		
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		Proveedor prov = em.find(Proveedor.class, id_proveedor);
		MarcaProducto marca = em.find(MarcaProducto.class, id_marca, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

		if (prov != null && marca != null && prov.getActivo() && marca.getActivo()) {
			List<MarcaProducto> marcasList = prov.getMarcas();

			if (marcasList.contains(marca)) {
				marcasList.remove(marca);
				marca.getProveedores().remove(prov);

				try{
					transaccion.commit();
					res = id_proveedor;
					em.close();
					return res;
				}
				catch(Exception e){
					transaccion.rollback();
					em.close();
					return res;
				}
			}
			
		} else {
			transaccion.rollback();
			em.close();
			return res;
		}

		em.close();

		return res;
	}
	
/////////////////VALIDACION
// Mï¿½todo para validar la sintaxis del ID
private boolean validarId(Integer id) {
return id != null && id > 0;
}


// Mï¿½todo para validar la sintaxis del nombre
private boolean validarCIF(String CIF) {
return CIF != null && !CIF.isEmpty();
}

}