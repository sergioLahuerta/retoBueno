package DAO;

import model.Pagos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PagosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Pagos WHERE 1=1 ";
    private IMotorSql motorSql;

    public PagosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Pagos p = (Pagos) bean;
        String sql = "INSERT INTO Pagos (ID_Factura, metodoPago, fechaPago, estadoPago) VALUES (" +
                p.getId_factura() + ", '" +
                p.getMetodoPago() + "', '" +
                new Date(p.getFechaPago().getTime()) + "', '" +
                p.getEstadoPago() + "')";
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object bean) {
        Pagos p = (Pagos) bean;
        String sql = "DELETE FROM Pagos WHERE ID_Pago = " + p.getId_pago();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Pagos p = (Pagos) bean;
        String sql = "UPDATE Pagos SET " +
                "ID_Factura = " + p.getId_factura() + ", " +
                "metodoPago = '" + p.getMetodoPago() + "', " +
                "fechaPago = '" + new Date(p.getFechaPago().getTime()) + "', " +
                "estadoPago = '" + p.getEstadoPago() + "' " +
                "WHERE id_pago = " + p.getId_pago();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Pagos> FindAll(Object bean) {
        ArrayList<Pagos> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();
            if (bean != null) {
                Pagos p = (Pagos) bean;
                if (p.getId_pago() > 0) {
                    sql += " AND ID_Pago = " + p.getId_pago();
                }
                if (p.getId_factura() > 0) {
                    sql += " AND ID_Factura = " + p.getId_factura();
                }
                if (p.getMetodoPago() != null) {
                    sql += " AND metodoPago = '" + p.getMetodoPago() + "'";
                }
                if (p.getFechaPago() != null) {
                    sql += " AND fechaPago = '" + new Date(p.getFechaPago().getTime()) + "'";
                }
                if (p.getEstadoPago() != null) {
                    sql += " AND estadoPago = '" + p.getEstadoPago() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);
            while (rs != null && rs.next()) {
                Pagos p = new Pagos();
                p.setId_pago(rs.getInt("ID_Pago"));
                p.setId_factura(rs.getInt("ID_Factura"));
                p.setMetodoPago(rs.getString("metodoPago"));
                p.setFechaPago(rs.getDate("fechaPago"));
                p.setEstadoPago(rs.getString("estadoPago"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Pagos: " + e.getMessage());
        }

        return lista;
    }
}
