package DAO;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IMotorSql {
    public Connection connect();

    public int execute(String sql);

    public ResultSet executeQuery(String sql);

    public void disconnected();

    int executeUpdate(String sql);

}
