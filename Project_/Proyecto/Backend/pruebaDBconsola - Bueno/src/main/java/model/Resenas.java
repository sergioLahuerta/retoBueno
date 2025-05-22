package model;

import java.util.Date;

public class Resenas {
    private int id_resena;
    private int id_usuario;
    private int id_restaurante;
    private int valoracion;
    private Date fecha;

    // Getters y setters
    public int getId_resena() {
        return id_resena;
    }

    public void setId_resena(int id_resena) {
        this.id_resena = id_resena;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Resenas{" +
                "id_resena=" + id_resena +
                ", id_usuario=" + id_usuario +
                ", id_restaurante=" + id_restaurante +
                ", valoracion=" + valoracion +
                ", fecha=" + fecha +
                '}';
    }
}
