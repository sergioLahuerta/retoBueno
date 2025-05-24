package DAO;

import model.Ofertas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class OfertasDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM Ofertas WHERE 1=1 ";
    private IMotorSql motorSql;

    public OfertasDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Ofertas oferta = (Ofertas) bean;

        // Convertir java.util.Date a java.sql.Date
        Date sqlDate = new Date(oferta.getFechaExpiracion().getTime());

        String sql = "INSERT INTO Ofertas (nombre, precio, descripcion, columnaImagen, fechaExpiracion) VALUES ('" +
                oferta.getNombre() + "', " +
                oferta.getPrecio() + ", '" +
                oferta.getDescripcion() + "', '" +
                oferta.getColumnaImagen() + "', '" +
                sqlDate + "')";

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Ofertas oferta = (Ofertas) e;
        String sql = "DELETE FROM Ofertas WHERE id_oferta = " + oferta.getId_oferta();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Ofertas oferta = (Ofertas) bean;
        Date sqlDate = new Date(oferta.getFechaExpiracion().getTime());

        String sql = "UPDATE Ofertas SET " +
                "nombre = '" + oferta.getNombre() + "', " +
                "precio = " + oferta.getPrecio() + ", " +
                "descripcion = '" + oferta.getDescripcion() + "', " +
                "columnaImagen = '" + oferta.getColumnaImagen() + "', " +
                "fechaExpiracion = '" + sqlDate + "' " +
                "WHERE id_oferta = " + oferta.getId_oferta();

        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Ofertas> FindAll(Object bean) {
        ArrayList<Ofertas> ofertas = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Ofertas oferta = (Ofertas) bean;

                if (oferta.getId_oferta() > 0) {
                    sql += " AND ID_Oferta = " + oferta.getId_oferta();
                }
                if (oferta.getNombre() != null && !oferta.getNombre().isEmpty()) {
                    sql += " AND nombre = '" + oferta.getNombre() + "'";
                }
                // Cambia esta condición para que solo filtre si precio es mayor que cero:
                if (oferta.getPrecio() > 0) {
                    sql += " AND precio = " + oferta.getPrecio();
                }
                if (oferta.getDescripcion() != null && !oferta.getDescripcion().isEmpty()) {
                    sql += " AND descripcion = '" + oferta.getDescripcion() + "'";
                }
                if (oferta.getColumnaImagen() != null && !oferta.getColumnaImagen().isEmpty()) {
                    sql += " AND columnaImagen = '" + oferta.getColumnaImagen() + "'";
                }
                if (oferta.getFechaExpiracion() != null) {
                    sql += " AND fechaExpiracion = '" + new Date(oferta.getFechaExpiracion().getTime()) + "'";
                }
            }

            // Si bean es null, se ejecuta el SQL sin filtros: "SELECT * FROM ofertas WHERE 1=1"
            ResultSet rs = motorSql.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Ofertas ofertaBd = new Ofertas();
                    ofertaBd.setId_oferta(rs.getInt("ID_Oferta"));
                    ofertaBd.setNombre(rs.getString("nombre"));
                    ofertaBd.setPrecio(rs.getDouble("precio"));
                    ofertaBd.setDescripcion(rs.getString("descripcion"));
                    ofertaBd.setColumnaImagen(rs.getString("columnaImagen"));
                    ofertaBd.setFechaExpiracion(rs.getDate("fechaExpiracion"));

                    ofertas.add(ofertaBd);
                }
            } else {
                System.out.println("La consulta no devolvió resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta o procesar el ResultSet: " + e.getMessage());
            e.printStackTrace();
        }

        return ofertas;
    }
}