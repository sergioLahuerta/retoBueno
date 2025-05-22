package DAO;

import java.sql.*;

public class MotorSql implements IMotorSql {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/esquema_reto";
    public static final String MYSQL_USER = "root";
    public static final String MYSQL_PASS = "Aa12021888.";

    private Connection m_Connection;
    private Statement m_Statement;
    private ResultSet m_Resulset;

    @Override
    public void connect() {
        try {
            // Cargar el driver JDBC
            Class.forName(DRIVER_NAME);

            // Establecer la conexión
            m_Connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASS);

            // Crear el statement
            m_Statement = m_Connection.createStatement();
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException sqlEx) {
            System.out.println("Error en la conexión: " + sqlEx.getMessage());
        }
    }

    @Override
    public int execute(String sql) {
        int filasAfectadas = 0;
        try {
            // Ejecutar una consulta de tipo INSERT, UPDATE o DELETE
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
            // Ejecutar la consulta SELECT
            m_Resulset = m_Statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
            ex.printStackTrace(); // Esto es para ver el stack trace de la excepción
            return null; // Si ocurre un error, devolvemos null
        }
        return m_Resulset;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado", e);
        }
        return DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASS);
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
}
