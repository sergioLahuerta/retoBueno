package DAO;

import model.Almacen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlmacenDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM almacen WHERE 1=1 ";
    private IMotorSql motorSql;

    public AlmacenDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Almacen a = (Almacen) bean;

        String sql = "INSERT INTO almacen (nombre, capacidad, ubicacion) VALUES ('" +
                a.getNombre() + "', " +
                a.getCapacidad() + ", '" +
                a.getUbicacion() + "')";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Almacen a = (Almacen) e;
        String sql = "DELETE FROM almacen WHERE id_almacen = " + a.getId_almacen();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Almacen a = (Almacen) bean;

        String sql = "UPDATE almacen SET " +
                "nombre = '" + a.getNombre() + "', " +
                "capacidad = " + a.getCapacidad() + ", " +
                "ubicacion = '" + a.getUbicacion() + "' " +
                "WHERE id_almacen = " + a.getId_almacen();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Almacen> FindAll(Object bean) {
        ArrayList<Almacen> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Almacen a = (Almacen) bean;

                if (a.getId_almacen() > 0) {
                    sql += " AND id_almacen = " + a.getId_almacen();
                }
                if (a.getNombre() != null && !a.getNombre().isEmpty()) {
                    sql += " AND nombre = '" + a.getNombre() + "'";
                }
                if (a.getCapacidad() > 0) {
                    sql += " AND capacidad = " + a.getCapacidad();
                }
                if (a.getUbicacion() != null && !a.getUbicacion().isEmpty()) {
                    sql += " AND ubicacion = '" + a.getUbicacion() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Almacen almacen = new Almacen();
                almacen.setId_almacen(rs.getInt("id_almacen"));
                almacen.setNombre(rs.getString("nombre"));
                almacen.setCapacidad(rs.getInt("capacidad"));
                almacen.setUbicacion(rs.getString("ubicacion"));

                lista.add(almacen);
            }

        } catch (SQLException e) {
            System.out.println("Error en FindAll Almacen: " + e.getMessage());
        }

        return lista;
    }
}
