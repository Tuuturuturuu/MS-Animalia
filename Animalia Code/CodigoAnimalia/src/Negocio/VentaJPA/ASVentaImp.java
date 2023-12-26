/**
 * 
 */
package Negocio.VentaJPA;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.Factura.TCarrito;
import Negocio.ProductoJPA.Alimentacion;
import Negocio.ProductoJPA.Merchadising;
import Negocio.ProductoJPA.Producto;
import Negocio.ProductoJPA.TAlimentacion;
import Negocio.ProductoJPA.TMerchandising;
import Negocio.ProductoJPA.TProducto;
import Negocio.TrabajadorJPA.Trabajador;

public class ASVentaImp implements ASVenta {
	
	public TCarritoJPA comprobarTrabajador(Integer idTrabajador){
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        Trabajador t = em.find(Trabajador.class, idTrabajador);
        TCarritoJPA c = new TCarritoJPA();
        if(t != null && t.getActivo()){
        	c.getVenta().setIdTrabajador(idTrabajador);
        }else{
        	c.getVenta().setIdTrabajador(-100); //Error de que no existe el trabajador indicado
        }
        em.close();
		return c;
	}

	public Integer cerrarVenta(TCarritoJPA carrito) {
        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        int resultado = -1;
		Trabajador trabajador = em.find(Trabajador.class, carrito.getVenta().getIdTrabajador(), LockModeType.OPTIMISTIC);
		if(trabajador != null && trabajador.getActivo()){
			if(!carrito.getLineasVenta().isEmpty()){
				double total_venta = 0;
				Venta venta = new Venta();
				venta.setTrabajador(trabajador);
				LocalDate localDate = LocalDate.now().plusDays(1);
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				venta.setFecha(date);
				venta.setActivo(true);
				venta.setMetodo_pago(carrito.getVenta().getMetodo_pago());
				em.persist(venta);
				for(TLineaVenta lv: carrito.getLineasVenta()){
					Producto producto = em.find(Producto.class, lv.getIdProducto());
					if(producto != null){
						if(producto.getActivo()){
							if(lv.getUnidades() <= producto.getStock()){
								//Modificamos el stock del producto
								producto.setStock(producto.getStock() - lv.getUnidades());
								//Calculamos el precio de la lineaVenta
								double precio_lineaVenta = lv.getUnidades() * producto.getPrecio();
								LineaVenta lineaVenta = new LineaVenta();
								lineaVenta.transferToEntity(lv);
								lineaVenta.setProducto(producto);
								lineaVenta.setVenta(venta);
								lineaVenta.setPrecioVenta(precio_lineaVenta);
								em.persist(lineaVenta);
								venta.getListalineaVentas().add(lineaVenta);
								//Actualizamos el precio total a pagar
								total_venta = total_venta + precio_lineaVenta;
							}else{
								et.rollback();
								em.close();
								return -101; //No hay stock suficiente del producto
							}
						}else{
							et.rollback();
							em.close();
							return -102; //El producto no esta activo
						}
					}else{
						et.rollback();
						em.close();
						return -103; //El producto no existe
					}
				}
				venta.setPrecio_total(total_venta);
				try {
					et.commit();
					resultado = venta.getId();
				} catch (Exception e) {
					et.rollback();
				}
			}else {
				et.rollback();
				em.close();
				return -104; //Error de que no se puede cerrar una venta con un carrito vacío
			}
				
		}else{
			et.rollback();
			em.close();
			return -105; //Error de que no se puede cerrar una venta con un trabajador que no existe o esta inactivo
			
		}
        
		em.close();
		return resultado;
		// end-user-code
	}

	public TVentaConProductos mostrarVenta(Integer idVenta) {
        TVentaConProductos vcp = new TVentaConProductos();
        Set<TProducto> productos = new HashSet<TProducto>();
        Set<TLineaVenta> lineasVenta = new HashSet<TLineaVenta>();
		
		EntityManager EM = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = EM.getTransaction();

		t.begin();
		
		Venta venta = EM.find(Venta.class, idVenta);
		if(venta != null){
			
			TVenta ventaTransfer = venta.entitityToTransfer();
			vcp.settVenta(ventaTransfer);
			for (LineaVenta lv : venta.getListalineaVentas()){
				TProducto producto = null;
				if(lv.getProducto() instanceof Alimentacion){
					Alimentacion alimento = (Alimentacion) lv.getProducto();
					producto = new TAlimentacion(alimento.getId(),alimento.getNombre(), alimento.getPrecio()
						, alimento.getStock(), alimento.getTipoAl(), alimento.getActivo(), 
							alimento.getMarcaProducto().getId());
					
				}else if(lv.getProducto() instanceof Merchadising){
					Merchadising mercha = (Merchadising) lv.getProducto();
					producto = new TMerchandising(mercha.getId(), mercha.getNombre(), mercha.getPrecio(),
							mercha.getStock(), mercha.getCategoria(), mercha.getEdicionLimitada(),
							mercha.getActivo(), mercha.getMarcaProducto().getId());
				}
				productos.add(producto);
				TLineaVenta linea = new TLineaVenta(lv.getVenta().getId(), lv.getProducto().getId(), 
						lv.getUnidades(), lv.getPrecioVenta() );
				lineasVenta.add(linea);
			}
			
			vcp.settProducto(productos);
			vcp.settLineaVenta(lineasVenta);
			try {
				t.commit();
			} catch (Exception e) {
				t.rollback();
			}		
					
		}else{
			TVenta ventaTransfer = new TVenta();
			ventaTransfer.setId(-106); //Error de no existe la venta con dicho id
			vcp.settVenta(ventaTransfer);
			t.rollback();
		}
		
		EM.close();
		return vcp;
	}

	public List<TVenta> listarVentas() {
        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); //No hay transacción porque es un listar

		TypedQuery<Venta> query = em.createNamedQuery("Negocio.VentaJPA.Venta.findAll", Venta.class);
		List<Venta> l = query.getResultList(); //Obtenemos una lista de departamentos activos
		List<TVenta> lista = new ArrayList<TVenta>();
		for(Venta v: l){
			TVenta venta = v.entitityToTransfer();
			lista.add(venta);
		}
		em.close();
		return lista;
		// end-user-code
	}

	public List<TVenta> listarVentaPorTrabajador(Integer idTrabajador) {
		List<TVenta> lista = new ArrayList<TVenta>();
		
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager(); //No hay transacción porque es un listar
		Trabajador t = em.find(Trabajador.class, idTrabajador);
		if(t == null){
			em.close();
			return null;
		}else{
			
			TypedQuery<Venta> query = em.createNamedQuery("Negocio.VentaJPA.Venta.findBytrabajador", Venta.class);
			query.setParameter("trabajador", t);
			List<Venta> l = query.getResultList(); //Obtenemos una lista de departamentos activos
			for(Venta v: l){
				TVenta venta = v.entitityToTransfer();
				lista.add(venta);
			}
		}

		em.close();
		return lista;
	}
	

	public Integer devolucionVenta(TLineaVenta tLineaVenta) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        int resultado = -1;
        
        Venta venta = em.find(Venta.class, tLineaVenta.getIdVenta());
        
		if(venta != null){
			double nuevo_precio = venta.getPrecio_total();
			resultado = venta.getId();
			Producto producto = em.find(Producto.class,tLineaVenta.getIdProducto());
			if(producto != null){
				LineaVentaID idlinea = new LineaVentaID(tLineaVenta.getIdVenta(), tLineaVenta.getIdProducto());
				LineaVenta lineaVenta = em.find(LineaVenta.class, idlinea);
				if(lineaVenta != null){
					//Comprobamos que la cantidad a devolver no es superior a la cantidad que tenia la factura
					if(tLineaVenta.getUnidades() > lineaVenta.getUnidades()){
						et.rollback();
						em.close();
						return -1; //Error de que devolvemos una cantidad superior a la que tenia la factura
					}
					
					if(!producto.getActivo()){
						producto.setActivo(true);
					}
					
					producto.setStock(producto.getStock() + tLineaVenta.getUnidades());
					venta.setPrecio_total(venta.getPrecio_total() - (tLineaVenta.getUnidades()* producto.getPrecio()));
					
					if(venta.getPrecio_total() == 0)
						venta.setActivo(false);
					
					//Modificamos las unidades de la linea de Venta
					lineaVenta.setUnidades(lineaVenta.getUnidades() - tLineaVenta.getUnidades());
					lineaVenta.setPrecioVenta(lineaVenta.getPrecioVenta() - (tLineaVenta.getUnidades()* producto.getPrecio()));
					
					if(lineaVenta.getUnidades() == 0)
						em.remove(lineaVenta);
					
					try {
						et.commit();
					} catch (Exception e) {
						et.rollback();
					}
					
				}else{
					et.rollback();
					em.close();
					return -107; //Error de que no existe la linea de Venta
				}
			}else{
				et.rollback();
				em.close();
				return -108; //Error de que no existe el producto
			}
		}else{
			et.rollback();
			em.close();
			return -106; //Error de que no existe la venta indicada
		}
		em.close();
		return resultado;
	}

	public Integer modificarVenta(TVenta venta) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        int resultado = -1;
        
        Venta v = em.find(Venta.class, venta.getId());

        if(v != null){
        	if(v.getActivo()){
        		//Debo modificar el trabajador
        		//No hago bloqueo porque puedo asignar un trabajador que este dado de baja
        		Trabajador t = em.find(Trabajador.class, venta.getIdTrabajador());
        		if(t != null){
        			v.setMetodo_pago(venta.getMetodo_pago());
        			v.setTrabajador(t);
        			try {				
    					et.commit();
    					resultado = v.getId();
    				} catch (Exception e) {
    					et.rollback();
    				}
        		}else{
        			et.rollback();
        			em.close();
        			return -100; //Error de que el trabajador no existe
        		}
        		
        		
        	}else{
        		et.rollback();
    			em.close();
    			return -109; //Error de que la venta esta inactiva
        	}
        }else{
        	et.rollback();
			em.close();
			return -106; //Error de que no existe la venta indicada
        }
        em.close();
		return resultado;
	}
}