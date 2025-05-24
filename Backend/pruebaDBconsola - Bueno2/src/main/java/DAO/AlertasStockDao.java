package DAO;

import model.AlertasStock;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlertasStockDao implements iDao {
    private final String SQL_FIND_ALL = "SELECT * FROM Alertas_Stock WHERE 1=1 ";
    private IMotorSql motorSql;

    public AlertasStockDao() {
        motorSql = new MotorSql();
    }

    @Override
    public ArrayList<AlertasStock> FindAll(Object bean) {
        ArrayList<AlertasStock> lista = new ArrayList<>();
        String sql = SQL_FIND_ALL;

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                AlertasStock alerta = new AlertasStock();
                alerta.setIdAlerta(rs.getInt("ID_Alerta"));
                alerta.setIdIngrediente(rs.getInt("ID_Ingrediente"));
                alerta.setFechaAlerta(rs.getTimestamp("FechaAlerta"));
                alerta.setStockDisponible(rs.getDouble("StockDisponible"));
                alerta.setMensaje(rs.getString("Mensaje"));

                lista.add(alerta);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error en AlertasStockDao -> FindAll: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public int add(Object bean) {
        throw new UnsupportedOperationException("No se permite añadir alertas manualmente.");
    }

    @Override
    public int delete(Object bean) {
        throw new UnsupportedOperationException("No se permite eliminar alertas.");
    }

    @Override
    public int update(Object bean) {
        throw new UnsupportedOperationException("No se permite actualizar alertas.");
    }
}
