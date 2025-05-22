package DAO;

import model.Restaurante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RestauranteDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM restaurante WHERE 1=1 ";
    private IMotorSql motorSql;

    public RestauranteDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Restaurante restaurante = (Restaurante) bean;

        String sql = "INSERT INTO restaurante (nombre, direccion, telefono, email, aforo, imagenRestaurante) VALUES ('" +
                restaurante.getNombre() + "', '" +
                restaurante.getDireccion() + "', '" +
                restaurante.getTelefono() + "', '" +
                restaurante.getEmail() + "', " +
                restaurante.getAforo() + ", '" +
                restaurante.getImagenRestaurante() + "')";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Restaurante restaurante = (Restaurante) e;
        String sql = "DELETE FROM restaurante WHERE id_restaurante = " + restaurante.getId_restaurante();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Restaurante restaurante = (Restaurante) bean;

        String sql = "UPDATE restaurante SET " +
                "nombre = '" + restaurante.getNombre() + "', " +
                "direccion = '" + restaurante.getDireccion() + "', " +
                "telefono = '" + restaurante.getTelefono() + "', " +
                "email = '" + restaurante.getEmail() + "', " +
                "aforo = " + restaurante.getAforo() + ", " +
                "imagenRestaurante = '" + restaurante.getImagenRestaurante() + "' " +
                "WHERE id_restaurante = " + restaurante.getId_restaurante();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Restaurante> FindAll(Object bean) {
        ArrayList<Restaurante> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Restaurante r = (Restaurante) bean;

                if (r.getId_restaurante() > 0) {
                    sql += " AND id_restaurante = " + r.getId_restaurante();
                }
                if (r.getNombre() != null && !r.getNombre().isEmpty()) {
                    sql += " AND nombre = '" + r.getNombre() + "'";
                }
                if (r.getDireccion() != null && !r.getDireccion().isEmpty()) {
                    sql += " AND direccion = '" + r.getDireccion() + "'";
                }
                if (r.getTelefono() != null && !r.getTelefono().isEmpty()) {
                    sql += " AND telefono = '" + r.getTelefono() + "'";
                }
                if (r.getEmail() != null && !r.getEmail().isEmpty()) {
                    sql += " AND email = '" + r.getEmail() + "'";
                }
                if (r.getAforo() > 0) {
                    sql += " AND aforo = " + r.getAforo();
                }
                if (r.getImagenRestaurante() != null && !r.getImagenRestaurante().isEmpty()) {
                    sql += " AND imagenRestaurante = '" + r.getImagenRestaurante() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Restaurante restaurante = new Restaurante();
                restaurante.setId_restaurante(rs.getInt("id_restaurante"));
                restaurante.setNombre(rs.getString("nombre"));
                restaurante.setDireccion(rs.getString("direccion"));
                restaurante.setTelefono(rs.getString("telefono"));
                restaurante.setEmail(rs.getString("email"));
                restaurante.setAforo(rs.getInt("aforo"));
                restaurante.setImagenRestaurante(rs.getString("imagenRestaurante"));
                lista.add(restaurante);
            }

        } catch (SQLException e) {
            System.out.println("Error en FindAll Restaurante: " + e.getMessage());
        }

        return lista;
    }
}
