package DAO;

import model.HistorialPuntos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class HistorialPuntosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Historial_Puntos WHERE 1=1 ";
    private IMotorSql motorSql;

    public HistorialPuntosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        HistorialPuntos h = (HistorialPuntos) bean;
        String sql = "INSERT INTO Historial_Puntos (ID_Factura, Fecha, Puntos, TipoMovimiento, Descripcion) VALUES (" +
                h.getId_factura() + ", '" +
                new Date(h.getFecha().getTime()) + "', " +
                h.getPuntos() + ", '" +
                h.getTipoMovimiento() + "', '" +
                h.getDescripcion() + "')";
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object bean) {
        HistorialPuntos h = (HistorialPuntos) bean;
        String sql = "DELETE FROM Historial_Puntos WHERE ID_HistorialPuntos = " + h.getId_historialPuntos();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        HistorialPuntos h = (HistorialPuntos) bean;
        String sql = "UPDATE Historial_Puntos SET " +
                "ID_Factura = " + h.getId_factura() + ", " +
                "Fecha = '" + new Date(h.getFecha().getTime()) + "', " +
                "Puntos = " + h.getPuntos() + ", " +
                "TipoMovimiento = '" + h.getTipoMovimiento() + "', " +
                "Descripcion = '" + h.getDescripcion() + "' " +
                "WHERE ID_HistorialPuntos = " + h.getId_historialPuntos();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<HistorialPuntos> FindAll(Object bean) {
        ArrayList<HistorialPuntos> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();
            if (bean != null) {
                HistorialPuntos h = (HistorialPuntos) bean;
                if (h.getId_historialPuntos() > 0) {
                    sql += " AND ID_HistorialPuntos = " + h.getId_historialPuntos();
                }
                if (h.getId_factura() > 0) {
                    sql += " AND ID_Factura = " + h.getId_factura();
                }
                if (h.getFecha() != null) {
                    sql += " AND Fecha = '" + new Date(h.getFecha().getTime()) + "'";
                }
                if (h.getPuntos() > 0) {
                    sql += " AND Puntos = " + h.getPuntos();
                }
                if (h.getTipoMovimiento() != null) {
                    sql += " AND TipoMovimiento = '" + h.getTipoMovimiento() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);
            while (rs != null && rs.next()) {
                HistorialPuntos h = new HistorialPuntos();
                h.setId_historialPuntos(rs.getInt("ID_HistorialPuntos"));
                h.setId_factura(rs.getInt("ID_Factura"));
                h.setFecha(rs.getDate("Fecha"));
                h.setPuntos(rs.getInt("Puntos"));
                h.setTipoMovimiento(rs.getString("TipoMovimiento"));
                h.setDescripcion(rs.getString("Descripcion"));
                lista.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Historial_Puntos: " + e.getMessage());
        }

        return lista;
    }
}
