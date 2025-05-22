package DAO;

import java.sql.Date;
import java.sql.ResultSet;

public interface IMotorSql {
    public void connect();

    public int execute(String sql);

    public ResultSet executeQuery(String sql);

    public void disconnected();

    int executeUpdate(String sql);
}
