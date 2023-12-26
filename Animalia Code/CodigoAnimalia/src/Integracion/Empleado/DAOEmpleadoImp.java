package Integracion.Empleado;

import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class DAOEmpleadoImp implements DAOEmpleado {

    @SuppressWarnings("resource")
	@Override
    public Integer alta(TEmpleado empleado) {

        try {
            TransactionManager tm = TransactionManager.getInstance();
            Transaction t = tm.getTransaction();
            Connection c = (Connection) t.getResource();
            
            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO empleado (nombre, dni, sueldoBase, telefono, activo, tipo) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getDni());
            statement.setDouble(3, empleado.getSueldoBase());
            statement.setInt(4, empleado.getTelefono());
            statement.setBoolean(5, empleado.getActivo());
            statement.setInt(6, empleado.getTipo());
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);

                if (empleado instanceof TEmpleadoLimpieza) {
                    statement = c.prepareStatement(
                            "INSERT INTO empleadolimpieza (id, suplemento, zona) VALUES(?,?,?)");
                    statement.setInt(1, id);
                    statement.setDouble(2, ((TEmpleadoLimpieza) empleado).getSuplemento());
                    statement.setString(3, ((TEmpleadoLimpieza) empleado).getZona());

                    if (statement.executeUpdate() == 0) {
                        statement.close();
                        result.close();
                        return -1;
                    }
                } else if (empleado instanceof TEmpleadoZoologico) {
                    statement = c.prepareStatement(
                            "INSERT INTO empleadozoologico (id, especialidad, tasa, experiencia) VALUES(?,?,?,?)");
                    statement.setInt(1, id);
                    statement.setString(2, ((TEmpleadoZoologico) empleado).getEspecialidad());
                    statement.setDouble(3, ((TEmpleadoZoologico) empleado).getTasa());
                    statement.setInt(4, ((TEmpleadoZoologico) empleado).getExperiencia());

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

    @Override
    public Integer baja(Integer idEmpleado) {
		try {

			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();
	
			PreparedStatement statement = c.prepareStatement("UPDATE empleado SET activo=false WHERE id=?");
			statement.setInt(1, idEmpleado);

			statement.executeUpdate();
			statement.close();
			return idEmpleado;
		} catch (Exception e) {
			return -1;
		}
    }

    @Override
    public TEmpleado mostrar(Integer idEmpleado) {

    	try {
    		TransactionManager tm = TransactionManager.getInstance();
    		Transaction t = tm.getTransaction();
    		Connection c = (Connection) t.getResource();

    		PreparedStatement statement = c.prepareStatement(
    				"SELECT * FROM empleado AS e JOIN empleadolimpieza AS el ON e.id=el.id WHERE e.id=? FOR UPDATE");
    		statement.setInt(1, idEmpleado);
    		ResultSet result = statement.executeQuery();
    		TEmpleado empleado = null;

    		if (result.next()) {
    			TEmpleadoLimpieza empleadoLimpieza = new TEmpleadoLimpieza();
    			empleadoLimpieza.setId(result.getInt("id"));
    			empleadoLimpieza.setNombre(result.getString("nombre"));
    			empleadoLimpieza.setDni(result.getString("dni"));
    			empleadoLimpieza.setSueldoBase(result.getDouble("sueldoBase"));
    			empleadoLimpieza.setTelefono(result.getInt("telefono"));
    			empleadoLimpieza.setActivo(result.getBoolean("activo"));
    			empleadoLimpieza.setTipo(result.getInt("tipo"));
    			empleadoLimpieza.setSuplemento(result.getDouble("suplemento"));
    			empleadoLimpieza.setZona(result.getString("zona"));

    			empleado = empleadoLimpieza;
    		} else {
    			statement = c.prepareStatement(
    					"SELECT * FROM empleado AS e JOIN empleadozoologico AS ez ON e.id=ez.id WHERE e.id=? FOR UPDATE");
    			statement.setInt(1, idEmpleado);
    			result = statement.executeQuery();
    			if(result.next())
    			{
    				TEmpleadoZoologico empleadoZoologico = new TEmpleadoZoologico();
    				empleadoZoologico.setId(result.getInt("id"));
    				empleadoZoologico.setNombre(result.getString("nombre"));
    				empleadoZoologico.setDni(result.getString("dni"));
    				empleadoZoologico.setSueldoBase(result.getDouble("sueldoBase"));
    				empleadoZoologico.setTelefono(result.getInt("telefono"));
    				empleadoZoologico.setActivo(result.getBoolean("activo"));
    				empleadoZoologico.setTipo(result.getInt("tipo"));
    				empleadoZoologico.setEspecialidad(result.getString("especialidad"));
    				empleadoZoologico.setTasa(result.getDouble("tasa"));
    				empleadoZoologico.setExperiencia(result.getInt("experiencia"));

    				empleado = empleadoZoologico;
    			}
    		}

    		result.close();
    		statement.close();
    		return empleado;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    @SuppressWarnings("resource")
	@Override
    public Integer modificar(TEmpleado empleado) {

        try {
            TransactionManager tm = TransactionManager.getInstance();
            Transaction t = tm.getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement(
                    "UPDATE empleado SET nombre=?, dni=?, sueldoBase=?, telefono=?, activo=? WHERE id=?");
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getDni());
            statement.setDouble(3, empleado.getSueldoBase());
            statement.setInt(4, empleado.getTelefono());
            statement.setBoolean(5, empleado.getActivo());
            statement.setInt(6, empleado.getId());
            statement.executeUpdate();

            if (empleado instanceof TEmpleadoLimpieza) {
                statement = c.prepareStatement("UPDATE empleadolimpieza SET suplemento=?, zona=? WHERE id=?");
                statement.setDouble(1, ((TEmpleadoLimpieza) empleado).getSuplemento());
                statement.setString(2, ((TEmpleadoLimpieza) empleado).getZona());
                statement.setInt(3, empleado.getId());

                statement.executeUpdate();
                statement.close();

            } else if (empleado instanceof TEmpleadoZoologico) {
                statement = c.prepareStatement(
                        "UPDATE empleadozoologico SET especialidad=?, tasa=?, experiencia=? WHERE id=?");
                statement.setString(1, ((TEmpleadoZoologico) empleado).getEspecialidad());
                statement.setDouble(2, ((TEmpleadoZoologico) empleado).getTasa());
                statement.setInt(3, ((TEmpleadoZoologico) empleado).getExperiencia());
                statement.setInt(4, empleado.getId());

                statement.executeUpdate();
                statement.close();
            }

            return empleado.getId();
        } catch (Exception e) {
            return -1;
        }

    }

    @Override
    public Set<TEmpleado> listarEmpleados() {
        Set<TEmpleado> listaEmpleados = new HashSet<>();

        try {
            TransactionManager tm = TransactionManager.getInstance();
            Transaction t = tm.getTransaction();
            Connection c = (Connection) t.getResource();

            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM empleado AS e LEFT JOIN empleadoLimpieza AS el ON e.id=el.id LEFT JOIN empleadozoologico AS ez ON e.id=ez.id FOR UPDATE");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TEmpleado empleado;

                if (result.getInt("tipo") == 0) {
                    TEmpleadoLimpieza empleadoLimpieza = new TEmpleadoLimpieza();
                    empleadoLimpieza.setId(result.getInt("id"));
                    empleadoLimpieza.setNombre(result.getString("nombre"));
                    empleadoLimpieza.setDni(result.getString("dni"));
                    empleadoLimpieza.setSueldoBase(result.getDouble("sueldoBase"));
                    empleadoLimpieza.setTelefono(result.getInt("telefono"));
                    empleadoLimpieza.setActivo(result.getBoolean("activo"));
                    empleadoLimpieza.setTipo(0);
                    empleadoLimpieza.setSuplemento(result.getDouble("suplemento"));
                    empleadoLimpieza.setZona(result.getString("zona"));

                    empleado = empleadoLimpieza;
                } else {
                    TEmpleadoZoologico empleadoZoologico = new TEmpleadoZoologico();
                    empleadoZoologico.setId(result.getInt("id"));
                    empleadoZoologico.setNombre(result.getString("nombre"));
                    empleadoZoologico.setDni(result.getString("dni"));
                    empleadoZoologico.setSueldoBase(result.getDouble("sueldoBase"));
                    empleadoZoologico.setTelefono(result.getInt("telefono"));
                    empleadoZoologico.setActivo(result.getBoolean("activo"));
                    empleadoZoologico.setTipo(1);
                    empleadoZoologico.setEspecialidad(result.getString("especialidad"));
                    empleadoZoologico.setTasa(result.getDouble("tasa"));
                    empleadoZoologico.setExperiencia(result.getInt("experiencia"));

                    empleado = empleadoZoologico;
                }

                listaEmpleados.add(empleado);
            }

            result.close();
            statement.close();

            return listaEmpleados;
        } catch (Exception e) {
            return null;
        }


    }

    @Override
    public Set<TEmpleado> listarPorHabitat(Integer idHabitat) {
        Set<TEmpleado> empleadosEnHabitat = new HashSet<>();

        try {
    		TransactionManager tm = TransactionManager.getInstance();
    		Transaction t = tm.getTransaction();
    		Connection c = (Connection) t.getResource();
                String query = "SELECT * FROM empleado AS e "+
                		" LEFT JOIN empleadolimpieza AS el ON e.id = el.id "+
                		" LEFT JOIN empleadozoologico AS ez ON e.id = ez.id "+
                		" JOIN trabajo AS t ON e.id = t.id_empleado "+
                		" WHERE t.id_habitat = ? ";
;

                PreparedStatement statement = c.prepareStatement(query);
                statement.setInt(1, idHabitat);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    TEmpleado empleado;

                    if (result.getString("especialidad") == null) {
                        TEmpleadoLimpieza empleadoLimpieza = new TEmpleadoLimpieza();
                        empleadoLimpieza.setId(result.getInt("id"));
                        empleadoLimpieza.setNombre(result.getString("nombre"));
                        empleadoLimpieza.setDni(result.getString("dni"));
                        empleadoLimpieza.setSueldoBase(result.getDouble("sueldoBase"));
                        empleadoLimpieza.setTelefono(result.getInt("telefono"));
                        empleadoLimpieza.setActivo(result.getBoolean("activo"));
                        empleadoLimpieza.setSuplemento(result.getDouble("suplemento"));
                        empleadoLimpieza.setZona(result.getString("zona"));

                        empleado = empleadoLimpieza;
                    } else {
                        TEmpleadoZoologico empleadoZoologico = new TEmpleadoZoologico();
                        empleadoZoologico.setId(result.getInt("id"));
                        empleadoZoologico.setNombre(result.getString("nombre"));
                        empleadoZoologico.setDni(result.getString("dni"));
                        empleadoZoologico.setSueldoBase(result.getDouble("sueldoBase"));
                        empleadoZoologico.setTelefono(result.getInt("telefono"));
                        empleadoZoologico.setActivo(result.getBoolean("activo"));
                        empleadoZoologico.setEspecialidad(result.getString("especialidad"));
                        empleadoZoologico.setTasa(result.getDouble("tasa"));
                        empleadoZoologico.setExperiencia(result.getInt("experiencia"));

                        empleado = empleadoZoologico;
                    }

                    empleadosEnHabitat.add(empleado);
                }

                result.close();
                statement.close();

                return empleadosEnHabitat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public TEmpleado leerPorDniUnico(String dniEmpleado) {

    	try {
    		TransactionManager tm = TransactionManager.getInstance();
    		Transaction t = tm.getTransaction();
    		Connection c = (Connection) t.getResource();

    		PreparedStatement statement = c.prepareStatement(
    				"SELECT * FROM empleado AS e JOIN empleadolimpieza AS el ON e.id=el.id WHERE e.dni=? FOR UPDATE");
    		statement.setString(1, dniEmpleado);
    		ResultSet result = statement.executeQuery();
    		TEmpleado empleado = null;

    		if (result.next()) {
    			TEmpleadoLimpieza empleadoLimpieza = new TEmpleadoLimpieza();
    			empleadoLimpieza.setId(result.getInt("id"));
    			empleadoLimpieza.setNombre(result.getString("nombre"));
    			empleadoLimpieza.setDni(result.getString("dni"));
    			empleadoLimpieza.setSueldoBase(result.getDouble("sueldoBase"));
    			empleadoLimpieza.setTelefono(result.getInt("telefono"));
    			empleadoLimpieza.setTipo(result.getInt("tipo"));
    			empleadoLimpieza.setActivo(result.getBoolean("activo"));
    			empleadoLimpieza.setSuplemento(result.getDouble("suplemento"));
    			empleadoLimpieza.setZona(result.getString("zona"));

    			empleado = empleadoLimpieza;
    		} else {
    			statement = c.prepareStatement(
    					"SELECT * FROM empleado AS e JOIN empleadozoologico AS ez ON e.id=ez.id WHERE e.dni=? FOR UPDATE");
    			statement.setString(1, dniEmpleado);
    			result = statement.executeQuery();
    			if(result.next())
    			{
    				TEmpleadoZoologico empleadoZoologico = new TEmpleadoZoologico();
    				empleadoZoologico.setId(result.getInt("id"));
    				empleadoZoologico.setNombre(result.getString("nombre"));
    				empleadoZoologico.setDni(result.getString("dni"));
    				empleadoZoologico.setSueldoBase(result.getDouble("sueldoBase"));
    				empleadoZoologico.setTelefono(result.getInt("telefono"));
    				empleadoZoologico.setTipo(result.getInt("tipo"));
    				empleadoZoologico.setActivo(result.getBoolean("activo"));
    				empleadoZoologico.setEspecialidad(result.getString("especialidad"));
    				empleadoZoologico.setTasa(result.getDouble("tasa"));
    				empleadoZoologico.setExperiencia(result.getInt("experiencia"));

    				empleado = empleadoZoologico;
    			}
    		}

    		result.close();
    		statement.close();
    		return empleado;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

	@Override
	public Set<TEmpleado> listarEmpleadosLimpieza() {
		Set<TEmpleado> listaEmpleados = new HashSet<>();

		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement(
					"SELECT * FROM empleado AS e LEFT JOIN empleadolimpieza AS el ON e.id=el.id LEFT JOIN empleadozoologico AS ez ON e.id=ez.id FOR UPDATE");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				
				if (result.getInt("tipo") == 0) {
					TEmpleadoLimpieza empleadoLimpieza = new TEmpleadoLimpieza();
					empleadoLimpieza.setId(result.getInt("id"));
					empleadoLimpieza.setNombre(result.getString("nombre"));
					empleadoLimpieza.setDni(result.getString("dni"));
					empleadoLimpieza.setSueldoBase(result.getDouble("sueldoBase"));
					empleadoLimpieza.setTelefono(result.getInt("telefono"));
					empleadoLimpieza.setTipo(0);
					empleadoLimpieza.setActivo(result.getBoolean("activo"));
					empleadoLimpieza.setSuplemento(result.getDouble("suplemento"));
					empleadoLimpieza.setZona(result.getString("zona"));

					listaEmpleados.add(empleadoLimpieza);
				}

			}

			result.close();
			statement.close();

			return listaEmpleados;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Set<TEmpleado> listarEmpleadosZoologico() {
		Set<TEmpleado> listaEmpleados = new HashSet<>();

		try {
			TransactionManager tm = TransactionManager.getInstance();
			Transaction t = tm.getTransaction();
			Connection c = (Connection) t.getResource();

			PreparedStatement statement = c.prepareStatement(
					"SELECT * FROM empleado AS e LEFT JOIN empleadolimpieza AS el ON e.id=el.id LEFT JOIN empleadozoologico AS ez ON e.id=ez.id FOR UPDATE");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				
				if (result.getInt("tipo") == 1) {
					TEmpleadoZoologico empleadoZoologico = new TEmpleadoZoologico();
					empleadoZoologico.setId(result.getInt("id"));
					empleadoZoologico.setNombre(result.getString("nombre"));
					empleadoZoologico.setDni(result.getString("dni"));
					empleadoZoologico.setSueldoBase(result.getDouble("sueldoBase"));
					empleadoZoologico.setTelefono(result.getInt("telefono"));
					empleadoZoologico.setTipo(1);
					empleadoZoologico.setActivo(result.getBoolean("activo"));
					empleadoZoologico.setEspecialidad(result.getString("especialidad"));
					empleadoZoologico.setTasa(result.getDouble("tasa"));
					empleadoZoologico.setExperiencia(result.getInt("experiencia"));

					listaEmpleados.add(empleadoZoologico);
				}

			}

			result.close();
			statement.close();

			return listaEmpleados;
		} catch (Exception e) {
			return null;
		}
	}
    
}
