package model;

public class Categorias {
    private int id_categoria;
    private String nombre;

    public Categorias() {}

    public Categorias(int id_categoria, String nombre) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "Categoria{" +
                "ID_Categoria=" + id_categoria +
                ", Nombre='" + nombre + '\'' +
                '}';
    }
}
