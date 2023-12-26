package Integracion.FactoriaQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;

import Negocio.Habitat.THabitat;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class CalcularHabitatConMasIngresos implements Query {

    @Override
    @SuppressWarnings("unchecked")
    public Object execute(Object params) {
		HashMap<String, Object> args = (HashMap<String, Object>) params;
        return buscarHabitatConMasIngresos((LocalDate) args.get("desdeFecha"), (LocalDate) args.get("hastaFecha"));
    }

    private THabitat buscarHabitatConMasIngresos(LocalDate desdeFecha, LocalDate hastaFecha) {
        THabitat habitat = null;

        try {
            TransactionManager tManager = TransactionManager.getInstance();
            Transaction t = tManager.getTransaction();
            Connection c = (Connection) t.getResource();
            String query = "SELECT H.* " +
                           "FROM Habitat H JOIN Pase P ON H.id = P.id_habitat " +
                           "JOIN LineaFactura LF ON P.id = LF.id_pase " +
                           "JOIN Factura F ON LF.id_factura = F.id " +
                           "WHERE F.fecha_compra BETWEEN ? AND ? " +
                           "GROUP BY H.id, H.nombre, H.tamano, H.activo " +
                           "ORDER BY SUM(F.precio_total) DESC " +
                           "LIMIT 1";

            PreparedStatement statement = c.prepareStatement(query);
            statement.setDate(1, java.sql.Date.valueOf(desdeFecha));
            statement.setDate(2, java.sql.Date.valueOf(hastaFecha));

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                habitat = new THabitat();
                habitat.setId(rs.getInt("id"));
                habitat.setNombre(rs.getString("nombre"));
                habitat.setTamano(rs.getInt("tamano"));
                habitat.setActivo(rs.getBoolean("activo"));
            }

            statement.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitat;
    }
}
