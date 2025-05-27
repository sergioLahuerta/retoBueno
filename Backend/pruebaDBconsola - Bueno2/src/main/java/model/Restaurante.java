package model;

public class Restaurante {
    private int id_restaurante;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private int aforo;
    private String imagenRestaurante;

    // Getters y Setters
    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getImagenRestaurante() {
        return imagenRestaurante;
    }

    public void setImagenRestaurante(String imagenRestaurante) {
        this.imagenRestaurante = imagenRestaurante;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "ID_Restaurante=" + id_restaurante +
                ", Nombre='" + nombre + '\'' +
                ", Direccion='" + direccion + '\'' +
                ", Telefono='" + telefono + '\'' +
                ", Email='" + email + '\'' +
                ", Aforo=" + aforo +
                ", imagenRestaurante=" + imagenRestaurante +
                '}';
    }
}
