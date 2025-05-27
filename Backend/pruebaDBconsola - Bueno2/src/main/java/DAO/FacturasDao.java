package DAO;

import model.Facturas;

import java.sql.*;
import java.util.ArrayList;

public class FacturasDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Facturas WHERE 1=1 "; //Lo encontrará si soy capaz de escribir el nombre de tablasy campos excatamente como en el .sql, en fin, que despiste.
    private IMotorSql motorSql;

    public FacturasDao() {
        motorSql = new MotorSql();
    }

    public static int add(Facturas factura, Connection conn) throws SQLException {
        String sql = "INSERT INTO Facturas (FechaFactura, ImporteTotal) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (factura.getFechaFactura() == null) {
                throw new IllegalArgumentException("FechaFactura no puede ser null");
            }
            ps.setDate(1, new java.sql.Date(factura.getFechaFactura().getTime()));
            ps.setDouble(2, factura.getImporteTotal());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert factura falló, no se afectaron filas.");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("No se generó ID para factura.");
                }
            }
        }
    }


    @Override
    public int add(Object bean) {
        return 0;
    }

    @Override
    public int delete(Object e) {
        Facturas factura = (Facturas) e;
        String sql = "DELETE FROM Facturas WHERE ID_Factura = " + factura.getId_factura();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Facturas factura = (Facturas) bean;
        Date sqlDate = new Date(factura.getFechaFactura().getTime());

        String sql = "UPDATE Facturas SET FechaFactura = '" + sqlDate + "', " +
                "ImporteTotal = " + factura.getImporteTotal() +
                " WHERE ID_Factura = " + factura.getId_factura();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Facturas> FindAll(Object bean) {
        ArrayList<Facturas> lista = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();
            if (bean != null) {
                Facturas f = (Facturas) bean;
                if (f.getId_factura() > 0) {
                    sql += " AND ID_Factura = " + f.getId_factura();
                }
                if (f.getFechaFactura() != null) {
                    sql += " AND FechaFactura = '" + new Date(f.getFechaFactura().getTime()) + "'";
                }
                if (f.getImporteTotal() >= 0) {
                    sql += " AND ImporteTotal = " + f.getImporteTotal();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);
            while (rs.next()) {
                Facturas facturaBd = new Facturas();
                facturaBd.setId_factura(rs.getInt("ID_Factura"));
                facturaBd.setFechaFactura(rs.getDate("FechaFactura"));
                facturaBd.setImporteTotal(rs.getDouble("ImporteTotal"));
                lista.add(facturaBd);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Facturas: " + e.getMessage());
        }

        return lista;
    }
}
