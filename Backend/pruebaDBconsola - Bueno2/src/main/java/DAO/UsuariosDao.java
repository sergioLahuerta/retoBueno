package DAO;

import model.Usuarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuariosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Usuarios WHERE 1=1 ";
    private IMotorSql motorSql;

    public UsuariosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Usuarios usuario = (Usuarios) bean;

        String sql = "INSERT INTO usuarios (Nombre, Email, Contrasena, DNI, Telefono, Direccion) VALUES ('" +
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
        String sql = "DELETE FROM Usuarios WHERE ID_Usuario = " + usuario.getId_usuario();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Usuarios usuario = (Usuarios) bean;

        String sql = "UPDATE usuarios SET " +
                "Nombre = '" + usuario.getNombre() + "', " +
                "Email = '" + usuario.getEmail() + "', " +
                "Contrasena = '" + usuario.getContrasena() + "', " +
                "DNI = '" + usuario.getDni() + "', " +
                "Telefono = '" + usuario.getTelefono() + "', " +
                "Direccion = '" + usuario.getDireccion() + "' " +
                "WHERE ID_Usuario = " + usuario.getId_usuario();

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
                    sql += " AND ID_Usuario = " + u.getId_usuario();
                }
                if (u.getNombre() != null && !u.getNombre().isEmpty()) {
                    sql += " AND Nombre = '" + u.getNombre() + "'";
                }
                if (u.getEmail() != null && !u.getEmail().isEmpty()) {
                    sql += " AND Email = '" + u.getEmail() + "'";
                }
                if (u.getContrasena() != null && !u.getContrasena().isEmpty()) {
                    sql += " AND Contrasena = '" + u.getContrasena() + "'";
                }
                if (u.getDni() != null && !u.getDni().isEmpty()) {
                    sql += " AND DNI = '" + u.getDni() + "'";
                }
                if (u.getTelefono() != null && !u.getTelefono().isEmpty()) {
                    sql += " AND Telefono = '" + u.getTelefono() + "'";
                }
                if (u.getDireccion() != null && !u.getDireccion().isEmpty()) {
                    sql += " AND Direccion = '" + u.getDireccion() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setDni(rs.getString("DNI"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setDireccion(rs.getString("Direccion"));

                lista.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Error en FindAll Usuarios: " + ex.getMessage());
        }

        return lista;
    }
}
