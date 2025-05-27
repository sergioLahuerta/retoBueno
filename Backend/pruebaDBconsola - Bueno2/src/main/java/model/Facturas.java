package model;

import java.util.Date;

public class Facturas {
    private int id_factura;
    private Date fechaFactura;
    private double importeTotal;

    // Getters y setters
    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    @Override
    public String toString() {
        return "Facturas{" +
                "ID_Factura=" + id_factura +
                ", FechaFactura=" + fechaFactura +
                ", ImporteTotal=" + importeTotal +
                '}';
    }
}
