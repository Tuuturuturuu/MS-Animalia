/**
 * 
 */
package Negocio.Animal;

import java.util.HashSet;
import java.util.Set;

import Integracion.Animal.DAOAnimal;
import Integracion.Especie.DAOEspecie;
import Integracion.FactoriaDAO.FactoriaDAO;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Especie.TEspecie;

public class AnimalSAImp implements AnimalSA {

	public Integer altaAnimal(TAnimal animal) {
		//Comprobaciones de el formato de los datos
				//Comprobamos si en el alta han puesto campos nulos
				if(animal.getNombre().isEmpty() || animal.getId_Especie() == 0 ){
					return -3; //Enviamos error de no se pueden dejar campos vacios en ALTA 
				}
				int exito = -1;
				
				try {
					TransactionManager transaction = TransactionManager.getInstance();
					Transaction t = transaction.newTransaction();
					t.start();
					FactoriaDAO f = FactoriaDAO.getInstance();
					DAOEspecie daoEspecie = f.getDAOEspecie();
					TEspecie especie = daoEspecie.mostrarEspecie(animal.getId_Especie());
					if(especie != null){
						
						if(especie.isActivo()){
							DAOAnimal daoAnimal = f.getDAOAnimal();
							TAnimal animalUnica2 = daoAnimal.leerPorNombreUnico(animal.getNombre()); // TODO: ERROR EN LAS TRANSACCIONES
							if(animalUnica2 == null){ //No existe una especie con el mismo nombre, por lo tanto damos de alta sin problema
								exito = daoAnimal.altaAnimal(animal);
								t.commit();
							}else if(animalUnica2.isActivo() == false){ //Comprobamos si esa especie que ya tiene el mismo nombre esta dado de baja
								//Procedemos a reactivarlo y actualizar sus valores
								animal.setId(animalUnica2.getId());
								//Comprobamos si el tipo es del mismo anterior o esta reactivando un animal de otro tipo
								if(animalUnica2.getTipo() == animal.getTipo()){ //Si son del mismo tipo lo podemos reactivar
									exito = daoAnimal.modificarAnimal(animal);
									t.commit();
								}else{//Si no son del mismo tipo debemos mostrar un error
									exito = -30; 
									t.rollback();
								}								
							}else{
								//Mostramos error
								exito = -31; //Error de que ya existe una animal con el mismo nombre y activa
								t.rollback();
							}
						}else{
							exito = -12; //Error de que el id especie no esta Activo
							t.rollback();
						}
					}else{
						exito = -11; //Error de que el id especie no existe 
						t.rollback();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return exito;
	}

	public Integer bajaAnimal(Integer idAnimal) {
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOAnimal daoAnimal = f.getDAOAnimal();
			TAnimal animal = daoAnimal.mostrarAnimal(idAnimal);
			//Comprobamos que existe un Animal con ese id y que esta activo
			if (animal != null ) {
				
				if(animal.isActivo() == true){
						exito = daoAnimal.bajaAnimal(idAnimal);
						t.commit();
				}
				else{
					exito = -32; //Error de que el id es el de un animal inactivo
					t.rollback();
				}
			} else{
				exito = -33; //Error de que el id es el de una animal que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public Integer modificarAnimalAcuatico(TAnimalAcuatico animal) {
		//Comprobamos si en el alta han puesto campos nulos
				if(animal.getId()==0 || animal.getNombre().isEmpty() || animal.getId_Especie() == 0 ){
					return -3; //Enviamos error de no se pueden dejar campos vacios 
				}
				
				int exito = -1;
				try {
					TransactionManager transaction = TransactionManager.getInstance();
					Transaction t = transaction.newTransaction();
					t.start();
					FactoriaDAO f = FactoriaDAO.getInstance();
					DAOAnimal daoAnimal = f.getDAOAnimal();
					TAnimal animalBuscar = daoAnimal.mostrarAnimal(animal.getId());
					if (animalBuscar != null ) {
						
						if(animalBuscar.isActivo()){
							
							DAOEspecie daoEspecie = f.getDAOEspecie();
							TEspecie especie = daoEspecie.mostrarEspecie(animal.getId_Especie());
							if(especie != null){
								if(especie.isActivo()){
									TAnimal animalUnica = daoAnimal.leerPorNombreUnico(animal.getNombre());
									if(animalUnica == null){ //No existe una animal con el mismo nombre, por lo tanto modificamos especie sin problema
										exito = daoAnimal.modificarAnimalAcuatico(animal);
										t.commit();
									}else if(animalUnica.isActivo() == false){ //Comprobamos si esa animal que ya tiene el mismo nombre esta dado de baja
										exito = -34;
										t.rollback();
									}else{
										//Mostramos error
										exito = -31; //Error de que ya existe una animal con el mismo nombre y activa
										t.rollback();
									}
								}else{
									exito = -12; //Error de que el id ESPECIE no esta Activo
									t.rollback();
								}
								
							}else{
								exito = -11; //Error de que el id especie no existe 
								t.rollback();
							}
						}else{
							exito = -32; //Error de que el id es el de una animal inactiva
							t.rollback();
						}
						
					} else{
						exito = -33;//Error de que el id es el de una animal que no existe 
						t.rollback();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return exito;
	}
	
	@Override
	public Integer modificarAnimalNoAcuatico(TAnimalNoAcuatico animal) {
		//Comprobamos si en el alta han puesto campos nulos
		if(animal.getId()==0 || animal.getNombre().isEmpty() || animal.getId_Especie() == 0 ){
			return -3; //Enviamos error de no se pueden dejar campos vacios 
		}
		
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOAnimal daoAnimal = f.getDAOAnimal();
			TAnimal animalBuscar = daoAnimal.mostrarAnimal(animal.getId());
			if (animalBuscar != null ) {
				
				if(animalBuscar.isActivo()){
					
					DAOEspecie daoEspecie = f.getDAOEspecie();
					TEspecie especie = daoEspecie.mostrarEspecie(animal.getId_Especie());
					if(especie != null){
							if(especie.isActivo()){
								TAnimal animalUnica = daoAnimal.leerPorNombreUnico(animal.getNombre());
								if(animalUnica == null){ //No existe una animal con el mismo nombre, por lo tanto modificamos especie sin problema
									exito = daoAnimal.modificarAnimalNoAcuatico(animal);
									t.commit();
								}else if(animalUnica.isActivo() == false){ //Comprobamos si esa animal que ya tiene el mismo nombre esta dado de baja
									exito = -34;
									t.rollback();
								}else{
									//Mostramos error
									exito = -31; //Error de que ya existe una animal con el mismo nombre y activa
									t.rollback();
								}
							}else{
								exito = -12; //Error de que el id ESPECIE no esta Activo
								t.rollback();
							}
						
						}else{
							exito = -11; //Error de que el id especie no existe 
							t.rollback();
						}
				}else{
					exito = -32; //Error de que el id es el de una animal inactiva
					t.rollback();
				}
				
			} else{
				exito = -33;//Error de que el id es el de una animal que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public TAnimal mostrarAnimal(Integer idAnimal) {
		TAnimal animalMostrar = new TAnimal(0,"",0,0,false);
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOAnimal daoAnimal = f.getDAOAnimal();
			TAnimal animalBuscar = daoAnimal.mostrarAnimal(idAnimal);
			if (animalBuscar != null ) {
				animalMostrar.setId(animalBuscar.getId());
				animalMostrar.setNombre(animalBuscar.getNombre());
				animalMostrar.setId_Especie(animalBuscar.getId_Especie());
				animalMostrar.setTipo(animalBuscar.getTipo());
				animalMostrar.setActivo(animalBuscar.isActivo());
				t.commit();
			} else{
				animalMostrar.setId(-33);//Error de que el id es el de un animal que no existe 
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return animalMostrar;
	}

	public Set<TAnimal> listarAnimales() {
		Set<TAnimal> animales = new HashSet<>();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOAnimal daoAnimal = f.getDAOAnimal();
			Set<TAnimal> animalesBuscar = daoAnimal.listarAnimales();
			for(TAnimal animal : animalesBuscar){
				animales.add(animal);
			}
			animalesBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return animales;
	}
	
	public Set<TAnimal> listarAnimalesAcuaticos() {
		Set<TAnimal> animales = new HashSet<>();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOAnimal daoAnimal = f.getDAOAnimal();
			Set<TAnimal> animalesBuscar = daoAnimal.listarAnimalesAcuaticos();
			for(TAnimal animal : animalesBuscar){
				animales.add(animal);
			}
			animalesBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return animales;
	}
	
	public Set<TAnimal> listarAnimalesNoAcuaticos() {
		Set<TAnimal> animales = new HashSet<>();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();
			DAOAnimal daoAnimal = f.getDAOAnimal();
			Set<TAnimal> animalesBuscar = daoAnimal.listarAnimalesNoAcuaticos();
			for(TAnimal animal : animalesBuscar){
				animales.add(animal);
			}
			animalesBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return animales;
	}

	public Set<TAnimal> listarAnimalesPorEspecie(Integer idEspecie) {
		Set<TAnimal> Animales = new HashSet<TAnimal>();
		TAnimal animal = new TAnimal();
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.newTransaction();
			t.start();
			FactoriaDAO f = FactoriaDAO.getInstance();

			DAOEspecie daoEspecie = f.getDAOEspecie();
			TEspecie especie = daoEspecie.mostrarEspecie(idEspecie);
			if(especie != null){
				DAOAnimal daoAnimal = f.getDAOAnimal();
				Set<TAnimal> animalesBuscar = daoAnimal.listarAnimalesPorEspecie(idEspecie);	
				for(TAnimal animalEncontrada : animalesBuscar){
					Animales.add(animalEncontrada);
				}
				animalesBuscar = null; //Liberamos memoria
				t.commit();
			}else{
				animal.setId(-11); //Error de que el id de la especie no existe
				Animales.add(animal);
				t.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Animales;
	}

	
}