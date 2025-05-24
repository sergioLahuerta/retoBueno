package DAO;

import model.Ingredientes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientesDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Ingredientes WHERE 1=1 ";
    private IMotorSql motorSql;

    public IngredientesDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Ingredientes ingrediente = (Ingredientes) bean;
        String sql = "INSERT INTO Ingredientes (Nombre, UnidadMedida, StockDisponible, TipoAlmacenamiento, EstaActivo) VALUES ('" +
                ingrediente.getNombre() + "', '" +
                ingrediente.getUnidadMedida() + "', " +
                ingrediente.getStockDisponible() + ", '" +
                ingrediente.getTipoAlmacenamiento() + "', " +
                (ingrediente.isEstaActivo() ? 1 : 0) + ")";
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Ingredientes ingrediente = (Ingredientes) e;
        String sql = "DELETE FROM Ingredientes WHERE ID_Ingrediente = " + ingrediente.getId_ingrediente();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Ingredientes ingrediente = (Ingredientes) bean;
        String sql = "UPDATE Ingredientes SET " +
                "Nombre = '" + ingrediente.getNombre() + "', " +
                "UnidadMedida = '" + ingrediente.getUnidadMedida() + "', " +
                "StockDisponible = " + ingrediente.getStockDisponible() + ", " +
                "TipoAlmacenamiento = '" + ingrediente.getTipoAlmacenamiento() + "', " +
                "EstaActivo = " + (ingrediente.isEstaActivo() ? 1 : 0) +
                " WHERE ID_Ingrediente = " + ingrediente.getId_ingrediente();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Ingredientes> FindAll(Object bean) {
        ArrayList<Ingredientes> ingredientes = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Ingredientes ingrediente = (Ingredientes) bean;

                if (ingrediente.getId_ingrediente() > 0) {
                    sql += " AND ID_Ingrediente = " + ingrediente.getId_ingrediente();
                }
                if (ingrediente.getNombre() != null && !ingrediente.getNombre().isEmpty()) {
                    sql += " AND Nombre = '" + ingrediente.getNombre() + "'";
                }
                if (ingrediente.getUnidadMedida() != null && !ingrediente.getUnidadMedida().isEmpty()) {
                    sql += " AND UnidadMedida = '" + ingrediente.getUnidadMedida() + "'";
                }
                if (ingrediente.getStockDisponible() > 0) {
                    sql += " AND StockDisponible = '" + ingrediente.getStockDisponible() + "'";
                }
                if (ingrediente.getTipoAlmacenamiento() != null && !ingrediente.getTipoAlmacenamiento().isEmpty()) {
                    sql += " AND TipoAlmacenamiento = '" + ingrediente.getTipoAlmacenamiento() + "'";
                }
                if (ingrediente.isEstaActivo()) {
                    sql += " AND EstaActivo = 1";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Ingredientes ingredienteBd = new Ingredientes();
                    ingredienteBd.setId_ingrediente(rs.getInt("ID_Ingrediente"));
                    ingredienteBd.setNombre(rs.getString("Nombre"));
                    ingredienteBd.setUnidadMedida(rs.getString("UnidadMedida"));
                    ingredienteBd.setStockDisponible(rs.getDouble("StockDisponible"));
                    ingredienteBd.setTipoAlmacenamiento(rs.getString("TipoAlmacenamiento"));
                    ingredienteBd.setEstaActivo(rs.getBoolean("EstaActivo"));

                    ingredientes.add(ingredienteBd);
                }
            } else {
                System.out.println("La consulta no devolvió resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Ingredientes: " + e.getMessage());
            e.printStackTrace();
        }

        return ingredientes;
    }
}
