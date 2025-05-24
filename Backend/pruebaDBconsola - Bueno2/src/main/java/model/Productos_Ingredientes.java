package model;

public class Productos_Ingredientes {
    private int id_productoIngrediente;
    private int id_ingrediente;
    private int id_producto;
    private double cantidad;

    public int getId_productoIngrediente() {
        return id_productoIngrediente;
    }

    public void setId_productoIngrediente(int id_productoIngrediente) {
        this.id_productoIngrediente = id_productoIngrediente;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }


}
