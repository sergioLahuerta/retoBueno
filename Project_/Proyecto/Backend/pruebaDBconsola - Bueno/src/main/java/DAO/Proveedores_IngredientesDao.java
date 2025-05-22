package DAO;

import model.Proveedores_Ingredientes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Proveedores_IngredientesDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM proveedores_ingredientes WHERE 1=1 ";
    private IMotorSql motorSql;

    public Proveedores_IngredientesDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Proveedores_Ingredientes pi = (Proveedores_Ingredientes) bean;

        String sql = "INSERT INTO proveedores_ingredientes (id_proveedor, id_ingrediente, precioUnitario, tiempoEntregaDias) VALUES (" +
                pi.getId_proveedor() + ", " +
                pi.getId_ingrediente() + ", " +
                pi.getPrecioUnitario() + ", " +
                pi.getTiempoEntregaDias() + ")";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Proveedores_Ingredientes pi = (Proveedores_Ingredientes) e;
        String sql = "DELETE FROM proveedores_ingredientes WHERE id_proveedorIngrediente = " + pi.getId_proveedorIngrediente();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Proveedores_Ingredientes pi = (Proveedores_Ingredientes) bean;

        String sql = "UPDATE proveedores_ingredientes SET " +
                "id_proveedor = " + pi.getId_proveedor() + ", " +
                "id_ingrediente = " + pi.getId_ingrediente() + ", " +
                "precioUnitario = " + pi.getPrecioUnitario() + ", " +
                "tiempoEntregaDias = " + pi.getTiempoEntregaDias() + " " +
                "WHERE id_proveedorIngrediente = " + pi.getId_proveedorIngrediente();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Proveedores_Ingredientes> FindAll(Object bean) {
        ArrayList<Proveedores_Ingredientes> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Proveedores_Ingredientes pi = (Proveedores_Ingredientes) bean;

                if (pi.getId_proveedorIngrediente() > 0) {
                    sql += " AND id_proveedorIngrediente = " + pi.getId_proveedorIngrediente();
                }
                if (pi.getId_proveedor() > 0) {
                    sql += " AND id_proveedor = " + pi.getId_proveedor();
                }
                if (pi.getId_ingrediente() > 0) {
                    sql += " AND id_ingrediente = " + pi.getId_ingrediente();
                }
                if (pi.getPrecioUnitario() >= 0) {
                    sql += " AND precioUnitario = " + pi.getPrecioUnitario();
                }
                if (pi.getTiempoEntregaDias() >= 0) {
                    sql += " AND tiempoEntregaDias = " + pi.getTiempoEntregaDias();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Proveedores_Ingredientes pi = new Proveedores_Ingredientes();
                pi.setId_proveedorIngrediente(rs.getInt("id_proveedorIngrediente"));
                pi.setId_proveedor(rs.getInt("id_proveedor"));
                pi.setId_ingrediente(rs.getInt("id_ingrediente"));
                pi.setPrecioUnitario(rs.getDouble("precioUnitario"));
                pi.setTiempoEntregaDias(rs.getInt("tiempoEntregaDias"));

                lista.add(pi);
            }

        } catch (SQLException e) {
            System.out.println("Error en FindAll Proveedores_Ingredientes: " + e.getMessage());
        }

        return lista;
    }
}
