package DAO;

import model.Proveedores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedoresDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Proveedores WHERE 1=1 ";
    private IMotorSql motorSql;

    public ProveedoresDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Proveedores p = (Proveedores) bean;

        String sql = "INSERT INTO Proveedores (nombreEmpresa, Telefono, Email) VALUES ('" +
                p.getNombreEmpresa() + "', '" +
                p.getTelefono() + "', '" +
                p.getEmail() + "')";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Proveedores p = (Proveedores) e;
        String sql = "DELETE FROM Proveedores WHERE ID_Proveedor = " + p.getId_proveedor();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Proveedores p = (Proveedores) bean;

        String sql = "UPDATE Proveedores SET " +
                "nombreEmpresa = '" + p.getNombreEmpresa() + "', " +
                "Telefono = '" + p.getTelefono() + "', " +
                "Email = '" + p.getEmail() + "' " +
                "WHERE ID_Proveedor = " + p.getId_proveedor();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Proveedores> FindAll(Object bean) {
        ArrayList<Proveedores> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Proveedores p = (Proveedores) bean;

                if (p.getId_proveedor() > 0) {
                    sql += " AND ID_Proveedor = " + p.getId_proveedor();
                }
                if (p.getNombreEmpresa() != null && !p.getNombreEmpresa().isEmpty()) {
                    sql += " AND nombreEmpresa = '" + p.getNombreEmpresa() + "'";
                }
                if (p.getTelefono() != null && !p.getTelefono().isEmpty()) {
                    sql += " AND Telefono = '" + p.getTelefono() + "'";
                }
                if (p.getEmail() != null && !p.getEmail().isEmpty()) {
                    sql += " AND Email = '" + p.getEmail() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Proveedores proveedor = new Proveedores();
                proveedor.setId_proveedor(rs.getInt("ID_Proveedor"));
                proveedor.setNombreEmpresa(rs.getString("nombreEmpresa"));
                proveedor.setTelefono(rs.getString("Telefono"));
                proveedor.setEmail(rs.getString("Email"));

                lista.add(proveedor);
            }

        } catch (SQLException e) {
            System.out.println("Error en FindAll Proveedores: " + e.getMessage());
        }

        return lista;
    }
}
