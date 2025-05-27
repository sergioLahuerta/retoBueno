package model;

public class Usuarios {
    private int id_usuario;
    private String nombre;
    private String email;
    private String contrasena;
    private String dni;
    private String telefono;
    private String direccion;

    // Getters y setters
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "ID_Usuario=" + id_usuario +
                ", Nombre='" + nombre + '\'' +
                ", Email='" + email + '\'' +
                ", Contrasena='" + contrasena + '\'' +
                ", DNI='" + dni + '\'' +
                ", Telefono='" + telefono + '\'' +
                ", Direccion='" + direccion + '\'' +
                '}';
    }
}
