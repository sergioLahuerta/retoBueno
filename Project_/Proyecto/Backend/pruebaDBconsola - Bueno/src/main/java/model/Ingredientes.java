package model;

public class Ingredientes {
    private int id_ingrediente;
    private String nombre;
    private String unidadMedida;
    private double stockDisponible;
    private String tipoAlmacenamiento;
    private boolean disponible;

    // Getters y setters
    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(double stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getTipoAlmacenamiento() {
        return tipoAlmacenamiento;
    }

    public void setTipoAlmacenamiento(String tipoAlmacenamiento) {
        this.tipoAlmacenamiento = tipoAlmacenamiento;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Ingredientes{" +
                "id_ingrediente=" + id_ingrediente +
                ", nombre='" + nombre + '\'' +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", stockDisponible=" + stockDisponible +
                ", tipoAlmacenamiento='" + tipoAlmacenamiento + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
