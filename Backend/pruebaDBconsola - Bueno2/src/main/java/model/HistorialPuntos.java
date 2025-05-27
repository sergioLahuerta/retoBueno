package model;

import java.util.Date;

public class HistorialPuntos {
    private int id_historialPuntos;
    private int id_factura;
    private Date fecha;
    private int puntos;
    private String tipoMovimiento; // 'GANADO' o 'CANJEADO'
    private String descripcion;

    // Getters y Setters
    public int getId_historialPuntos() {
        return id_historialPuntos;
    }

    public void setId_historialPuntos(int id_historialPuntos) {
        this.id_historialPuntos = id_historialPuntos;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Historial_Puntos{" +
                "ID_HistorialPuntos=" + id_historialPuntos +
                ", ID_Factura=" + id_factura +
                ", Fecha=" + fecha +
                ", Puntos=" + puntos +
                ", TipoMovimiento='" + tipoMovimiento + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                '}';
    }
}
