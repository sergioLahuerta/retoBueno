package model;
import java.util.ArrayList;
import model.DetallesPedidos;

public class Carrito {
    private ArrayList<DetallesPedidos> items;

    public Carrito() {
        items = new ArrayList<>();
    }

    public void agregarProducto(int idProducto, int cantidad, String observaciones) {
        // Verificar si el producto ya está en el carrito para sumar cantidad (opcional)
        for (DetallesPedidos dp : items) {
            if (dp.getId_producto() == idProducto) {
                dp.setCantidad(dp.getCantidad() + cantidad);
                return;
            }
        }
        DetallesPedidos nuevoDetalle = new DetallesPedidos();
        nuevoDetalle.setId_producto(idProducto);
        nuevoDetalle.setCantidad(cantidad);
        nuevoDetalle.setObservaciones(observaciones);
        items.add(nuevoDetalle);
    }

    public void eliminarProducto(int idProducto) {
        items.removeIf(dp -> dp.getId_producto() == idProducto);
    }

    public ArrayList<DetallesPedidos> getItems() {
        return items;
    }

    public void limpiar() {
        items.clear();
    }
}
