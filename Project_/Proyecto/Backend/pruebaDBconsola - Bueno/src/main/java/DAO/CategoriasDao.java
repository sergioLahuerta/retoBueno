package DAO;

import model.Categorias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriasDao implements iDao {
    private final String SQL_FIND = "SELECT * FROM categorias WHERE 1=1 ";
    private IMotorSql motorSql;

    public CategoriasDao() {
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        Categorias categoria = (Categorias) bean;
        String sql = "INSERT INTO categorias (nombre) VALUES ('" + categoria.getNombre() + "')";
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int delete(Object e) {
        Categorias categoria = (Categorias) e;
        String sql = "DELETE FROM categorias WHERE id_categoria = " + categoria.getId_categoria();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public int update(Object bean) {
        Categorias categoria = (Categorias) bean;
        String sql = "UPDATE categorias SET nombre = '" + categoria.getNombre() + "' WHERE id_categoria = " + categoria.getId_categoria();
        motorSql.connect();
        return motorSql.executeUpdate(sql);
    }

    @Override
    public ArrayList<Categorias> FindAll(Object bean) {
        ArrayList<Categorias> categorias = new ArrayList<>();
        String sql = SQL_FIND;

        try {
            motorSql.connect();

            if (bean != null) {
                Categorias categoria = (Categorias) bean;

                if (categoria.getId_categoria() > 0) {
                    sql += " AND id_categoria = " + categoria.getId_categoria();
                }
                if (categoria.getNombre() != null && !categoria.getNombre().isEmpty()) {
                    sql += " AND nombre = '" + categoria.getNombre() + "'";
                }
            }

            ResultSet rs = motorSql.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Categorias categoriaBd = new Categorias();
                    categoriaBd.setId_categoria(rs.getInt("id_categoria"));
                    categoriaBd.setNombre(rs.getString("nombre"));

                    categorias.add(categoriaBd);
                }
            } else {
                System.out.println("La consulta no devolvió resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta o procesar el ResultSet: " + e.getMessage());
            e.printStackTrace();
        }

        return categorias;
    }
}
