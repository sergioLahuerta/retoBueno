package model;

public class DetallesPedidos {
    private int id_detallePedido;
    private int id_pedido;
    private int id_producto;
    private int cantidad;
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

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Detalles_Pedidos{" +
                "ID_DetallePedido=" + id_detallePedido +
                ", ID_Pedido=" + id_pedido +
                ", ID_Producto=" + id_producto +
                ", Cantidad=" + cantidad +
                ", Observaciones='" + observaciones + '\'' +
                '}';
    }
}
