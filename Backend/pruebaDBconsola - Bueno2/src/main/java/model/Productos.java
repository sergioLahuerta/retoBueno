package model;

public class Productos {
    private int id_producto;
    private int id_oferta;
    private int id_categoria;
    private String nombre;
    private String descripcion;
    private double precio;

    // Getters y setters
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "ID_Producto=" + id_producto +
                ", ID_Oferta=" + id_oferta +
                ", ID_Categoria=" + id_categoria +
                ", Nombre='" + nombre + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                ", Precio=" + precio +
                '}';
    }
}
