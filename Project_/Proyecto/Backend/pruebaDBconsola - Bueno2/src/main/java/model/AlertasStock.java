package model;

import java.util.Date;

public class AlertasStock {
    private int idAlerta;
    private int idIngrediente;
    private Date fechaAlerta;
    private double stockDisponible;
    private String mensaje;

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public double getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(double stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "AlertaStock{" +
                "idAlerta=" + idAlerta +
                ", idIngrediente=" + idIngrediente +
                ", fechaAlerta=" + fechaAlerta +
                ", stockDisponible=" + stockDisponible +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
