package model;

import java.util.Date;

public class Ofertas {
    private int id_oferta;
    private String nombre;
    private double precio;
    private String descripcion;
    private String columnaImagen;
    private Date fechaExpiracion;

    // Getters y setters
    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColumnaImagen() {
        return columnaImagen;
    }

    public void setColumnaImagen(String columnaImagen) {
        this.columnaImagen = columnaImagen;
    }
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String toString() {
        return "Ofertas{" +
                "id_oferta=" + id_oferta +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", columnaImagen='" + columnaImagen + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                '}';
    }
}
