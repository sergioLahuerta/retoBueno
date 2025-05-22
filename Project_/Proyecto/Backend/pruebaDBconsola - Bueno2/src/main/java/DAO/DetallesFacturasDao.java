package DAO;

import model.DetallesFacturas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetallesFacturasDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM detalles_facturas WHERE 1=1 ";
    private IMotorSql motorSql;

    public DetallesFacturasDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        DetallesFacturas df = (DetallesFacturas) bean;

        String sql = "INSERT INTO detalles_facturas (id_detalle_pedido, id_factura, precioUnitario, totalLinea, descuento) VALUES (" +
                df.getId_detalle_pedido() + ", " +
                df.getId_factura() + ", " +
                df.getPrecioUnitario() + ", " +
                df.getTotalLinea() + ", " +
                df.getDescuento() + ")";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        DetallesFacturas df = (DetallesFacturas) e;
        String sql = "DELETE FROM detalles_facturas WHERE id_detalleFactura = " + df.getId_detalleFactura();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        DetallesFacturas df = (DetallesFacturas) bean;

        String sql = "UPDATE detalles_facturas SET " +
                "id_detalle_pedido = " + df.getId_detalle_pedido() + ", " +
                "id_factura = " + df.getId_factura() + ", " +
                "precioUnitario = " + df.getPrecioUnitario() + ", " +
                "totalLinea = " + df.getTotalLinea() + ", " +
                "descuento = " + df.getDescuento() +
                " WHERE id_detalleFactura = " + df.getId_detalleFactura();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<DetallesFacturas> FindAll(Object bean) {
        ArrayList<DetallesFacturas> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                DetallesFacturas df = (DetallesFacturas) bean;

                if (df.getId_detalleFactura() > 0) {
                    sql += " AND id_detalleFactura = " + df.getId_detalleFactura();
                }
                if (df.getId_detalle_pedido() > 0) {
                    sql += " AND id_detalle_pedido = " + df.getId_detalle_pedido();
                }
                if (df.getId_factura() > 0) {
                    sql += " AND id_factura = " + df.getId_factura();
                }
                if (df.getPrecioUnitario() > 0) {
                    sql += " AND precioUnitario = " + df.getPrecioUnitario();
                }
                if (df.getTotalLinea() > 0) {
                    sql += " AND totalLinea = " + df.getTotalLinea();
                }
                if (df.getDescuento() > 0) {
                    sql += " AND descuento = " + df.getDescuento();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs != null && rs.next()) {
                DetallesFacturas df = new DetallesFacturas();
                df.setId_detalleFactura(rs.getInt("id_detalleFactura"));
                df.setId_detalle_pedido(rs.getInt("id_detalle_pedido"));
                df.setId_factura(rs.getInt("id_factura"));
                df.setPrecioUnitario(rs.getDouble("precioUnitario"));
                df.setTotalLinea(rs.getDouble("totalLinea"));
                df.setDescuento(rs.getDouble("descuento"));

                lista.add(df);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll DetallesFacturas: " + e.getMessage());
        }

        return lista;
    }
}
