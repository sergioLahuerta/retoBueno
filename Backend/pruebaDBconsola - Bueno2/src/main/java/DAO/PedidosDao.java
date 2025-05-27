package DAO;

import model.Pedidos;

import java.sql.*;
import java.util.ArrayList;

public class PedidosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Pedidos WHERE 1=1 ";
    private IMotorSql motorSql;

    public PedidosDao() {
        motorSql = new MotorSql();
    }

    public static int add(Pedidos pedido, Connection conn) throws SQLException {
        String sql = "INSERT INTO Pedidos (ID_Factura, ID_Restaurante, ID_Usuario, Numero) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        if (pedido.getId_factura() > 0) {
            ps.setInt(1, pedido.getId_factura());
        } else {
            ps.setNull(1, java.sql.Types.INTEGER);
        }
        ps.setInt(2, pedido.getId_restaurante());
        ps.setInt(3, pedido.getId_usuario());
        ps.setInt(4, pedido.getNumero());

        System.out.println("Insertando pedido:");
        System.out.println("Factura ID: " + pedido.getId_factura());
        System.out.println("Restaurante ID: " + pedido.getId_restaurante());
        System.out.println("Usuario ID: " + pedido.getId_usuario());
        System.out.println("Numero: " + pedido.getNumero());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // Devuelve el ID generado del pedido (el ID_Pedido)
        }
        return -1; // Si no se generó el ID
    }


    @Override
    public int add(Object bean) {
        return 0;
    }

    @Override
    public int delete(Object e) {
        Pedidos pedido = (Pedidos) e;
        String sql = "DELETE FROM Pedidos WHERE ID_Pedido = " + pedido.getId_pedido();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Pedidos pedido = (Pedidos) bean;
        String sql = "UPDATE Pedidos SET ID_Factura = " + pedido.getId_factura() + ", " +
                "ID_Restaurante = " + pedido.getId_restaurante() + ", " +
                "ID_Usuario = " + pedido.getId_usuario() + ", " +
                "Numero = " + pedido.getNumero() +
                " WHERE ID_Pedido = " + pedido.getId_pedido();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Pedidos> FindAll(Object bean) {
        ArrayList<Pedidos> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Pedidos p = (Pedidos) bean;
                if (p.getId_pedido() > 0) sql += " AND ID_Pedido = " + p.getId_pedido();
                if (p.getId_factura() > 0) sql += " AND ID_Factura = " + p.getId_factura();
                if (p.getId_restaurante() > 0) sql += " AND ID_Restaurante = " + p.getId_restaurante();
                if (p.getId_usuario() > 0) sql += " AND ID_Usuario = " + p.getId_usuario();
                if (p.getNumero() > 0) sql += " AND Numero = " + p.getNumero();
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs != null && rs.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setId_pedido(rs.getInt("ID_Pedido"));
                pedido.setId_factura(rs.getInt("ID_Factura"));
                pedido.setId_restaurante(rs.getInt("ID_Restaurante"));
                pedido.setId_usuario(rs.getInt("ID_Usuario"));
                pedido.setNumero(rs.getInt("Numero"));
                lista.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Pedidos: " + e.getMessage());
        }

        return lista;
    }
}
