package Negocio.ProductoJPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Integracion.Transactions.Transaction;
import Negocio.EMFSingleton.EMFSingleton;
import Negocio.MarcaProductoJPA.MarcaProducto;

public class ASProductoImp implements ASProducto {

	public Integer altaProducto(TProducto producto) {
		Integer id = -1;

		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		TypedQuery<Producto> query = em.createNamedQuery("Negocio.ProductoJPA.Producto.findBynombre", Producto.class);
		query.setParameter("nombre", producto.getNombre());
		Producto productoAux = null;

		MarcaProducto marcaAux = em.find(MarcaProducto.class, producto.getIdMarcaProducto());

		try {
			productoAux = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("No existe un producto con el mismo nombre luego se puede crear");
		}

		Producto newProducto = null;

		if (productoAux == null && marcaAux != null && marcaAux.getActivo()) {
			if (producto instanceof TAlimentacion) {
				Alimentacion alimentacionAux = new Alimentacion();
				alimentacionAux.transferToEntity(producto);
				alimentacionAux.setMarcaProducto(marcaAux);
				newProducto = alimentacionAux;
				marcaAux.setContadorProductos(marcaAux.getContadorProductos() + 1);

			} else if (producto instanceof TMerchandising) {
				Merchadising merchAux = new Merchadising();
				merchAux.transferToEntity(producto);
				merchAux.setMarcaProducto(marcaAux);
				newProducto = merchAux;
				marcaAux.setContadorProductos(marcaAux.getContadorProductos() + 1);

			}

			em.persist(newProducto);

			try {
				transaction.commit();
				id = newProducto.getId();
			} catch (Exception e) {
				transaction.rollback();
			}
			}
		else if(productoAux != null && marcaAux != null && !productoAux.getActivo()  && marcaAux.getActivo()){
			productoAux.setActivo(true);
			marcaAux.setContadorProductos(marcaAux.getContadorProductos() + 1);
			newProducto=productoAux;
			em.persist(newProducto);
			try {
				transaction.commit();
				id = newProducto.getId();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
		 else {
			transaction.rollback();
			id = -133;
		}

		em.close();
		return id;

	}

	public Integer bajaProducto(Integer idProducto) {
		int resultado = -1;

		if (!validarId(idProducto)) {
			// El ID no es sint�cticamente correcto
			return resultado;
		}

		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Producto producto = em.find(Producto.class, idProducto);


		if (producto != null && producto.getActivo()) {
			MarcaProducto marcaAux = em.find(MarcaProducto.class, producto.getMarcaProducto().getId());
			producto.setActivo(false);
			em.persist(producto);
			try {
				marcaAux.setContadorProductos(marcaAux.getContadorProductos() - 1);

				transaction.commit();
				resultado = producto.getId();
			} catch (Exception e) {
				transaction.rollback();
				return resultado;

			}
		} else {
			transaction.rollback();
			resultado = -134;
			return resultado;
		}

		em.close();
		return resultado;
	}

	public Integer modificarProducto(TProducto producto) {
		Integer resultado = -1;

		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); // No
																						// usamos
																						// transacci�n
																						// al
																						// ser
																						// un
																						// read
		EntityTransaction et = em.getTransaction();
		et.begin();

		Producto c2 = em.find(Producto.class, producto.getID());
	
		TypedQuery<Producto> q = em.createNamedQuery("Negocio.ProductoJPA.Producto.findBynombre", Producto.class);
		q.setParameter("nombre", producto.getNombre());
		Producto c = null;

		try {
			c = q.getSingleResult();
		} catch (Exception e) {
		}

		if (c2 != null && (c == null || producto.getNombre().equals(c2.getNombre()))) {
			MarcaProducto t = em.find(MarcaProducto.class, producto.getIdMarcaProducto());
			if (t != null && t.getActivo()) {
				c2.transferToEntity(producto);
				if (t != c2.getMarcaProducto()) {
					t.setContadorProductos(t.getContadorProductos() + 1);
					c2.getMarcaProducto().setContadorProductos(c2.getMarcaProducto().getContadorProductos() - 1);
					c2.setMarcaProducto(t);

				}
				try {
					resultado = c2.getId();
					et.commit();
					
				} catch (Exception e) {
					et.rollback();
				}

			} else
				et.rollback();
		} else {
			et.rollback();
		}
		em.close();
		return resultado;

	}

	public TProducto mostrarProducto(Integer idProducto) {

		if (!validarId(idProducto)) {
			// DEBUG
			System.out.println("Formato incorrecto para el ID del producto.");
			return null;
		}

		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); // No
																						// usamos
																						// transacci�n
																						// al
																						// ser
																						// un
																						// read

		Producto producto = em.find(Producto.class, idProducto);

		if (producto == null)
			return null;

		TProducto TProducto = entityToTransfer(producto);

		em.close();

		return TProducto;
	}

	public List<TProducto> listarProductos() {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); // No
																						// hay
																						// transacci�n
																						// porque
																						// es
																						// un
																						// listar

		TypedQuery<Producto> query = em.createNamedQuery("Negocio.ProductoJPA.Producto.findByactivo", Producto.class);
		query.setParameter("activo", true);

		List<Producto> l = query.getResultList(); // Obtenemos una lista de
													// departamentos activos
		List<TProducto> lista = new ArrayList<TProducto>();

		for (Producto p : l) {
			TProducto t = entityToTransfer(p);
			lista.add(t);
		}

		em.close();
		return lista;
	}

	public List<TProducto> listarProductosPorMarcasDeProducto(Integer idMarcaDeProducto) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();

		MarcaProducto marca = em.find(MarcaProducto.class, idMarcaDeProducto);

		

		
		List<TProducto> productos = new ArrayList<>();
		if(marca == null){
			em.close();
			return null;
		}
		else{
			List<Producto> prodMarca = marca.getProductos();
			for (Producto p : prodMarca) {
				productos.add(new TProducto(p));
			}
		}
		
		em.close();
		return productos;
	}

	///////////////// VALIDACION
	// M�todo para validar la sintaxis del ID
	private boolean validarId(Integer id) {
		return id != null && id > 0;
	}

	// M�todo para validar la sintaxis del nombre
	private boolean validarNombre(String nombre) {
		return nombre != null && !nombre.isEmpty();
	}

	private TProducto entityToTransfer(Producto producto) {
		TProducto tProducto = null;

		if (producto instanceof Alimentacion) {
			Alimentacion alimentacion = (Alimentacion) producto;
			tProducto = alimentacion.entityToTransfer();
		} else if (producto instanceof Merchadising) {
			Merchadising merchadising = (Merchadising) producto;
			tProducto = merchadising.entityToTransfer();
		}
		return tProducto;
	}

}