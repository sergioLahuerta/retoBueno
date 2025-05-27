package DAO;

import model.DetallesFacturas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetallesFacturasDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Detalles_Facturas WHERE 1=1 ";
    private IMotorSql motorSql;

    public DetallesFacturasDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        DetallesFacturas df = (DetallesFacturas) bean;

        String sql = "INSERT INTO Detalles_Facturas (ID_Detalle_Pedido, ID_Factura, PrecioUnitario, TasaLocal, Descuento) VALUES (" +
                df.getId_detalle_pedido() + ", " +
                df.getId_factura() + ", " +
                df.getPrecioUnitario() + ", " +
                df.getTasaLocal() + ", " +
                df.getDescuento() + ")";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        DetallesFacturas df = (DetallesFacturas) e;
        String sql = "DELETE FROM Detalles_Facturas WHERE ID_DetalleFactura = " + df.getId_detalleFactura();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        DetallesFacturas df = (DetallesFacturas) bean;

        String sql = "UPDATE Detalles_Facturas SET " +
                "ID_Detalle_Pedido = " + df.getId_detalle_pedido() + ", " +
                "ID_Factura = " + df.getId_factura() + ", " +
                "PrecioUnitario = " + df.getPrecioUnitario() + ", " +
                "TasaLocal = " + df.getTasaLocal() + ", " +
                "Descuento = " + df.getDescuento() +
                " WHERE ID_DetalleFactura = " + df.getId_detalleFactura();

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
                    sql += " AND ID_DetalleFactura = " + df.getId_detalleFactura();
                }
                if (df.getId_detalle_pedido() > 0) {
                    sql += " AND ID_Detalle_Pedido = " + df.getId_detalle_pedido();
                }
                if (df.getId_factura() > 0) {
                    sql += " AND ID_Factura = " + df.getId_factura();
                }
                if (df.getPrecioUnitario() > 0) {
                    sql += " AND PrecioUnitario = " + df.getPrecioUnitario();
                }
                if (df.getTasaLocal() > 0) {
                    sql += " AND TasaLocal = " + df.getTasaLocal();
                }
                if (df.getDescuento() > 0) {
                    sql += " AND Descuento = " + df.getDescuento();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs != null && rs.next()) {
                DetallesFacturas df = new DetallesFacturas();
                df.setId_detalleFactura(rs.getInt("ID_DetalleFactura"));
                df.setId_detalle_pedido(rs.getInt("ID_Detalle_Pedido"));
                df.setId_factura(rs.getInt("ID_Factura"));
                df.setPrecioUnitario(rs.getDouble("PrecioUnitario"));
                df.setTasaLocal(rs.getDouble("TasaLocal"));
                df.setDescuento(rs.getDouble("Descuento"));

                lista.add(df);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Detalles_Facturas: " + e.getMessage());
        }

        return lista;
    }
}
