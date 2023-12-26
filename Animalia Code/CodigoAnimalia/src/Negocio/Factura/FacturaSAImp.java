
package Negocio.Factura;

import java.util.HashSet;
import java.util.Set;

import Integracion.Especie.DAOEspecie;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Factura.TLineaFactura;
import Integracion.Pase.DAOPase;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Especie.TEspecie;
import Negocio.Pase.TPase;

public class FacturaSAImp implements FacturaSA {

	public Integer cerrarFactura(TFactura factura, TCarrito carrito) {
		TransactionManager tm = TransactionManager.getInstance();
		try 
		{
			Transaction t = tm.newTransaction();
			t.start();
			FactoriaDAO fDAO = FactoriaDAO.getInstance();
			if(!carrito.getLineasFactura().isEmpty())
			{
				DAOPase daoPase = fDAO.getDAOPase();
				double precio_total = 0.0;
				for(TLineaFactura lf : carrito.getLineasFactura())
				{
					TPase pase = daoPase.mostrarPase(lf.GetIdPase());
					if(pase != null)
					{
						if(pase.getActivo())
						{
							if(lf.GetCantidad() <= pase.getCantidadDisponible())
							{
								pase.setCantidadDisponible(pase.getCantidadDisponible() - lf.GetCantidad());
								daoPase.modificarPase(pase);
								//Calculamos el precio de la linea Factura y actualizamos en la linea Factura
								double precio_lineaF = lf.GetCantidad() * pase.getPrecio();
								lf.SetPrecio(precio_lineaF);
								precio_total = precio_total +precio_lineaF ;
							}
							else
							{
								t.rollback();
								return -60;//60 No hay cantidad disponible del pase
							}
								
						}
						else
						{
							t.rollback();
							return -61; //61 Este pase no esta activo
						}
					}
					else
					{
						t.rollback();
						return -62; //62 Pase no existe
					}
				}
				DAOFactura daoFactura = fDAO.getDAOFactura();
				factura.SetPrecioTotal(precio_total);
				int id = daoFactura.crearFactura(factura);
				if(id > 0)
				{
					DAOLineaFactura daoLF = fDAO.getDAOLineaFactura();
					for(TLineaFactura lf : carrito.getLineasFactura())
					{
						//Asignamos en cada linea de factura el id de Factura generada
						lf.SetIdFactura(id);
						int r = daoLF.crearLineaFactura(lf);
						if(r < 0)
						{
							t.rollback();
							return -63; //63 Error al crear LineaFactura
						}
					}
				}
				else
				{
					t.rollback();
					return -64; //64 Error al crear factura
				}
				t.commit();
				return id;
			}
			else
			{
				t.rollback();
				return -65; // 65 El carrito esta vacio
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return -1; 
		}
		
	}

	public TFacturaConPases mostrarFactura(Integer id) {
		
		TFacturaConPases fcp = new TFacturaConPases();		
		Set<TPase> pases = new HashSet<TPase>();
		TFactura factura = new TFactura();
		try{
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO fDAO = FactoriaDAO.getInstance();
			DAOFactura daoFactura = fDAO.getDAOFactura();
			TFactura facturaBD = daoFactura.leerFactura(id);
			if(facturaBD != null){
				//Si la factura existe la agregamos al TFacturaConPases
				fcp.setFactura(facturaBD);
				
				//Conseguimos todas las lineaFactura que tengan el idFactura
				Set<TLineaFactura> lineasfacturaBD = fDAO.getDAOLineaFactura().leerTodasLineaFactura(id);				
				
				DAOPase daoPase = fDAO.getDAOPase();
				for (TLineaFactura tLineaFactura : lineasfacturaBD) {
					//Conseguimos cada pase de las lineas Factura y agregamos al set
					TPase pase = daoPase.mostrarPase(tLineaFactura.GetIdPase());
					pases.add(pase);
				}
				
				//Agregamos el conjunto de Pases
				fcp.setPases(pases);
				//Agregamos las lineaFactura a TFacturaConPases
				fcp.setLineasFactura(lineasfacturaBD);
				t.commit();
			}else{
				factura.SetId(-72); //No existe la factura con dicho id
				fcp.setFactura(factura);
				t.rollback();
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return fcp;
	}

	public Set<TFactura> listarFacturas() {
		
        Set<TFactura> facturas = new HashSet<>();
        try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOFactura daoFactura = f.getDAOFactura();
			Set<TFactura> facturasBuscar = daoFactura.leerTodasFactura();
			for(TFactura factura : facturasBuscar){
				facturas.add(factura);
			}
			facturasBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return facturas;
	}

	
	public Integer modificarFactura(TFactura factura) {
		DAOFactura daoFactura = FactoriaDAO.getInstance().getDAOFactura();
		TransactionManager tm = TransactionManager.getInstance();
		int r = -1;
		try 
		{
			Transaction t = tm.newTransaction();
			t.start();
			//Comprobamos que la factura existe
			TFactura facturaBD = daoFactura.leerFactura(factura.GetId());
			if(facturaBD != null){
				if(facturaBD.GetActivo()){
					//Conseguimos el precio anterior de la factura para no perderlo
					factura.SetPrecioTotal(facturaBD.GetPrecioTotal());
					//Conseguimos si estaba activa o no
					factura.SetActivo(facturaBD.GetActivo());
					r = daoFactura.modificarFactura(factura);
					if(r < 0){
						t.rollback();
						return -67; //67 Error al modificar Factura
					}
					t.commit();
				}else{
					t.rollback();
					return -66; //66 Factura no activa
				}
			}else{
				t.rollback();
				return -72;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return r;
		}
		return r;
	}

	public Integer devolverFactura(TLineaFactura lineaFactura) {
		TransactionManager tm = TransactionManager.getInstance();
		FactoriaDAO fDAO = FactoriaDAO.getInstance();
		int r = -1;
		try 
		{
			Transaction t = tm.newTransaction();
			t.start();
			DAOFactura daoFactura = fDAO.getDAOFactura();
			TFactura factura = daoFactura.leerFactura(lineaFactura.GetIdFactura());
			if(factura != null)
			{
				if(factura.GetActivo())
				{	
					DAOPase daoPase = fDAO.getDAOPase();
					TPase pase = daoPase.mostrarPase(lineaFactura.GetIdPase());
					if(pase != null)
					{
						DAOLineaFactura daoLF = fDAO.getDAOLineaFactura();
						TLineaFactura lf = daoLF.leerLineaFactura(lineaFactura.GetIdFactura(), lineaFactura.GetIdPase());
						if(lf != null)
						{
							//Comprobamos que la cantidad a devolver no es superior a la cantidad que tenia la factura
							if(lf.GetCantidad() <lineaFactura.GetCantidad()){
								t.rollback();
								return -75; 
							}
							if(!pase.getActivo())
								pase.setActivo(true);
							pase.setCantidadDisponible(pase.getCantidadDisponible() + lineaFactura.GetCantidad());
							r = daoPase.modificarPase(pase);
							if(r < 0)
							{
								t.rollback();
								return -68;//68 Error al modificar pase
							}
							//Si la factura pasa a tener un precioTotal = 0, la pasamos a dar de baja
							factura.SetPrecioTotal(factura.GetPrecioTotal() - (lineaFactura.GetCantidad() * pase.getPrecio()));
							if(factura.GetPrecioTotal() == 0)
								factura.SetActivo(false);
							r = daoFactura.modificarFactura(factura);
							if(r < 0)
							{
								t.rollback();
								return -67; //67 Error al modificar factura
							}
							//Se comprueba si se devuelve toda la linea factura o no
							//Actualizamos la cantidad
							lf.SetCantidad(lf.GetCantidad() - lineaFactura.GetCantidad());
							//Actualizamos el precio de la linea Factura
							lf.SetPrecio(lf.GetPrecio() - ((lineaFactura.GetCantidad() * pase.getPrecio())));
							r = daoLF.modificarLineaFactura(lf);
							if(r < 0)
							{
								t.rollback();
								return -70;// 70 Error al modificar LineaFactura
							}
							if(lf.GetCantidad() == 0)
								r = daoLF.eliminarLineaFactura(lf.GetIdFactura(), lf.GetIdPase());
							if(r < 0)
							{
								t.rollback();
								return -69;//69 Error al eliminarLineaFactura
							}
							t.commit();
							return r;
						}
						else
						{
							t.rollback();
							return -71; // 71 LineaFactura no existe
						}
					}
					else
					{
						t.rollback();
						return -62; //62 Pase no existe
					}
				}
				else
				{
					t.rollback();
					return -66; //66 Factura no activa
				}
			}
			else
			{
				t.rollback();
				return -72;// 72 Factura no existe
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return r;
		}
	}
	
}