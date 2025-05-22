package model;

public class DetallesPedidos {
    private int id_detallePedido;
    private int id_pedido;
    private int id_producto;
    private int cantidad;
    private double precioUnidad;
    private String observaciones;

    // Getters y Setters
    public int getId_detallePedido() {
        return id_detallePedido;
    }

    public void setId_detallePedido(int id_detallePedido) {
        this.id_detallePedido = id_detallePedido;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "DetallesPedidos{" +
                "id_detallePedido=" + id_detallePedido +
                ", id_pedido=" + id_pedido +
                ", id_producto=" + id_producto +
                ", cantidad=" + cantidad +
                ", precioUnidad=" + precioUnidad +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
