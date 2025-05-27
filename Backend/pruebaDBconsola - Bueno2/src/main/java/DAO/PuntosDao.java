package DAO;

import model.Puntos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuntosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Puntos WHERE 1=1 ";
    private IMotorSql motorSql;

    public PuntosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Puntos p = (Puntos) bean;
        String sql = "INSERT INTO Puntos (ID_Usuario, PuntosActuales) VALUES (" +
                p.getId_usuario() + ", " + p.getPuntosActuales() + ")";
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object bean) {
        Puntos p = (Puntos) bean;
        String sql = "DELETE FROM Puntos WHERE ID_Puntos = " + p.getId_puntos();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Puntos p = (Puntos) bean;
        String sql = "UPDATE Puntos SET " +
                "ID_Usuario = " + p.getId_usuario() + ", " +
                "PuntosActuales = " + p.getPuntosActuales() +
                " WHERE ID_Puntos = " + p.getId_puntos();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Puntos> FindAll(Object bean) {
        ArrayList<Puntos> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Puntos p = (Puntos) bean;

                if (p.getId_puntos() > 0) {
                    sql += " AND ID_Puntos = " + p.getId_puntos();
                }
                if (p.getId_usuario() > 0) {
                    sql += " AND ID_Usuario = " + p.getId_usuario();
                }
                if (p.getPuntosActuales() > 0) {
                    sql += " AND PuntosActuales = " + p.getPuntosActuales();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs != null && rs.next()) {
                Puntos p = new Puntos();
                p.setId_puntos(rs.getInt("ID_Puntos"));
                p.setId_usuario(rs.getInt("ID_Usuario"));
                p.setPuntosActuales(rs.getInt("PuntosActuales"));

                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Puntos: " + e.getMessage());
        }

        return lista;
    }
}
