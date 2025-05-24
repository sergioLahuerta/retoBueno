package DAO;

import model.Usuarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuariosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM usuarios WHERE 1=1 ";
    private IMotorSql motorSql;

    public UsuariosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Usuarios usuario = (Usuarios) bean;

        String sql = "INSERT INTO usuarios (nombre, email, contrasena, dni, telefono, direccion) VALUES ('" +
                usuario.getNombre() + "', '" +
                usuario.getEmail() + "', '" +
                usuario.getContrasena() + "', '" +
                usuario.getDni() + "', '" +
                usuario.getTelefono() + "', '" +
                usuario.getDireccion() + "')";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Usuarios usuario = (Usuarios) e;
        String sql = "DELETE FROM usuarios WHERE id_usuario = " + usuario.getId_usuario();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Usuarios usuario = (Usuarios) bean;

        String sql = "UPDATE usuarios SET " +
                "nombre = '" + usuario.getNombre() + "', " +
                "email = '" + usuario.getEmail() + "', " +
                "contrasena = '" + usuario.getContrasena() + "', " +
                "dni = '" + usuario.getDni() + "', " +
                "telefono = '" + usuario.getTelefono() + "', " +
                "direccion = '" + usuario.getDireccion() + "' " +
                "WHERE id_usuario = " + usuario.getId_usuario();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Usuarios> FindAll(Object bean) {
        ArrayList<Usuarios> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Usuarios u = (Usuarios) bean;

                if (u.getId_usuario() > 0) {
                    sql += " AND id_usuario = " + u.getId_usuario();
                }
                if (u.getNombre() != null && !u.getNombre().isEmpty()) {
                    sql += " AND nombre = '" + u.getNombre() + "'";
                }
                if (u.getEmail() != null && !u.getEmail().isEmpty()) {
                    sql += " AND email = '" + u.getEmail() + "'";
                }
                if (u.getContrasena() != null && !u.getContrasena().isEmpty()) {
                    sql += " AND contrasena = '" + u.getContrasena() + "'";
                }
                if (u.getDni() != null && !u.getDni().isEmpty()) {
                    sql += " AND dni = '" + u.getDni() + "'";
                }
                if (u.getTelefono() != null && !u.getTelefono().isEmpty()) {
                    sql += " AND telefono = '" + u.getTelefono() + "'";
                }
                if (u.getDireccion() != null && !u.getDireccion().isEmpty()) {
                    sql += " AND direccion = '" + u.getDireccion() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setDni(rs.getString("dni"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));

                lista.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Error en FindAll Usuarios: " + ex.getMessage());
        }

        return lista;
    }
}
