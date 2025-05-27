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
        String sql = "INSERT INTO Productos_Ingredientes (ID_Ingrediente, ID_Producto, Cantidad) VALUES (" +
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
                "ID_Ingrediente = " + pi.getId_ingrediente() + ", " +
                "ID_Producto = " + pi.getId_producto() + ", " +
                "Cantidad = " + pi.getCantidad() +
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
                    sql += " AND ID_Ingrediente = " + pi.getId_ingrediente();
                }
                if (pi.getId_producto() > 0) {
                    sql += " AND ID_Producto = " + pi.getId_producto();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Productos_Ingredientes piBd = new Productos_Ingredientes();
                    piBd.setId_productoIngrediente(rs.getInt("ID_ProductoIngrediente"));
                    piBd.setId_ingrediente(rs.getInt("ID_Ingrediente"));
                    piBd.setId_producto(rs.getInt("ID_Producto"));
                    piBd.setCantidad(rs.getDouble("Cantidad"));

                    lista.add(piBd);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Productos_Ingredientes: " + e.getMessage());
        }

        return lista;
    }
}
