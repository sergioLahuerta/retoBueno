package model;

public class Puntos {
    private int id_puntos;
    private int id_usuario;
    private int puntosActuales;

    // Getters y Setters
    public int getId_puntos() {
        return id_puntos;
    }

    public void setId_puntos(int id_puntos) {
        this.id_puntos = id_puntos;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getPuntosActuales() {
        return puntosActuales;
    }

    public void setPuntosActuales(int puntosActuales) {
        this.puntosActuales = puntosActuales;
    }

    @Override
    public String toString() {
        return "Puntos{" +
                "ID_Puntos=" + id_puntos +
                ", ID_Usuario=" + id_usuario +
                ", PuntosActuales=" + puntosActuales +
                '}';
    }
}
