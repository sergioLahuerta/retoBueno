package Controller;

import DAO.PedidosDao;
import DAO.DetallesPedidosDao;
import DAO.FacturasDao;
import DAO.ProductosDao;
import model.Carrito;
import model.Pedidos;
import model.DetallesPedidos;
import model.Facturas;

import java.sql.Connection;

public class CompraService {
    public static void procesarCompra(Carrito carrito, int id_usuario, int idRestaurante, Connection conn) {
        try {
            conn.setAutoCommit(false);

            double total = 0;

            for (DetallesPedidos item : carrito.getItems()) {
                double precio = ProductosDao.obtenerPrecioProducto(item.getId_producto(), conn);
                if (precio < 0) {
                    throw new RuntimeException("Precio inválido para producto ID: " + item.getId_producto());
                }
                total += item.getCantidad() * precio;
                System.out.println("Precio producto " + item.getId_producto() + ": " + precio);
            }

            Facturas factura = new Facturas();
            factura.setFechaFactura(new java.util.Date());
            factura.setImporteTotal(total);
            System.out.println("[DEBUG] Intentando crear factura...");
            int id_factura = FacturasDao.add(factura, conn);
            System.out.println("[DEBUG] Resultado ID factura: " + id_factura);
            if (id_factura < 0) {
                throw new RuntimeException("No se pudo crear factura");
            }

            Pedidos pedido = new Pedidos();
            pedido.setId_factura(id_factura);
            pedido.setId_usuario(id_usuario);
            pedido.setId_restaurante(idRestaurante);
            pedido.setNumero(generarNumeroPedido());
            int idPedido = PedidosDao.add(pedido, conn);
            if (idPedido < 0) {
                throw new RuntimeException("No se pudo crear pedido");
            }
            System.out.println("Pedido creado con ID: " + idPedido);

            for (DetallesPedidos item : carrito.getItems()) {
                item.setId_pedido(idPedido);
                int res = DetallesPedidosDao.add(item, conn);
                if (res <= 0) {
                    throw new RuntimeException("Error al insertar detalle para producto " + item.getId_producto());
                }
                System.out.println("Detalle añadido: Producto " + item.getId_producto() + ", Cantidad " + item.getCantidad());
            }

            conn.commit();

        } catch (java.sql.SQLException sqlEx) {
            try {
                conn.rollback();
                System.err.println("Rollback ejecutado tras error SQL");
            } catch (Exception rollbackEx) {
                System.err.println("Error haciendo rollback: " + rollbackEx.getMessage());
            }
            System.err.println("Error SQL en procesarCompra: " + sqlEx.getMessage());
            sqlEx.printStackTrace();
            throw new RuntimeException("Error SQL al procesar la compra: " + sqlEx.getMessage(), sqlEx);

        } catch (RuntimeException rte) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.err.println("Rollback ejecutado tras error Runtime");
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error haciendo rollback: " + rollbackEx.getMessage());
            }
            System.err.println("Error Runtime en procesarCompra: " + rte.getMessage());
            rte.printStackTrace();
            throw rte;

        } catch (Exception ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.err.println("Rollback ejecutado tras error inesperado");
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error haciendo rollback: " + rollbackEx.getMessage());
            }
            System.err.println("Error inesperado en procesarCompra: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error inesperado al procesar la compra: " + ex.getMessage(), ex);

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (Exception ex) {
                System.err.println("Error restaurando autocommit: " + ex.getMessage());
            }
        }
    }


    private static int generarNumeroPedido() {
        return (int) (Math.random() * 1000000);
    }
}
