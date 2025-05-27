package DAO;

import model.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDao {

    public int add(Productos producto, Connection conn) throws SQLException {
        String sql = "INSERT INTO Productos (ID_Oferta, ID_Categoria, Nombre, Descripcion, Precio) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, producto.getId_oferta());
            stmt.setInt(2, producto.getId_categoria());
            stmt.setString(3, producto.getNombre());
            stmt.setString(4, producto.getDescripcion());
            stmt.setDouble(5, producto.getPrecio());
            return stmt.executeUpdate();
        }
    }

    public int delete(int idProducto, Connection conn) throws SQLException {
        String sql = "DELETE FROM Productos WHERE ID_Producto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            return stmt.executeUpdate();
        }
    }

    public int update(Productos producto, Connection conn) throws SQLException {
        String sql = "UPDATE Productos SET ID_Oferta = ?, ID_Categoria = ?, Nombre = ?, Descripcion = ?, Precio = ? " +
                "WHERE ID_Producto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, producto.getId_oferta());
            stmt.setInt(2, producto.getId_categoria());
            stmt.setString(3, producto.getNombre());
            stmt.setString(4, producto.getDescripcion());
            stmt.setDouble(5, producto.getPrecio());
            stmt.setInt(6, producto.getId_producto());
            return stmt.executeUpdate();
        }
    }

    public ArrayList<Productos>FindAll(Productos filtro, Connection conn) throws SQLException {
        ArrayList<Productos> productos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Productos WHERE 1=1");

        if (filtro != null) {
            if (filtro.getId_producto() > 0)
                sql.append(" AND ID_Producto = ").append(filtro.getId_producto());
            if (filtro.getId_categoria() > 0)
                sql.append(" AND ID_Categoria = ").append(filtro.getId_categoria());
            if (filtro.getId_oferta() > 0)
                sql.append(" AND ID_Oferta = ").append(filtro.getId_oferta());
            if (filtro.getNombre() != null && !filtro.getNombre().isEmpty())
                sql.append(" AND Nombre = '").append(filtro.getNombre()).append("'");
            if (filtro.getDescripcion() != null && !filtro.getDescripcion().isEmpty())
                sql.append(" AND Descripcion = '").append(filtro.getDescripcion()).append("'");
            if (filtro.getPrecio() > 0)
                sql.append(" AND Precio = ").append(filtro.getPrecio());
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString());
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Productos p = new Productos();
                p.setId_producto(rs.getInt("ID_Producto"));
                p.setId_oferta(rs.getInt("ID_Oferta"));
                p.setId_categoria(rs.getInt("ID_Categoria"));
                p.setNombre(rs.getString("Nombre"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setPrecio(rs.getDouble("Precio"));
                productos.add(p);
            }
        }

        return productos;
    }

    public static double obtenerPrecioProducto(int idProducto, Connection conn) throws SQLException {
        String sql = "SELECT Precio FROM Productos WHERE ID_Producto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Precio");
            } else {
                System.out.println("Producto no encontrado en BD con ID: " + idProducto);
                return -1;
            }
        }
    }
}
