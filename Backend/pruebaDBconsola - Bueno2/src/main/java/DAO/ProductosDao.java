package DAO;

import model.Productos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Productos WHERE 1=1 ";
    private static IMotorSql motorSql;

    public ProductosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Productos producto = (Productos) bean;
        String sql = "INSERT INTO Productos (ID_Oferta, ID_Categoria, Nombre, Descripcion, Precio) " +
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
        String sql = "DELETE FROM Productos WHERE ID_Producto = " + producto.getId_producto();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Productos producto = (Productos) bean;
        String sql = "UPDATE Productos SET " +
                "ID_Oferta = " + producto.getId_oferta() + ", " +
                "ID_Categoria = " + producto.getId_categoria() + ", " +
                "Nombre = '" + producto.getNombre() + "', " +
                "Descripcion = '" + producto.getDescripcion() + "', " +
                "Precio = " + producto.getPrecio() +
                " WHERE ID_Producto = " + producto.getId_producto();
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
                    sql += " AND ID_Producto = " + producto.getId_producto();
                }
                if (producto.getId_categoria() > 0) {
                    sql += " AND ID_Categoria = " + producto.getId_categoria();
                }
                if (producto.getId_oferta() > 0) {
                    sql += " AND ID_Oferta = " + producto.getId_oferta();
                }
                if (producto.getNombre() != null && !producto.getNombre().isEmpty()) {
                    sql += " AND Nombre = '" + producto.getNombre() + "'";
                }
                if (producto.getDescripcion() != null && !producto.getDescripcion().isEmpty()) {
                    sql += " AND Descripcion = '" + producto.getDescripcion() + "'";
                }
                if (producto.getPrecio() > 0) {
                    sql += " AND Precio = " + producto.getPrecio();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Productos productoBd = new Productos();
                    productoBd.setId_producto(rs.getInt("ID_Producto"));
                    productoBd.setId_oferta(rs.getInt("ID_Oferta"));
                    productoBd.setId_categoria(rs.getInt("ID_Categoria"));
                    productoBd.setNombre(rs.getString("Nombre"));
                    productoBd.setDescripcion(rs.getString("Descripcion"));
                    productoBd.setPrecio(rs.getDouble("Precio"));

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

    public static double obtenerPrecioProducto(int idProducto, Connection conn) {
        double precio = 0.0;
        String sql = "SELECT precio FROM Productos WHERE ID_Producto = " + idProducto;

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);
            if (rs != null && rs.next()) {
                precio = rs.getDouble("Precio");
            } else {
                System.out.println("Producto no encontrado: ID = " + idProducto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener precio del producto ID " + idProducto + ": " + e.getMessage());
        }

        return precio;
    }
}
