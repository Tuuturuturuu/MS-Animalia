package Negocio.Pase;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Habitat.DAOHabitat;
import Integracion.Pase.DAOPase;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Habitat.THabitat;


public class PaseSAImp implements PaseSA{

	@Override
	public Integer Alta(TPase pase) {
		//Comprobaciones de el formato de los datos
		
				//Comprobamos si en el alta han puesto campos nulos
				if(pase.getPrecio()==0 || pase.getFecha().equals(null) || pase.getHora().equals(null) || pase.getCantidadDisponible()==0 || pase.getIDHabitat() == 0 ){
					return -3; //Enviamos error de no se pueden dejar campos vacios en ALTA 
				}
				int exito = -1;
				
				try {
					TransactionManager transaction = TransactionManager.getInstance();
					Transaction t = transaction.newTransaction();
					t.start();
					FactoriaDAO f = FactoriaDAO.getInstance();
					DAOHabitat daoHabitat = f.getDAOHabitat();
					THabitat habitat = daoHabitat.mostarHabitat(pase.getIDHabitat());					
					//ESTO DESCOMENTAR CUANDO ESTE IMPLEMENTADO HABITAT
					if(habitat != null){
						
						if(habitat.isActivo()){
							DAOPase daoPase = f.getDAOPase();
							TPase paseUnico = daoPase.leerPorCampoUnico(pase.getFecha(), pase.getHora(), pase.getIDHabitat());
							if(paseUnico == null){ //No existe un pase con el mismo campo unico, por lo tanto damos de alta sin problema
								exito = daoPase.altaPase(pase);
								t.commit();
							}else if(paseUnico.getActivo() == false){ //Comprobamos si ese pase que ya tiene el mismo campo unico esta dado de baja
								//Procedemos a reactivarlo y actualizar sus valores
								pase.setID(paseUnico.getID());
								exito = daoPase.modificarPase(pase);
								t.commit();
							}else{
								//Mostramos error
								exito = -50; //Error de que ya existe un pase  con el mismo campo unico
							}
						}else{
							exito = -21; //Error de que el id Habitat no esta Activo
						}
						
					}else{
						exito = -20; //Error de que el id Habitat no existe 
						t.rollback();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return exito;
	}

	@Override
	public Integer Baja(Integer id) {
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOPase daoPase = f.getDAOPase();
			TPase pase = daoPase.mostrarPase(id);
			//Comprobamos que existe un pase con ese id y que este activo
			if (pase != null ) {
				
				if(pase.getActivo() == true){
					exito = daoPase.bajaPase(id);
					t.commit();
				}
				else{
					exito = -52; //Error de que el id es el de un pase inactivo
					t.rollback();
				}
			} else{
				exito = -51; //Error de que el id es el de un pase que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	@Override
	public Integer Modificar(TPase pase) {
		
		//Comprobamos si en el alta han puesto campos nulos
				if(pase.getID()==0 || pase.getIDHabitat()==0 || pase.getFecha() == null || pase.getHora() == null || pase.getCantidadDisponible() == 0 || pase.getPrecio() == 0 ){
					return -3; //Enviamos error de no se pueden dejar campos vacios 
				}
				
				int exito = -1;
				try {
					TransactionManager transaction = TransactionManager.getInstance();
					Transaction t = transaction.newTransaction();
					t.start();
					FactoriaDAO f = FactoriaDAO.getInstance();
					DAOPase daoPase = f.getDAOPase();
					TPase paseBuscar = daoPase.mostrarPase(pase.getID());
					if (paseBuscar != null ) {
						
						if(paseBuscar.getActivo()){
							
							//ESTO DESCOMENTAR CUANDO ESTE IMPLEMENTADO HABITAT
							
							//Comprobamos que el id de Habitat existe y esta activo
							DAOHabitat daoHabitat = f.getDAOHabitat();
							THabitat habitat = daoHabitat.mostarHabitat(pase.getIDHabitat());
							if(habitat != null){
								
								if(habitat.isActivo()){
										TPase paseUnico = daoPase.leerPorCampoUnico(pase.getFecha(),pase.getHora(), pase.getIDHabitat());
										if(paseUnico == null){ //No existe un pase con los mismos datos, por lo tanto modificamos pase sin problema
											exito = daoPase.modificarPase(pase);
											t.commit();
										}else if(paseUnico.getActivo() == false){ //Comprobamos si ese pase que ya tiene los mismos datos esta dado de baja
											exito = -53;
											t.rollback();
										}else{
											//Mostramos error
											exito = -50; //Error de que ya existe un pase con los mismos datos y activo
											t.rollback();
										}
								}else{
									exito = -21; //Error de que el id Habitat no esta Activo
									t.rollback();
								}
								
							}else{
								exito = -20; //Error de que el id Habitat no existe 
								t.rollback();
							}
							
							
						}else{
							exito = -52; //Error de que el id es el de un pase inactivo
							t.rollback();
						}
						
					} else{
						exito = -51;//Error de que el id es el de un pase que no existe 
						t.rollback();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return exito;
	}

	@Override
	public TPase Mostrar(Integer id) {
		TPase paseMostrar = new TPase();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOPase daoPase = f.getDAOPase();
			TPase paseBuscar = daoPase.mostrarPase(id);
			if (paseBuscar != null ) {
				paseMostrar.setID(paseBuscar.getID());
				paseMostrar.setCantidadDisponible(paseBuscar.getCantidadDisponible());
				paseMostrar.setFecha(paseBuscar.getFecha());
				paseMostrar.setHora(paseBuscar.getHora());
				paseMostrar.setPrecio(paseBuscar.getPrecio());
				paseMostrar.setIDHabitat(paseBuscar.getIDHabitat());
				paseMostrar.setActivo(paseBuscar.getActivo());
				t.commit();
			} else{
				paseMostrar.setID(-51);//Error de que el id es el de un pase que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return paseMostrar;
	}

	@Override
	public Set<TPase> Listar() {
		 Set<TPase> pases = new HashSet<>();
			try {
				TransactionManager transaction = TransactionManager.getInstance();
				Transaction t = transaction.newTransaction();
				t.start();
				FactoriaDAO f = FactoriaDAO.getInstance();
				DAOPase daoPase = f.getDAOPase();
				Set<TPase> PasesBuscar = daoPase.listarPases();
				for(TPase pase : PasesBuscar){
					pases.add(pase);
				}
				PasesBuscar = null; //Liberamos memoria
				t.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return pases;
	}

	@Override
	public Set<TPase> ListarPasePorHabitat(Integer idHabitat) {
		Set<TPase> pases = new HashSet<TPase>();
		TPase pase = new TPase();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			//ESTO DESCOMENTAR CUANDO ESTE IMPLEMENTADO HABITAT

			DAOHabitat daoHabitat = f.getDAOHabitat();
			THabitat habitat = daoHabitat.mostarHabitat(idHabitat);
			if(habitat != null){
				DAOPase daoPase = f.getDAOPase();
				Set<TPase> pasesBuscar = daoPase.listarPasesPorHabitat(idHabitat);	
				for(TPase paseEncontrado : pasesBuscar){
					pases.add(paseEncontrado);
				}
				pasesBuscar = null; //Liberamos memoria
				t.commit();
			}else{
				pase.setID(-20); //Error de que el id Habitat no existe 
				pases.add(pase);
				t.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pases;
	}
}