package model;

import java.util.ArrayList;

public class Carrito {
    private static ArrayList<DetallesPedidos> items;

    public Carrito() {
        items = new ArrayList<>();
    }

    public static void agregarProducto(int id_producto, int cantidad, String observaciones) {
        for (DetallesPedidos dp : items) {
            if (dp.getId_producto() == id_producto) {
                dp.setCantidad(dp.getCantidad() + cantidad);
                return;
            }
        }
        DetallesPedidos nuevoDetalle = new DetallesPedidos();
        nuevoDetalle.setId_producto(id_producto);
        nuevoDetalle.setCantidad(cantidad);
        nuevoDetalle.setObservaciones(observaciones);
        items.add(nuevoDetalle);
    }

    public static void agregar(DetallesPedidos item) {
        agregarProducto(item.getId_producto(), item.getCantidad(), item.getObservaciones());
    }

    public void eliminarProducto(int id_producto) {
        items.removeIf(dp -> dp.getId_producto() == id_producto);
    }

    public ArrayList<DetallesPedidos> getItems() {
        return items;
    }

    public void limpiar() {
        items.clear();
    }
}
