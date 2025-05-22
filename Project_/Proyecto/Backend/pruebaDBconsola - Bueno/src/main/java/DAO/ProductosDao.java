package DAO;

import model.Productos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM productos WHERE 1=1 ";
    private IMotorSql motorSql;

    public ProductosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Productos producto = (Productos) bean;
        String sql = "INSERT INTO productos (id_oferta, id_categoria, nombre, descripcion, precio) " +
                "VALUES (" + producto.getId_oferta() + ", " +
                producto.getId_categoria() + ", '" +
                producto.getNombre() + "', '" +
                producto.getDescripcion() + "', " +
                producto.getPrecio() + ")";
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Productos producto = (Productos) e;
        String sql = "DELETE FROM productos WHERE id_producto = " + producto.getId_producto();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Productos producto = (Productos) bean;
        String sql = "UPDATE productos SET " +
                "id_oferta = " + producto.getId_oferta() + ", " +
                "id_categoria = " + producto.getId_categoria() + ", " +
                "nombre = '" + producto.getNombre() + "', " +
                "descripcion = '" + producto.getDescripcion() + "', " +
                "precio = " + producto.getPrecio() +
                " WHERE id_producto = " + producto.getId_producto();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Productos> FindAll(Object bean) {
        ArrayList<Productos> productos = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Productos producto = (Productos) bean;

                if (producto.getId_producto() > 0) {
                    sql += " AND id_producto = " + producto.getId_producto();
                }
                if (producto.getId_categoria() > 0) {
                    sql += " AND id_categoria = " + producto.getId_categoria();
                }
                if (producto.getId_oferta() > 0) {
                    sql += " AND id_oferta = " + producto.getId_oferta();
                }
                if (producto.getNombre() != null && !producto.getNombre().isEmpty()) {
                    sql += " AND nombre = '" + producto.getNombre() + "'";
                }
                if (producto.getDescripcion() != null && !producto.getDescripcion().isEmpty()) {
                    sql += " AND descripcion = '" + producto.getDescripcion() + "'";
                }
                if (producto.getPrecio() > 0) {
                    sql += " AND precio = " + producto.getPrecio();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Productos productoBd = new Productos();
                    productoBd.setId_producto(rs.getInt("id_producto"));
                    productoBd.setId_oferta(rs.getInt("id_oferta"));
                    productoBd.setId_categoria(rs.getInt("id_categoria"));
                    productoBd.setNombre(rs.getString("nombre"));
                    productoBd.setDescripcion(rs.getString("descripcion"));
                    productoBd.setPrecio(rs.getDouble("precio"));

                    productos.add(productoBd);
                }
            } else {
                System.out.println("La consulta no devolvió resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta o procesar el ResultSet: " + e.getMessage());
            e.printStackTrace();
        }

        return productos;
    }
}
