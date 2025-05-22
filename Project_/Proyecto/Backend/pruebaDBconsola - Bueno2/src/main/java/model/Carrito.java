package model;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<DetallesPedidos> items;

    public Carrito() {
        items = new ArrayList<>();
    }

    public void agregarProducto(int idProducto, int cantidad, String observaciones) {
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

    // ✅ Nuevo método para poder hacer .agregar(detalle)
    public void agregar(DetallesPedidos item) {
        agregarProducto(item.getId_producto(), item.getCantidad(), item.getObservaciones());
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
