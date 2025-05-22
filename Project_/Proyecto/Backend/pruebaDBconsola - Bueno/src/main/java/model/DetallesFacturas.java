package model;

public class DetallesFacturas {
    private int id_detalleFactura;
    private int id_detalle_pedido;
    private int id_factura;
    private double precioUnitario;
    private double totalLinea;
    private double descuento;

    // Getters y Setters
    public int getId_detalleFactura() {
        return id_detalleFactura;
    }

    public void setId_detalleFactura(int id_detalleFactura) {
        this.id_detalleFactura = id_detalleFactura;
    }

    public int getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(int id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotalLinea() {
        return totalLinea;
    }

    public void setTotalLinea(double totalLinea) {
        this.totalLinea = totalLinea;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "DetallesFacturas{" +
                "id_detalleFactura=" + id_detalleFactura +
                ", id_detalle_pedido=" + id_detalle_pedido +
                ", id_factura=" + id_factura +
                ", precioUnitario=" + precioUnitario +
                ", totalLinea=" + totalLinea +
                ", descuento=" + descuento +
                '}';
    }
}
