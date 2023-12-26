package Integracion.FactoriaQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Negocio.Empleado.TEmpleado;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class ListarEmpleadoPorEspecieEnHabitat implements Query {

    @Override
    @SuppressWarnings("unchecked")
    public Object execute(Object param) {
		HashMap<String, Object> args = (HashMap<String, Object>) param;
        Integer idEspecie = (Integer) args.get("idEspecie");
        return listarEmpleadosPorEspecieEnHabitat(idEspecie);
    }

    private Set<TEmpleado> listarEmpleadosPorEspecieEnHabitat(Integer idEspecie) {
        Set<TEmpleado> empleados = new HashSet<TEmpleado>();

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();

            String query = "SELECT E.* " +
                           "FROM Empleado E " +
                           "JOIN Trabajo T ON E.id = T.id_empleado " +
                           "JOIN Habitat H ON T.id_habitat = H.id " +
                           "JOIN Especie ESP ON H.id = ESP.id_habitat " +
                           "WHERE ESP.id = ?";

            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, idEspecie);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TEmpleado empleado = new TEmpleado();
                empleado.setId(result.getInt("id"));
                empleado.setNombre(result.getString("nombre"));
                empleado.setDni(result.getString("dni"));
                empleado.setSueldoBase(result.getDouble("sueldoBase"));
                empleado.setTelefono(result.getInt("telefono"));
                empleado.setActivo(result.getBoolean("activo"));
                empleado.setTipo(result.getInt("tipo"));
                empleados.add(empleado);
            }

            statement.close();
            result.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return empleados;
    }
}
