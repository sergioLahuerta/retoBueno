package Controller;

import DAO.PedidosDao;
import DAO.DetallesPedidosDao;
import DAO.FacturasDao;
import model.Carrito;
import model.ItemCarrito;
import model.Pedidos;
import model.DetallesPedidos;
import model.Facturas; // o model.Factura, según como se llame en tu código

public class CompraService {
    private PedidosDao pedidosDao = new PedidosDao();
    private DetallesPedidosDao detallesDao = new DetallesPedidosDao();
    private FacturasDao facturasDao = new FacturasDao();

    public void procesarCompra(Carrito carrito, int idUsuario, int idRestaurante) throws Exception {
        // 1. Crear Factura (obtener ID generado)
        Facturas factura = new Facturas(); // usa nombre correcto aquí
        // llenar datos básicos, fecha, importe total (suma del carrito)
        int idFactura = facturasDao.add(factura);

        // 2. Crear Pedido (con idFactura)
        Pedidos pedido = new Pedidos();
        pedido.setId_factura(idFactura);
        pedido.setId_usuario(idUsuario);
        pedido.setId_restaurante(idRestaurante);
        int idPedido = pedidosDao.add(pedido);

        // 3. Crear DetallesPedidos para cada ítem del carrito
        for (DetallesPedidos item : carrito.getItems()) {
            item.setId_pedido(idPedido); // Seteamos el idPedido en cada detalle existente
            detallesDao.add(item);       // Ya son DetallesPedidos directamente
        }


        // Opcional: Vaciar carrito, etc.
    }
}
