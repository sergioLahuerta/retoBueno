package DAO;

import model.Productos_Ingredientes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Productos_IngredientesDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Productos_Ingredientes WHERE 1=1 ";
    private IMotorSql motorSql;

    public Productos_IngredientesDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Productos_Ingredientes pi = (Productos_Ingredientes) bean;
        String sql = "INSERT INTO Productos_Ingredientes (id_ingrediente, id_producto, cantidad) VALUES (" +
                pi.getId_ingrediente() + ", " +
                pi.getId_producto() + ", " +
                pi.getCantidad() + ")";
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Productos_Ingredientes pi = (Productos_Ingredientes) e;
        String sql = "DELETE FROM Productos_Ingredientes WHERE ID_ProductoIngrediente = " + pi.getId_productoIngrediente();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Productos_Ingredientes pi = (Productos_Ingredientes) bean;
        String sql = "UPDATE Productos_Ingredientes SET " +
                "id_ingrediente = " + pi.getId_ingrediente() + ", " +
                "id_producto = " + pi.getId_producto() + ", " +
                "cantidad = " + pi.getCantidad() +
                " WHERE ID_ProductoIngrediente = " + pi.getId_productoIngrediente();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Productos_Ingredientes> FindAll(Object bean) {
        ArrayList<Productos_Ingredientes> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Productos_Ingredientes pi = (Productos_Ingredientes) bean;

                if (pi.getId_productoIngrediente() > 0) {
                    sql += " AND ID_ProductoIngrediente = " + pi.getId_productoIngrediente();
                }
                if (pi.getId_ingrediente() > 0) {
                    sql += " AND id_ingrediente = " + pi.getId_ingrediente();
                }
                if (pi.getId_producto() > 0) {
                    sql += " AND id_producto = " + pi.getId_producto();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Productos_Ingredientes piBd = new Productos_Ingredientes();
                    piBd.setId_productoIngrediente(rs.getInt("ID_ProductoIngrediente"));
                    piBd.setId_ingrediente(rs.getInt("id_ingrediente"));
                    piBd.setId_producto(rs.getInt("id_producto"));
                    piBd.setCantidad(rs.getDouble("cantidad"));

                    lista.add(piBd);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Productos_Ingredientes: " + e.getMessage());
        }

        return lista;
    }
}
