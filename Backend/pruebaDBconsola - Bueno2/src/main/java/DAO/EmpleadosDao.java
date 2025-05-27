package DAO;

import model.Empleados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class EmpleadosDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Empleados WHERE 1=1 ";
    private IMotorSql motorSql;

    public EmpleadosDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Empleados empleado = (Empleados) bean;
        Date sqlDate = new Date(empleado.getFechaContratacion().getTime());

        String sql = "INSERT INTO Empleados (ID_Restaurante, Nombre, Apellidos, DNI, Telefono, Sueldo, FechaContratacion) VALUES (" +
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
        String sql = "DELETE FROM Empleados WHERE ID_Empleado = " + empleado.getId_empleado();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Empleados empleado = (Empleados) bean;
        Date sqlDate = new Date(empleado.getFechaContratacion().getTime());

        String sql = "UPDATE Empleados SET " +
                "ID_Restaurante = " + empleado.getId_restaurante() + ", " +
                "Nombre = '" + empleado.getNombre() + "', " +
                "Apellidos = '" + empleado.getApellidos() + "', " +
                "DNI = '" + empleado.getDni() + "', " +
                "Telefono = '" + empleado.getTelefono() + "', " +
                "Sueldo = " + empleado.getSueldo() + ", " +
                "FechaContratacion = '" + sqlDate + "' " +
                "WHERE ID_Empleado = " + empleado.getId_empleado();

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
                    sql += " AND ID_Empleado = " + e.getId_empleado();
                }
                if (e.getId_restaurante() > 0) {
                    sql += " AND ID_Restaurante = " + e.getId_restaurante();
                }
                if (e.getNombre() != null && !e.getNombre().isEmpty()) {
                    sql += " AND Nombre = '" + e.getNombre() + "'";
                }
                if (e.getApellidos() != null && !e.getApellidos().isEmpty()) {
                    sql += " AND Apellidos = '" + e.getApellidos() + "'";
                }
                if (e.getDni() != null && !e.getDni().isEmpty()) {
                    sql += " AND DNI = '" + e.getDni() + "'";
                }
                if (e.getTelefono() != null && !e.getTelefono().isEmpty()) {
                    sql += " AND Telefono = '" + e.getTelefono() + "'";
                }
                if (e.getSueldo() >= 0) {
                    sql += " AND Sueldo = " + e.getSueldo();
                }
                if (e.getFechaContratacion() != null) {
                    sql += " AND FechaContratacion = '" + new Date(e.getFechaContratacion().getTime()) + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Empleados empleado = new Empleados();
                empleado.setId_empleado(rs.getInt("ID_Empleado"));
                empleado.setId_restaurante(rs.getInt("ID_Restaurante"));
                empleado.setNombre(rs.getString("Nombre"));
                empleado.setApellidos(rs.getString("apellidos"));
                empleado.setDni(rs.getString("DNI"));
                empleado.setTelefono(rs.getString("Telefono"));
                empleado.setSueldo(rs.getDouble("Sueldo"));
                empleado.setFechaContratacion(rs.getDate("FechaContratacion"));

                lista.add(empleado);
            }
        } catch (SQLException ex) {
            System.out.println("Error en FindAll Empleados: " + ex.getMessage());
        }

        return lista;
    }
}
