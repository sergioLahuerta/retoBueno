package DAO;

import model.Empleados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class EmpleadosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM empleados WHERE 1=1 ";
    private IMotorSql motorSql;

    public EmpleadosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Empleados empleado = (Empleados) bean;
        Date sqlDate = new Date(empleado.getFechaContratacion().getTime());

        String sql = "INSERT INTO empleados (id_restaurante, nombre, apellidos, dni, telefono, sueldo, fechaContratacion) VALUES (" +
                empleado.getId_restaurante() + ", '" +
                empleado.getNombre() + "', '" +
                empleado.getApellidos() + "', '" +
                empleado.getDni() + "', '" +
                empleado.getTelefono() + "', " +
                empleado.getSueldo() + ", '" +
                sqlDate + "')";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Empleados empleado = (Empleados) e;
        String sql = "DELETE FROM empleados WHERE id_empleado = " + empleado.getId_empleado();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Empleados empleado = (Empleados) bean;
        Date sqlDate = new Date(empleado.getFechaContratacion().getTime());

        String sql = "UPDATE empleados SET " +
                "id_restaurante = " + empleado.getId_restaurante() + ", " +
                "nombre = '" + empleado.getNombre() + "', " +
                "apellidos = '" + empleado.getApellidos() + "', " +
                "dni = '" + empleado.getDni() + "', " +
                "telefono = '" + empleado.getTelefono() + "', " +
                "sueldo = " + empleado.getSueldo() + ", " +
                "fechaContratacion = '" + sqlDate + "' " +
                "WHERE id_empleado = " + empleado.getId_empleado();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Empleados> FindAll(Object bean) {
        ArrayList<Empleados> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Empleados e = (Empleados) bean;

                if (e.getId_empleado() > 0) {
                    sql += " AND id_empleado = " + e.getId_empleado();
                }
                if (e.getId_restaurante() > 0) {
                    sql += " AND id_restaurante = " + e.getId_restaurante();
                }
                if (e.getNombre() != null && !e.getNombre().isEmpty()) {
                    sql += " AND nombre = '" + e.getNombre() + "'";
                }
                if (e.getApellidos() != null && !e.getApellidos().isEmpty()) {
                    sql += " AND apellidos = '" + e.getApellidos() + "'";
                }
                if (e.getDni() != null && !e.getDni().isEmpty()) {
                    sql += " AND dni = '" + e.getDni() + "'";
                }
                if (e.getTelefono() != null && !e.getTelefono().isEmpty()) {
                    sql += " AND telefono = '" + e.getTelefono() + "'";
                }
                if (e.getSueldo() >= 0) {
                    sql += " AND sueldo = " + e.getSueldo();
                }
                if (e.getFechaContratacion() != null) {
                    sql += " AND fechaContratacion = '" + new Date(e.getFechaContratacion().getTime()) + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Empleados empleado = new Empleados();
                empleado.setId_empleado(rs.getInt("id_empleado"));
                empleado.setId_restaurante(rs.getInt("id_restaurante"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidos(rs.getString("apellidos"));
                empleado.setDni(rs.getString("dni"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setSueldo(rs.getDouble("sueldo"));
                empleado.setFechaContratacion(rs.getDate("fechaContratacion"));

                lista.add(empleado);
            }
        } catch (SQLException ex) {
            System.out.println("Error en FindAll Empleados: " + ex.getMessage());
        }

        return lista;
    }
}
