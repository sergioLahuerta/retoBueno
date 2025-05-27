package model;

public class Almacen {
    private int id_almacen;
    private String nombre;
    private int capacidad;
    private String ubicacion;

    // Getters y Setters
    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Almacen{" +
                "ID_Almacen=" + id_almacen +
                ", Nombre='" + nombre + '\'' +
                ", Capacidad=" + capacidad +
                ", Ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
