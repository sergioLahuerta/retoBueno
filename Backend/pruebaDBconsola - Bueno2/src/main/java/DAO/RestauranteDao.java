package DAO;

import model.Restaurante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RestauranteDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Restaurante WHERE 1=1 ";
    private IMotorSql motorSql;

    public RestauranteDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Restaurante restaurante = (Restaurante) bean;

        String sql = "INSERT INTO Restaurante (Nombre, Direccion, Telefono, Email, Aforo, imagenRestaurante) VALUES ('" +
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
        String sql = "DELETE FROM Restaurante WHERE ID_Restaurante = " + restaurante.getId_restaurante();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Restaurante restaurante = (Restaurante) bean;

        String sql = "UPDATE Restaurante SET " +
                "nombre = '" + restaurante.getNombre() + "', " +
                "direccion = '" + restaurante.getDireccion() + "', " +
                "telefono = '" + restaurante.getTelefono() + "', " +
                "email = '" + restaurante.getEmail() + "', " +
                "aforo = " + restaurante.getAforo() + ", " +
                "imagenRestaurante = '" + restaurante.getImagenRestaurante() + "' " +
                "WHERE ID_Restaurante = " + restaurante.getId_restaurante();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList FindAll(Object bean) {
        return null;
    }

    public ArrayList<Restaurante> FindAll(Restaurante filtro) {
        ArrayList<Restaurante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante WHERE 1=1";

        if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
            sql += " AND Nombre LIKE '%" + filtro.getNombre() + "%'";
        }
        if (filtro.getDireccion() != null && !filtro.getDireccion().isEmpty()) {
            sql += " AND Direccion LIKE '%" + filtro.getDireccion() + "%'";
        }

        MotorSql motor = new MotorSql();
        try {
            motor.connect();
            ResultSet rs = motor.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Restaurante r = new Restaurante();
                    r.setId_restaurante(rs.getInt("ID_Restaurante"));  // <-- corregido aquí
                    r.setNombre(rs.getString("Nombre"));
                    r.setDireccion(rs.getString("Direccion"));
                    r.setTelefono(rs.getString("Telefono"));
                    r.setImagenRestaurante(rs.getString("imagenRestaurante"));
                    lista.add(r);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en DAO FindAll: " + e.getMessage());
            e.printStackTrace();
        } finally {
            motor.disconnected();
        }
        return lista;
    }


}
