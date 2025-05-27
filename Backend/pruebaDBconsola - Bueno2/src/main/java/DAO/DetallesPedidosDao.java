package DAO;

import model.DetallesPedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetallesPedidosDao implements iDao<DetallesPedidos, Integer> {

    private final String SQL_FIND = "SELECT * FROM Detalles_Pedidos WHERE 1=1";
    private IMotorSql motorSql;

    public DetallesPedidosDao() {
        motorSql = new MotorSql();
    }

    public static int add(DetallesPedidos bean, Connection conn) {
        String sql = "INSERT INTO Detalles_Pedidos (ID_Pedido, ID_Producto, Cantidad, Observaciones) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bean.getId_pedido());
            ps.setInt(2, bean.getId_producto());
            ps.setInt(3, bean.getCantidad());
            ps.setString(4, bean.getObservaciones());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar DetallesPedidos: " + e.getMessage());
            return -1;
        }
    }


    @Override
    public int add(DetallesPedidos bean) {
        return 0;
    }

    @Override
    public int delete(Integer idDetalle) {
        String sql = "DELETE FROM Detalles_Pedidos WHERE ID_DetallePedido = " + idDetalle;
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(DetallesPedidos bean) {
        String sql = "UPDATE Detalles_Pedidos SET " +
                "ID_Pedido = " + bean.getId_pedido() + ", " +
                "ID_Producto = " + bean.getId_producto() + ", " +
                "Cantidad = " + bean.getCantidad() + ", " +
                "Observaciones = '" + bean.getObservaciones() + "' " +
                "WHERE ID_DetallePedido = " + bean.getId_detallePedido();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<DetallesPedidos> FindAll(DetallesPedidos filtro) {
        ArrayList<DetallesPedidos> lista = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getId_detallePedido() > 0) {
                sql += " AND ID_DetallePedido = " + filtro.getId_detallePedido();
            }
            if (filtro.getId_pedido() > 0) {
                sql += " AND ID_Pedido = " + filtro.getId_pedido();
            }
            if (filtro.getId_producto() > 0) {
                sql += " AND ID_Producto = " + filtro.getId_producto();
            }
            if (filtro.getCantidad() > 0) {
                sql += " AND Cantidad = " + filtro.getCantidad();
            }
            if (filtro.getObservaciones() != null && !filtro.getObservaciones().isEmpty()) {
                sql += " AND Observaciones LIKE '%" + filtro.getObservaciones() + "%'";
            }
        }

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs != null && rs.next()) {
                DetallesPedidos dp = new DetallesPedidos();
                dp.setId_detallePedido(rs.getInt("ID_DetallePedido"));
                dp.setId_pedido(rs.getInt("ID_Pedido"));
                dp.setId_producto(rs.getInt("ID_Producto"));
                dp.setCantidad(rs.getInt("Cantidad"));
                dp.setObservaciones(rs.getString("Observaciones"));

                lista.add(dp);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Detalles_Pedidos: " + e.getMessage());
        }

        return lista;
    }
}
