package org.example;

import DAO.MotorSql;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        MotorSql motor = new MotorSql();
        try {
            motor.connect();
            ResultSet rs = motor.executeQuery("SELECT * FROM Restaurante LIMIT 1");
            if (rs != null && rs.next()) {
                System.out.println("Restaurante: " + rs.getString("nombre"));
            } else {
                System.out.println("No se encontraron restaurantes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motor.disconnected();
        }
    }
}
