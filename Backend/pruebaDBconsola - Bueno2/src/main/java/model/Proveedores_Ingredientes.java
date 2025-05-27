package model;

public class Proveedores_Ingredientes {
    private int id_proveedorIngrediente;
    private int id_proveedor;
    private int id_ingrediente;
    private double precioUnitario;
    private int tiempoEntregaDias;

    // Getters y Setters
    public int getId_proveedorIngrediente() {
        return id_proveedorIngrediente;
    }

    public void setId_proveedorIngrediente(int id_proveedorIngrediente) {
        this.id_proveedorIngrediente = id_proveedorIngrediente;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getTiempoEntregaDias() {
        return tiempoEntregaDias;
    }

    public void setTiempoEntregaDias(int tiempoEntregaDias) {
        this.tiempoEntregaDias = tiempoEntregaDias;
    }

    @Override
    public String toString() {
        return "Proveedores_Ingredientes{" +
                "ID_ProveedorIngrediente=" + id_proveedorIngrediente +
                ", ID_Proveedor=" + id_proveedor +
                ", ID_Ingrediente=" + id_ingrediente +
                ", PrecioUnitario=" + precioUnitario +
                ", TiempoEntregaDias=" + tiempoEntregaDias +
                '}';
    }
}
