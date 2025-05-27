package model;

import java.util.ArrayList;
import java.util.Date;

public class Empleados implements IModel{
    private int id_empleado;
    private int id_restaurante;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private double sueldo;
    private Date fechaContratacion;

    // Getters y Setters
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "ID_Empleado=" + id_empleado +
                ", ID_Restaurante=" + id_restaurante +
                ", Nombre='" + nombre + '\'' +
                ", Apellidos='" + apellidos + '\'' +
                ", DNI='" + dni + '\'' +
                ", Telefono='" + telefono + '\'' +
                ", Sueldo=" + sueldo +
                ", FechaContratacion=" + fechaContratacion +
                '}';
    }

    @Override
    public String fromArrayToJson(ArrayList bean) {

        return "";
    }

    @Override
    public String toArrayJson(ArrayList bean) {
        return "";
    }

    @Override
    public String toSqlWhereString() {
        return "";
    }
}
