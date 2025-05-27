package DAO;

import java.sql.*;

public class MotorSql implements IMotorSql {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://retodb.cwaiwnvcnh5j.us-east-1.rds.amazonaws.com:3306/esquema_reto";
    public static final String MYSQL_USER = "root";
    public static final String MYSQL_PASS = "Aa12021888.";

    public Connection m_Connection;
    private Statement m_Statement;
    private ResultSet m_Resulset;

    @Override
    public Connection connect() {
        try {
            Class.forName(DRIVER_NAME);
            m_Connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASS);
            m_Statement = m_Connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException sqlEx) {
            System.out.println("Error en la conexión: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        return m_Connection;
    }



    @Override
    public int execute(String sql) {
        int filasAfectadas = 0;
        try {
            //Ejecutar una consulta de tipo INSERT, UPDATE o DELETE
            filasAfectadas = m_Statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el SQL: " + ex.getMessage());
        }
        return filasAfectadas;
    }

    @Override
    public ResultSet executeQuery(String sql) {
        if (m_Statement == null) {
            System.out.println("Statement no ha sido inicializado. Asegúrate de conectar primero.");
            return null;
        }

        try {
            //Ejecutar la consulta SELECT
            m_Resulset = m_Statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
            ex.printStackTrace();
            return null; //En caso de error, devuelve null.
        }
        return m_Resulset;
    }

    @Override
    public void disconnected() {
        try {
            if (m_Resulset != null) {
                m_Resulset.close();
            }
            if (m_Statement != null) {
                m_Statement.close();
            }
            if (m_Connection != null) {
                m_Connection.close();
            }
            System.out.println("Desconexión exitosa.");
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }

    @Override
    public int executeUpdate(String sql) {
        return execute(sql);
    }
    public Connection getConnection() {
        return m_Connection;
    }
}
