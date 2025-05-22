package DAO;

import model.DetallesPedidos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetallesPedidosDao implements iDao<DetallesPedidos, Integer> {

    private final String SQL_FIND = "SELECT * FROM detalles_pedidos WHERE 1=1";
    private IMotorSql motorSql;

    public DetallesPedidosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(DetallesPedidos bean) {
        String sql = "INSERT INTO detalles_pedidos (id_pedido, id_producto, cantidad, observaciones) VALUES (" +
                bean.getId_pedido() + ", " +
                bean.getId_producto() + ", " +
                bean.getCantidad() + ", '" +
                bean.getObservaciones() + "')";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Integer idDetalle) {
        String sql = "DELETE FROM detalles_pedidos WHERE id_detallePedido = " + idDetalle;
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(DetallesPedidos bean) {
        String sql = "UPDATE detalles_pedidos SET " +
                "id_pedido = " + bean.getId_pedido() + ", " +
                "id_producto = " + bean.getId_producto() + ", " +
                "cantidad = " + bean.getCantidad() + ", " +
                "observaciones = '" + bean.getObservaciones() + "' " +
                "WHERE id_detallePedido = " + bean.getId_detallePedido();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<DetallesPedidos> FindAll(DetallesPedidos filtro) {
        ArrayList<DetallesPedidos> lista = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getId_detallePedido() > 0) {
                sql += " AND id_detallePedido = " + filtro.getId_detallePedido();
            }
            if (filtro.getId_pedido() > 0) {
                sql += " AND id_pedido = " + filtro.getId_pedido();
            }
            if (filtro.getId_producto() > 0) {
                sql += " AND id_producto = " + filtro.getId_producto();
            }
            if (filtro.getCantidad() > 0) {
                sql += " AND cantidad = " + filtro.getCantidad();
            }
            if (filtro.getObservaciones() != null && !filtro.getObservaciones().isEmpty()) {
                sql += " AND observaciones LIKE '%" + filtro.getObservaciones() + "%'";
            }
        }

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs != null && rs.next()) {
                DetallesPedidos dp = new DetallesPedidos();
                dp.setId_detallePedido(rs.getInt("id_detallePedido"));
                dp.setId_pedido(rs.getInt("id_pedido"));
                dp.setId_producto(rs.getInt("id_producto"));
                dp.setCantidad(rs.getInt("cantidad"));
                dp.setObservaciones(rs.getString("observaciones"));

                lista.add(dp);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll DetallesPedidos: " + e.getMessage());
        }

        return lista;
    }
}
