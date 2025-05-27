package model;

public class Proveedores {
    private int id_proveedor;
    private String nombreEmpresa;
    private String telefono;
    private String email;

    // Getters y Setters
    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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

    @Override
    public String toString() {
        return "Proveedores{" +
                "ID_Proveedor=" + id_proveedor +
                ", NombreEmpresa='" + nombreEmpresa + '\'' +
                ", Telefono='" + telefono + '\'' +
                ", Email='" + email + '\'' +
                '}';
    }
}
