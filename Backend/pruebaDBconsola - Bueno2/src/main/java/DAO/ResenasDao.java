package DAO;

import model.Resenas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class ResenasDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM resenas WHERE 1=1 ";
    private IMotorSql motorSql;

    public ResenasDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Resenas resena = (Resenas) bean;
        Date sqlDate = null;
        if (resena.getFecha() != null) {
            sqlDate = new Date(resena.getFecha().getTime());
        }

        String sql = "INSERT INTO resenas (ID_Usuario, ID_Restaurante, Valoracion, Fecha) VALUES (" +
                resena.getId_usuario() + ", " +
                resena.getId_restaurante() + ", " +
                resena.getValoracion() + ", " +
                (sqlDate != null ? "'" + sqlDate + "'" : "NULL") + ")";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Resenas resena = (Resenas) e;
        String sql = "DELETE FROM resenas WHERE ID_Resena = " + resena.getId_resena();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Resenas resena = (Resenas) bean;
        Date sqlDate = null;
        if (resena.getFecha() != null) {
            sqlDate = new Date(resena.getFecha().getTime());
        }

        String sql = "UPDATE resenas SET " +
                "ID_Usuario = " + resena.getId_usuario() + ", " +
                "ID_Restaurante = " + resena.getId_restaurante() + ", " +
                "Valoracion = " + resena.getValoracion() + ", " +
                "Fecha = " + (sqlDate != null ? "'" + sqlDate + "'" : "NULL") + " " +
                "WHERE ID_Resena = " + resena.getId_resena();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Resenas> FindAll(Object bean) {
        ArrayList<Resenas> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Resenas r = (Resenas) bean;

                if (r.getId_resena() > 0) {
                    sql += " AND ID_Resena = " + r.getId_resena();
                }
                if (r.getId_usuario() > 0) {
                    sql += " AND ID_Usuario = " + r.getId_usuario();
                }
                if (r.getId_restaurante() > 0) {
                    sql += " AND ID_Restaurante = " + r.getId_restaurante();
                }
                if (r.getValoracion() >= 0) {
                    sql += " AND Valoracion = " + r.getValoracion();
                }
                if (r.getFecha() != null) {
                    sql += " AND Fecha = '" + new Date(r.getFecha().getTime()) + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Resenas resenaBd = new Resenas();
                resenaBd.setId_resena(rs.getInt("ID_Resena"));
                resenaBd.setId_usuario(rs.getInt("ID_Usuario"));
                resenaBd.setId_restaurante(rs.getInt("ID_Restaurante"));
                resenaBd.setValoracion(rs.getInt("Valoracion"));
                resenaBd.setFecha(rs.getDate("Fecha"));

                lista.add(resenaBd);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Resenas: " + e.getMessage());
        }

        return lista;
    }
}
