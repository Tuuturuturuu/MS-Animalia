/**
 * 
 */
package Negocio.Especie;

import java.util.HashSet;
import java.util.Set;

import Integracion.Animal.DAOAnimal;
import Integracion.Especie.DAOEspecie;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Habitat.DAOHabitat;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Animal.TAnimal;
import Negocio.Habitat.THabitat;


public class EspecieSAImp implements EspecieSA {

	public Integer altaEspecie(TEspecie especie) {
						
		//Comprobamos si en el alta han puesto campos nulos
		if(especie.getNombreEspecie().isEmpty() || especie.getID_habitat() == 0 ){
			return -3; //Enviamos error de no se pueden dejar campos vacios en ALTA 
		}
		int exito = -1;
		
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOHabitat daoHabitat = f.getDAOHabitat();
			THabitat habitat = daoHabitat.mostarHabitat(especie.getID_habitat());
			//ESTO DESCOMENTAR CUANDO ESTE IMPLEMENTADO HABITAT
			if(habitat != null){
				
				if(habitat.isActivo()){
					DAOEspecie daoEspecie = f.getDAOEspecie();
					TEspecie especieUnica = daoEspecie.leerPorNombreUnico(especie.getNombreEspecie());
					if(especieUnica == null){ //No existe una especie con el mismo nombre, por lo tanto damos de alta sin problema
						exito = daoEspecie.altaEspecie(especie);
						t.commit();
					}else if(especieUnica.getActivo() == false){ //Comprobamos si esa especie que ya tiene el mismo nombre esta dado de baja
						//Procedemos a reactivarlo y actualizar sus valores
						especie.setID(especieUnica.getID());
						exito = daoEspecie.modificarEspecie(especie);
						t.commit();
					}else{
						//Mostramos error
						exito = -10; //Error de que ya existe una especie con el mismo nombre y activa
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

	public Integer bajaEspecie(Integer idEspecie) {
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEspecie daoEspecie = f.getDAOEspecie();
			TEspecie especie = daoEspecie.mostrarEspecie(idEspecie);
			//Comprobamos que existe una especie con ese id y que esta activa
			if (especie != null ) {
				
				if(especie.getActivo() == true){
					
					//ESTO DESCOMENTAR CUANDO ESTE IMPLEMENTADO HABITAT
					DAOAnimal daoAnimal = f.getDAOAnimal();
					Set <TAnimal> animales = daoAnimal.listarAnimalesPorEspecieActivos(idEspecie);
					if(animales.size() == 0){
						exito = daoEspecie.bajaEspecie(idEspecie);
						t.commit();
					}else{
						exito = -13; //Error de que no puedes dar de baja una especie con animales activos
						t.rollback();
					}
				}
				else{
					exito = -12; //Error de que el id es el de una especie inactiva
					t.rollback();
				}
			} else{
				exito = -11; //Error de que el id es el de una especie que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public TEspecie mostrarEspecie(Integer especie) {
		TEspecie especieMostrar = new TEspecie(0,"",0,false);
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEspecie daoEspecie = f.getDAOEspecie();
			TEspecie especieBuscar = daoEspecie.mostrarEspecie(especie);
			if (especieBuscar != null ) {
				especieMostrar.setID(especieBuscar.getID());
				especieMostrar.setNombreEspecie(especieBuscar.getNombreEspecie());
				especieMostrar.setID_habitat(especieBuscar.getID_habitat());
				especieMostrar.setActivo(especieBuscar.getActivo());
				t.commit();
			} else{
				especieMostrar.setID(-11);//Error de que el id es el de una especie que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return especieMostrar;
	}

	public Integer modificarEspecie(TEspecie especie) {
		
		//Comprobamos si en el alta han puesto campos nulos
		if(especie.getID()==0 || especie.getNombreEspecie().isEmpty() || especie.getID_habitat() == 0 ){
			return -3; //Enviamos error de no se pueden dejar campos vacios 
		}
		
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEspecie daoEspecie = f.getDAOEspecie();
			TEspecie especieBuscar = daoEspecie.mostrarEspecie(especie.getID());
			if (especieBuscar != null ) {
				
				if(especieBuscar.getActivo()){
					
					//ESTO DESCOMENTAR CUANDO ESTE IMPLEMENTADO HABITAT
					
					//Comprobamos que el id de Habitat existe y esta activo
					DAOHabitat daoHabitat = f.getDAOHabitat();
					THabitat habitat = daoHabitat.mostarHabitat(especie.getID_habitat());
					if(habitat != null){
						
					if(habitat.isActivo()){
							TEspecie especieUnica = daoEspecie.leerPorNombreUnico(especie.getNombreEspecie());
							if(especieUnica == null){ //No existe una especie con el mismo nombre, por lo tanto modificamos especie sin problema
								exito = daoEspecie.modificarEspecie(especie);
								t.commit();
							}else if(especieUnica.getActivo() == false){ //Comprobamos si esa especie que ya tiene el mismo nombre esta dado de baja
								exito = -14; //ERROR: ya existe una especie con el mismo nombre y inactivo
								t.rollback();
							}else{
								//Mostramos error
								exito = -10; //Error de que ya existe una especie con el mismo nombre y activa
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
					exito = -12; //Error de que el id es el de una especie inactiva
					t.rollback();
				}
				
			} else{
				exito = -11;//Error de que el id es el de una especie que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public Set<TEspecie> listarEspecies() {

        Set<TEspecie> especies = new HashSet<>();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOEspecie daoEspecie = f.getDAOEspecie();
			Set<TEspecie> especiesBuscar = daoEspecie.listarEspecie();
			for(TEspecie especie : especiesBuscar){
				especies.add(especie);
			}
			especiesBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return especies;
	}

	public Set<TEspecie> listarEspeciePorHabitat(Integer idHabitat) {
		Set<TEspecie> Especies = new HashSet<TEspecie>();
		TEspecie especie = new TEspecie();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			//ESTO DESCOMENTAR CUANDO ESTE IMPLEMENTADO HABITAT

			DAOHabitat daoHabitat = f.getDAOHabitat();
			THabitat habitat = daoHabitat.mostarHabitat(idHabitat);
			if(habitat != null){
				DAOEspecie daoEspecie = f.getDAOEspecie();
				Set<TEspecie> especiesBuscar = daoEspecie.listarEspeciePorHabitat(idHabitat);	
				for(TEspecie especieEncontrada : especiesBuscar){
					Especies.add(especieEncontrada);
				}
				especiesBuscar = null; //Liberamos memoria
				t.commit();
			}else{
				especie.setID(-20); //Error de que el id Habitat no existe 
				Especies.add(especie);
				t.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Especies;
	}
}