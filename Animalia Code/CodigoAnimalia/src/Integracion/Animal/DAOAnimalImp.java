/**
 * 
 */
package Integracion.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Negocio.Animal.TAnimalNoAcuatico;

public class DAOAnimalImp implements DAOAnimal {

	@SuppressWarnings("resource")
	public Integer altaAnimal(TAnimal animal) {
		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement("INSERT INTO Animal (nombre, id_especie, tipo, activo) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, animal.getNombre());
			statement.setInt(2, animal.getId_Especie());
			statement.setInt(3, animal.getTipo());
			statement.setBoolean(4, animal.isActivo());
			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				int id = result.getInt(1);

				if (animal instanceof TAnimalAcuatico) {
					statement = c
							.prepareStatement("INSERT INTO AnimalAcuatico (id, tipoAgua, temperatura) VALUES(?,?,?)");
					statement.setInt(1, id);
					statement.setString(2, ((TAnimalAcuatico) animal).getTipoAgua());
					statement.setInt(3, ((TAnimalAcuatico) animal).getTemperatura());

					if (statement.executeUpdate() == 0) {
						statement.close();
						result.close();
						return -1;
					}
				} else if (animal instanceof TAnimalNoAcuatico) {
					statement = c.prepareStatement("INSERT INTO AnimalNoAcuatico (id, numPatas) VALUES(?,?)");
					statement.setInt(1, id);
					statement.setInt(2, ((TAnimalNoAcuatico) animal).getNumPatas());

					if (statement.executeUpdate() == 0) {
						statement.close();
						result.close();
						return -1;
					}
				}

				statement.close();
				result.close();
				return id;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public Set<TAnimal> listarAnimalesPorEspecieActivos(Integer idEspecie) {
		Set<TAnimal> animales = new HashSet<>();

		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction t = tManager.getTransaction();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM animal AS e "
					+ "LEFT JOIN animalacuatico AS el ON e.id = el.id "
					+ "LEFT JOIN animalnoacuatico AS ez ON e.id = ez.id " + "JOIN especie AS t ON e.id_especie = t.id "
					+ "WHERE t.id = ? AND e.activo = true FOR UPDATE ");

			statement.setInt(1, idEspecie);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TAnimal animal;

				if (result.getInt("tipo") == 1) {
					TAnimalAcuatico animalAcuatico = new TAnimalAcuatico();
					animalAcuatico.setId(result.getInt("id"));
					animalAcuatico.setNombre(result.getString("nombre"));
					animalAcuatico.setTipoAgua(result.getString("tipoAgua"));
					animalAcuatico.setTemperatura(result.getInt("temperatura"));
					animalAcuatico.setId_Especie(result.getInt("id_especie"));
					animalAcuatico.setActivo(result.getBoolean("activo"));

					animal = animalAcuatico;
				} else {
					TAnimalNoAcuatico animalNoAcuatico = new TAnimalNoAcuatico();
					animalNoAcuatico.setId(result.getInt("id"));
					animalNoAcuatico.setNombre(result.getString("nombre"));
					animalNoAcuatico.setNumPatas(result.getInt("numPatas"));
					animalNoAcuatico.setId_Especie(result.getInt("id_especie"));
					animalNoAcuatico.setActivo(result.getBoolean("activo"));

					animal = animalNoAcuatico;
				}

				animales.add(animal);
			}

			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return animales;
	}

	public TAnimal mostrarAnimal(Integer idAnimal) {
		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement(
					"SELECT * FROM Animal AS e JOIN AnimalAcuatico AS el ON e.id=el.id WHERE e.id=? FOR UPDATE");
			statement.setInt(1, idAnimal);
			ResultSet result = statement.executeQuery();
			TAnimal animal = null;

			if (result.next()) {
				TAnimalAcuatico animalAcuatico = new TAnimalAcuatico();
				animalAcuatico.setId(result.getInt("id"));
				animalAcuatico.setNombre(result.getString("nombre"));
				animalAcuatico.setTipoAgua(result.getString("tipoAgua"));
				animalAcuatico.setTemperatura(result.getInt("temperatura"));
				animalAcuatico.setId_Especie(result.getInt("id_especie"));
				animalAcuatico.setTipo(result.getInt("tipo"));
				animalAcuatico.setActivo(result.getBoolean("activo"));

				animal = animalAcuatico;
			} else {
				statement = c.prepareStatement(
						"SELECT * FROM Animal AS e JOIN AnimalNoAcuatico AS ez ON e.id=ez.id WHERE e.id=? FOR UPDATE");
				statement.setInt(1, idAnimal);
				result = statement.executeQuery();
				if (result.next()) {
					TAnimalNoAcuatico animalNoAcuatico = new TAnimalNoAcuatico();
					animalNoAcuatico.setId(result.getInt("id"));
					animalNoAcuatico.setNombre(result.getString("nombre"));
					animalNoAcuatico.setNumPatas(result.getInt("numPatas"));
					animalNoAcuatico.setId_Especie(result.getInt("id_especie"));
					animalNoAcuatico.setTipo(result.getInt("tipo"));
					animalNoAcuatico.setActivo(result.getBoolean("activo"));

					animal = animalNoAcuatico;
				}
			}

			result.close();
			statement.close();
			return animal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("resource")
	public Integer modificarAnimal(TAnimal animal) {
		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement("UPDATE Animal SET nombre=?, activo=? WHERE id=?");
			statement.setString(1, animal.getNombre());
			statement.setBoolean(2, animal.isActivo());
			statement.setInt(3, animal.getId());
			//statement.setInt(3, animal.getId_Especie());
			
			statement.executeUpdate();

			if (animal instanceof TAnimalAcuatico) {
				statement = c.prepareStatement("UPDATE AnimalAcuatico SET tipoAgua=?, temperatura=? WHERE id=?");
				statement.setString(1, ((TAnimalAcuatico) animal).getTipoAgua());
				statement.setInt(2, ((TAnimalAcuatico) animal).getTemperatura());
				statement.setInt(3, animal.getId());

				statement.executeUpdate();
				statement.close();

			} else if (animal instanceof TAnimalNoAcuatico) {
				statement = c.prepareStatement("UPDATE AnimalNoAcuatico SET numPatas=? WHERE id=?");
				statement.setInt(1, ((TAnimalNoAcuatico) animal).getNumPatas());
				statement.setInt(2, animal.getId());

				statement.executeUpdate();
				statement.close();
			}

			return animal.getId();
		} catch (Exception e) {
			return -1;
		}
	}
	

	public Integer modificarAnimalAcuatico(TAnimalAcuatico animal) {
		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement("UPDATE animal SET nombre=?, activo=?, id_especie=? WHERE id=?");
			statement.setString(1, animal.getNombre());
			statement.setBoolean(2, animal.isActivo());
			statement.setInt(3, animal.getId_Especie());
			statement.setInt(4, animal.getId());
			
			statement.executeUpdate();

				statement = c.prepareStatement("UPDATE animalacuatico SET tipoAgua=?, temperatura=? WHERE id=?");
				statement.setString(1, ((TAnimalAcuatico) animal).getTipoAgua());
				statement.setInt(2, ((TAnimalAcuatico) animal).getTemperatura());
				statement.setInt(3, animal.getId());

				statement.executeUpdate();
				statement.close();


			return animal.getId();
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public Integer modificarAnimalNoAcuatico(TAnimalNoAcuatico animal) {
		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement("UPDATE animal SET nombre=?, activo=?, id_especie=? WHERE id=?");
			statement.setString(1, animal.getNombre());
			statement.setBoolean(2, animal.isActivo());
			statement.setInt(3, animal.getId_Especie());
			statement.setInt(4, animal.getId());
			
			statement.executeUpdate();
			
			statement = c.prepareStatement("UPDATE animalnoacuatico SET numPatas=? WHERE id=?");
			statement.setInt(1, ((TAnimalNoAcuatico) animal).getNumPatas());
			statement.setInt(2, animal.getId());

			statement.executeUpdate();
			statement.close();
			

			return animal.getId();
		} catch (Exception e) {
			return -1;
		}
	}

	public Set<TAnimal> listarAnimales() {
		Set<TAnimal> listaAnimales = new HashSet<>();

		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement(
					"SELECT * FROM Animal AS e LEFT JOIN AnimalAcuatico AS el ON e.id=el.id LEFT JOIN AnimalNoAcuatico AS ez ON e.id=ez.id FOR UPDATE");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TAnimal animal;

				if (result.getInt("tipo") == 1) {
					TAnimalAcuatico animalAcuatico = new TAnimalAcuatico();
					animalAcuatico.setId(result.getInt("id"));
					animalAcuatico.setNombre(result.getString("nombre"));
					animalAcuatico.setTipoAgua(result.getString("tipoAgua"));
					animalAcuatico.setTemperatura(result.getInt("temperatura"));
					animalAcuatico.setId_Especie(result.getInt("id_especie"));
					animalAcuatico.setTipo(1);
					animalAcuatico.setActivo(result.getBoolean("activo"));

					animal = animalAcuatico;
				} else {
					TAnimalNoAcuatico animalNoAcuatico = new TAnimalNoAcuatico();
					animalNoAcuatico.setId(result.getInt("id"));
					animalNoAcuatico.setNombre(result.getString("nombre"));
					animalNoAcuatico.setNumPatas(result.getInt("numPatas"));
					animalNoAcuatico.setId_Especie(result.getInt("id_especie"));
					animalNoAcuatico.setTipo(0);
					animalNoAcuatico.setActivo(result.getBoolean("activo"));

					animal = animalNoAcuatico;
				}

				listaAnimales.add(animal);
			}

			result.close();
			statement.close();

			return listaAnimales;
		} catch (Exception e) {
			return null;
		}

	}
	
	public Set<TAnimal> listarAnimalesAcuaticos() {
		Set<TAnimal> listaAnimales = new HashSet<>();

		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement(
					"SELECT * FROM Animal AS e LEFT JOIN AnimalAcuatico AS el ON e.id=el.id LEFT JOIN AnimalNoAcuatico AS ez ON e.id=ez.id FOR UPDATE");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				
				if (result.getInt("tipo") == 1) {
					TAnimalAcuatico animalAcuatico = new TAnimalAcuatico();
					animalAcuatico.setId(result.getInt("id"));
					animalAcuatico.setNombre(result.getString("nombre"));
					animalAcuatico.setTipoAgua(result.getString("tipoAgua"));
					animalAcuatico.setTemperatura(result.getInt("temperatura"));
					animalAcuatico.setId_Especie(result.getInt("id_especie"));
					animalAcuatico.setTipo(1);
					animalAcuatico.setActivo(result.getBoolean("activo"));

					listaAnimales.add(animalAcuatico);
				}

			}

			result.close();
			statement.close();

			return listaAnimales;
		} catch (Exception e) {
			return null;
		}

	}
	
	public Set<TAnimal> listarAnimalesNoAcuaticos() {
		Set<TAnimal> listaAnimales = new HashSet<>();

		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement(
					"SELECT * FROM Animal AS e LEFT JOIN AnimalAcuatico AS el ON e.id=el.id LEFT JOIN AnimalNoAcuatico AS ez ON e.id=ez.id FOR UPDATE");
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				if (result.getInt("tipo") == 0) {
					TAnimalNoAcuatico animalNoAcuatico = new TAnimalNoAcuatico();
					animalNoAcuatico.setId(result.getInt("id"));
					animalNoAcuatico.setNombre(result.getString("nombre"));
					animalNoAcuatico.setNumPatas(result.getInt("numPatas"));
					animalNoAcuatico.setId_Especie(result.getInt("id_especie"));
					animalNoAcuatico.setTipo(0);
					animalNoAcuatico.setActivo(result.getBoolean("activo"));

					listaAnimales.add(animalNoAcuatico);
				}

			}

			result.close();
			statement.close();

			return listaAnimales;
		} catch (Exception e) {
			return null;
		}

	}

	public Set<TAnimal> listarAnimalesPorEspecie(Integer idEspecie) {
		Set<TAnimal> animalesEnEspecie = new HashSet<>();

		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();
			String query = "SELECT * FROM Animal AS a LEFT JOIN animalnoacuatico AS an ON a.id = an.id LEFT JOIN animalacuatico as aa ON a.id = aa.id WHERE id_especie = ? FOR UPDATE";

			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idEspecie);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TAnimal animal;

				if (result.getInt("tipo") == 1) {
					TAnimalAcuatico animalAcuatico = new TAnimalAcuatico();
					animalAcuatico.setId(result.getInt("id"));
					animalAcuatico.setNombre(result.getString("nombre"));
					animalAcuatico.setId_Especie(result.getInt("id_especie"));
					animalAcuatico.setActivo(result.getBoolean("activo"));
					animalAcuatico.setTipo(1);
					
					animalAcuatico.setTipoAgua(result.getString("tipoAgua"));
					animalAcuatico.setTemperatura(result.getInt("temperatura"));
					

					animal = animalAcuatico;
				} else {
					TAnimalNoAcuatico animalNoAcuatico = new TAnimalNoAcuatico();
					animalNoAcuatico.setId(result.getInt("id"));
					animalNoAcuatico.setNombre(result.getString("nombre"));
					animalNoAcuatico.setId_Especie(result.getInt("id_especie"));
					animalNoAcuatico.setActivo(result.getBoolean("activo"));
					animalNoAcuatico.setTipo(0);
					
					animalNoAcuatico.setNumPatas(result.getInt("numPatas"));

					animal = animalNoAcuatico;
				}

				animalesEnEspecie.add(animal);
			}

			result.close();
			statement.close();

			return animalesEnEspecie;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Integer bajaAnimal(Integer idAnimal) {
		try {

			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement("UPDATE Animal SET activo=false WHERE id=?");
			statement.setInt(1, idAnimal);

			statement.executeUpdate();
			statement.close();
			return idAnimal;
		} catch (Exception e) {
			return -1;
		}
	}

	public TAnimal leerPorNombreUnico(String nombreAnimal) {
		TAnimal animal = null;
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement(
					"SELECT * FROM Animal AS e JOIN AnimalAcuatico AS el ON e.id=el.id WHERE e.nombre=? FOR UPDATE");
			statement.setString(1, nombreAnimal);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				TAnimalAcuatico animalAcuatico = new TAnimalAcuatico();
				animalAcuatico.setId(result.getInt("id"));
				animalAcuatico.setNombre(result.getString("nombre"));
				animalAcuatico.setTipoAgua(result.getString("tipoAgua"));
				animalAcuatico.setTemperatura(result.getInt("temperatura"));
				animalAcuatico.setId_Especie(result.getInt("id_especie"));
				animalAcuatico.setTipo(result.getInt("tipo"));
				animalAcuatico.setActivo(result.getBoolean("activo"));

				animal = animalAcuatico;
			} else {
				statement = c.prepareStatement(
						"SELECT * FROM Animal AS e JOIN AnimalNoAcuatico AS ez ON e.id=ez.id WHERE e.nombre=? FOR UPDATE");
				statement.setString(1, nombreAnimal);
				result = statement.executeQuery();
				if (result.next()) {
					TAnimalNoAcuatico animalNoAcuatico = new TAnimalNoAcuatico();
					animalNoAcuatico.setId(result.getInt("id"));
					animalNoAcuatico.setNombre(result.getString("nombre"));
					animalNoAcuatico.setNumPatas(result.getInt("numPatas"));
					animalNoAcuatico.setId_Especie(result.getInt("id_especie"));
					animalNoAcuatico.setTipo(result.getInt("tipo"));
					animalNoAcuatico.setActivo(result.getBoolean("activo"));

					animal = animalNoAcuatico;
				}
			}

			result.close();
			statement.close();
			return animal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}











