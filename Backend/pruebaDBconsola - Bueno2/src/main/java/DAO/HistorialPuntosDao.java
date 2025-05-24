package DAO;

import model.HistorialPuntos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class HistorialPuntosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM historial_puntos WHERE 1=1 ";
    private IMotorSql motorSql;

    public HistorialPuntosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        HistorialPuntos h = (HistorialPuntos) bean;
        String sql = "INSERT INTO historial_puntos (id_factura, fecha, puntos, tipoMovimiento, descripcion) VALUES (" +
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
        String sql = "DELETE FROM historial_puntos WHERE id_historialPuntos = " + h.getId_historialPuntos();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        HistorialPuntos h = (HistorialPuntos) bean;
        String sql = "UPDATE historial_puntos SET " +
                "id_factura = " + h.getId_factura() + ", " +
                "fecha = '" + new Date(h.getFecha().getTime()) + "', " +
                "puntos = " + h.getPuntos() + ", " +
                "tipoMovimiento = '" + h.getTipoMovimiento() + "', " +
                "descripcion = '" + h.getDescripcion() + "' " +
                "WHERE id_historialPuntos = " + h.getId_historialPuntos();
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
                    sql += " AND id_historialPuntos = " + h.getId_historialPuntos();
                }
                if (h.getId_factura() > 0) {
                    sql += " AND id_factura = " + h.getId_factura();
                }
                if (h.getFecha() != null) {
                    sql += " AND fecha = '" + new Date(h.getFecha().getTime()) + "'";
                }
                if (h.getPuntos() > 0) {
                    sql += " AND puntos = " + h.getPuntos();
                }
                if (h.getTipoMovimiento() != null) {
                    sql += " AND tipoMovimiento = '" + h.getTipoMovimiento() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);
            while (rs != null && rs.next()) {
                HistorialPuntos h = new HistorialPuntos();
                h.setId_historialPuntos(rs.getInt("id_historialPuntos"));
                h.setId_factura(rs.getInt("id_factura"));
                h.setFecha(rs.getDate("fecha"));
                h.setPuntos(rs.getInt("puntos"));
                h.setTipoMovimiento(rs.getString("tipoMovimiento"));
                h.setDescripcion(rs.getString("descripcion"));
                lista.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll HistorialPuntos: " + e.getMessage());
        }

        return lista;
    }
}
