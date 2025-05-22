package DAO;

import model.Ingredientes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientesDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM ingredientes WHERE 1=1 ";
    private IMotorSql motorSql;

    public IngredientesDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Ingredientes ingrediente = (Ingredientes) bean;
        String sql = "INSERT INTO ingredientes (nombre, unidadMedida, stockDisponible, tipoAlmacenamiento, disponible) VALUES ('" +
                ingrediente.getNombre() + "', '" +
                ingrediente.getUnidadMedida() + "', " +
                ingrediente.getStockDisponible() + ", '" +
                ingrediente.getTipoAlmacenamiento() + "', " +
                (ingrediente.isDisponible() ? 1 : 0) + ")";
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Ingredientes ingrediente = (Ingredientes) e;
        String sql = "DELETE FROM ingredientes WHERE id_ingrediente = " + ingrediente.getId_ingrediente();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Ingredientes ingrediente = (Ingredientes) bean;
        String sql = "UPDATE ingredientes SET " +
                "nombre = '" + ingrediente.getNombre() + "', " +
                "unidadMedida = '" + ingrediente.getUnidadMedida() + "', " +
                "stockDisponible = " + ingrediente.getStockDisponible() + ", " +
                "tipoAlmacenamiento = '" + ingrediente.getTipoAlmacenamiento() + "', " +
                "disponible = " + (ingrediente.isDisponible() ? 1 : 0) +
                " WHERE id_ingrediente = " + ingrediente.getId_ingrediente();
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
                    sql += " AND id_ingrediente = " + ingrediente.getId_ingrediente();
                }
                if (ingrediente.getNombre() != null && !ingrediente.getNombre().isEmpty()) {
                    sql += " AND nombre = '" + ingrediente.getNombre() + "'";
                }
                if (ingrediente.getUnidadMedida() != null && !ingrediente.getUnidadMedida().isEmpty()) {
                    sql += " AND unidadMedida = '" + ingrediente.getUnidadMedida() + "'";
                }
                if (ingrediente.getStockDisponible() >= 0) {
                    sql += " AND stockDisponible = " + ingrediente.getStockDisponible();
                }
                if (ingrediente.getTipoAlmacenamiento() != null && !ingrediente.getTipoAlmacenamiento().isEmpty()) {
                    sql += " AND tipoAlmacenamiento = '" + ingrediente.getTipoAlmacenamiento() + "'";
                }
                sql += " AND disponible = " + (ingrediente.isDisponible() ? 1 : 0);
            }

            ResultSet rs = motorSql.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Ingredientes ingredienteBd = new Ingredientes();
                    ingredienteBd.setId_ingrediente(rs.getInt("id_ingrediente"));
                    ingredienteBd.setNombre(rs.getString("nombre"));
                    ingredienteBd.setUnidadMedida(rs.getString("unidadMedida"));
                    ingredienteBd.setStockDisponible(rs.getDouble("stockDisponible"));
                    ingredienteBd.setTipoAlmacenamiento(rs.getString("tipoAlmacenamiento"));
                    ingredienteBd.setDisponible(rs.getBoolean("disponible"));

                    ingredientes.add(ingredienteBd);
                }
            } else {
                System.out.println("La consulta no devolvió resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta o procesar el ResultSet: " + e.getMessage());
            e.printStackTrace();
        }

        return ingredientes;
    }
}
