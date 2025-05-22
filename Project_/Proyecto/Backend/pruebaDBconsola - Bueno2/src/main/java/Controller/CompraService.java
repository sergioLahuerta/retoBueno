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
import java.sql.Date;
import java.util.List;

public class CompraService {
    private PedidosDao pedidosDao = new PedidosDao();
    private DetallesPedidosDao detallesDao = new DetallesPedidosDao();
    private FacturasDao facturasDao = new FacturasDao();
    private ProductosDao productosDao = new ProductosDao();

    public void procesarCompra(Carrito carrito, int idUsuario, int idRestaurante, Connection conn) throws Exception {
        try {
            double total = 0;

            // Paso 1: Calcular precios de productos
            for (DetallesPedidos item : carrito.getItems()) {
                double precio = productosDao.obtenerPrecioProducto(item.getId_producto(), conn);
                item.setPrecioUnidad(precio);
                total += item.getCantidad() * precio;
            }

            // Paso 2: Crear factura
            Facturas factura = new Facturas();
            factura.setFechaFactura(new java.util.Date());
            factura.setImporteTotal(total);
            int idFactura = facturasDao.add(factura, conn);
            System.out.println("Factura creada con ID: " + idFactura + " y total: " + total);

            // Paso 3: Crear pedido
            Pedidos pedido = new Pedidos();
            pedido.setId_factura(idFactura);
            pedido.setId_usuario(idUsuario);
            pedido.setId_restaurante(idRestaurante);
            pedido.setNumero(generarNumeroPedido()); // Si implementas esta lógica
            int idPedido = pedidosDao.add(pedido, conn);
            System.out.println("Pedido creado con ID: " + idPedido);

            // Paso 4: Insertar detalles del pedido
            for (DetallesPedidos item : carrito.getItems()) {
                item.setId_pedido(idPedido);
                detallesDao.add(item, conn);
                System.out.println("Detalle añadido: Producto " + item.getId_producto() + ", Cantidad " + item.getCantidad());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la compra", e);
        }
    }

    private int generarNumeroPedido() {
        return (int) (Math.random() * 1000000); // puedes cambiarlo por una lógica más sólida si quieres
    }
}
