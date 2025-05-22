package DAO;

import model.Pedidos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM pedidos WHERE 1=1 ";
    private IMotorSql motorSql;

    public PedidosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Pedidos pedido = (Pedidos) bean;

        String sql = "INSERT INTO pedidos (id_factura, id_restaurante, id_usuario, numero) VALUES (" +
                pedido.getId_factura() + ", " +
                pedido.getId_restaurante() + ", " +
                pedido.getId_usuario() + ", " +
                pedido.getNumero() + ")";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Pedidos pedido = (Pedidos) e;
        String sql = "DELETE FROM pedidos WHERE id_pedido = " + pedido.getId_pedido();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Pedidos pedido = (Pedidos) bean;

        String sql = "UPDATE pedidos SET " +
                "id_factura = " + pedido.getId_factura() + ", " +
                "id_restaurante = " + pedido.getId_restaurante() + ", " +
                "id_usuario = " + pedido.getId_usuario() + ", " +
                "numero = " + pedido.getNumero() +
                " WHERE id_pedido = " + pedido.getId_pedido();

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

                if (p.getId_pedido() > 0) {
                    sql += " AND id_pedido = " + p.getId_pedido();
                }
                if (p.getId_factura() > 0) {
                    sql += " AND id_factura = " + p.getId_factura();
                }
                if (p.getId_restaurante() > 0) {
                    sql += " AND id_restaurante = " + p.getId_restaurante();
                }
                if (p.getId_usuario() > 0) {
                    sql += " AND id_usuario = " + p.getId_usuario();
                }
                if (p.getNumero() > 0) {
                    sql += " AND numero = " + p.getNumero();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs != null && rs.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setId_factura(rs.getInt("id_factura"));
                pedido.setId_restaurante(rs.getInt("id_restaurante"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setNumero(rs.getInt("numero"));

                lista.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Pedidos: " + e.getMessage());
        }

        return lista;
    }
}
