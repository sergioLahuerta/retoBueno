package model;

public class Pedidos {
    private int id_pedido;
    private int id_factura;
    private int id_restaurante;
    private int id_usuario;
    private int numero;

    // Getters y Setters
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "ID_Pedido=" + id_pedido +
                ", ID_Factura=" + id_factura +
                ", ID_Restaurante=" + id_restaurante +
                ", ID_Usuario=" + id_usuario +
                ", Numero=" + numero +
                '}';
    }
}
