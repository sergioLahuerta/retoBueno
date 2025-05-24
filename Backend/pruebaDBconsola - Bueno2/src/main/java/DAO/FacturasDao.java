package DAO;

import model.Facturas;

import java.sql.*;
import java.util.ArrayList;

public class FacturasDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM facturas WHERE 1=1 ";
    private IMotorSql motorSql;

    public FacturasDao() {
        motorSql = new MotorSql();
    }

    public int add(Facturas factura, Connection conn) throws SQLException {
        String sql = "INSERT INTO facturas (fechaFactura, importeTotal) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, new java.sql.Date(factura.getFechaFactura().getTime()));
        ps.setDouble(2, factura.getImporteTotal());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // devuelve el ID generado
        }
        return -1;
    }


    @Override
    public int add(Object bean) {
        return 0;
    }

    @Override
    public int delete(Object e) {
        Facturas factura = (Facturas) e;
        String sql = "DELETE FROM facturas WHERE id_factura = " + factura.getId_factura();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Facturas factura = (Facturas) bean;
        Date sqlDate = new Date(factura.getFechaFactura().getTime());

        String sql = "UPDATE facturas SET fechaFactura = '" + sqlDate + "', " +
                "importeTotal = " + factura.getImporteTotal() +
                " WHERE id_factura = " + factura.getId_factura();

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
                    sql += " AND id_factura = " + f.getId_factura();
                }
                if (f.getFechaFactura() != null) {
                    sql += " AND fechaFactura = '" + new Date(f.getFechaFactura().getTime()) + "'";
                }
                if (f.getImporteTotal() >= 0) {
                    sql += " AND importeTotal = " + f.getImporteTotal();
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);
            while (rs.next()) {
                Facturas facturaBd = new Facturas();
                facturaBd.setId_factura(rs.getInt("id_factura"));
                facturaBd.setFechaFactura(rs.getDate("fechaFactura"));
                facturaBd.setImporteTotal(rs.getDouble("importeTotal"));
                lista.add(facturaBd);
            }
        } catch (SQLException e) {
            System.out.println("Error en FindAll Facturas: " + e.getMessage());
        }

        return lista;
    }
}
