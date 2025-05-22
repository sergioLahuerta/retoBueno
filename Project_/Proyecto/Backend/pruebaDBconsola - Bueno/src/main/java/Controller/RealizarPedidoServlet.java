package Controller;

import DAO.*;
import com.google.gson.Gson;
import model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/realizar-pedido")
public class RealizarPedidoServlet extends HttpServlet {

    private final UsuariosDao usuariosDao = new UsuariosDao();
    private final FacturasDao facturasDao = new FacturasDao();
    private final PedidosDao pedidosDao = new PedidosDao();
    private final DetallesPedidosDao detallesPedidosDao = new DetallesPedidosDao();
    private final DetallesFacturasDao detallesFacturasDao = new DetallesFacturasDao();
    private final PagosDao pagosDao = new PagosDao();
    private final ProductosDao productosDao = new ProductosDao(); // asumimos que existe

    static class ProductoPedido {
        public int id;
        public int cantidad;
        public String observaciones;
    }

    static class PedidoInput {
        public String nombre;
        public String email;
        public int id_restaurante;
        public List<ProductoPedido> productos;
        public String metodoPago;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        // Leer JSON del frontend
        BufferedReader reader = req.getReader();
        PedidoInput pedidoInput = gson.fromJson(reader, PedidoInput.class);

        // Validar usuario existente
        UsuariosDao uDao = new UsuariosDao();
        Usuarios usuarioFiltro = new Usuarios();
        usuarioFiltro.setEmail(pedidoInput.email);
        List<Usuarios> usuarios = uDao.FindAll(usuarioFiltro);

        if (!usuarios.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            out.write("Este correo ya está registrado. Por favor, inicia sesión.");
            return;
        }

        // Crear usuario nuevo
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(pedidoInput.nombre);
        nuevoUsuario.setEmail(pedidoInput.email);
        uDao.add(nuevoUsuario);

        int idUsuario = uDao.FindAll(usuarioFiltro).get(0).getId_usuario();

        // Calcular total
        double totalFactura = 0;
        Map<Integer, Double> preciosUnitarios = new HashMap<>();

        for (ProductoPedido p : pedidoInput.productos) {
            Productos filtro = new Productos();
            filtro.setId_producto(p.id);
            double precio = productosDao.FindAll(filtro).get(0).getPrecio();
            preciosUnitarios.put(p.id, precio);
            totalFactura += precio * p.cantidad;
        }

        // Crear factura
        Facturas factura = new Facturas();
        factura.setFechaFactura(new Date(System.currentTimeMillis()));
        factura.setImporteTotal(totalFactura);
        facturasDao.add(factura);
        int idFactura = facturasDao.FindAll(null).stream().max(Comparator.comparing(Facturas::getId_factura)).get().getId_factura();

        // Crear pedido
        Pedidos pedido = new Pedidos();
        pedido.setId_factura(idFactura);
        pedido.setId_restaurante(pedidoInput.id_restaurante);
        pedido.setId_usuario(idUsuario);
        pedido.setNumero(new Random().nextInt(9000) + 1000);
        pedidosDao.add(pedido);
        int idPedido = pedidosDao.FindAll(null).stream().max(Comparator.comparing(Pedidos::getId_pedido)).get().getId_pedido();

        // Insertar detalles pedido y factura
        for (ProductoPedido p : pedidoInput.productos) {
            // Detalles pedido
            DetallesPedidos detallePedido = new DetallesPedidos();
            detallePedido.setId_pedido(idPedido);
            detallePedido.setId_producto(p.id);
            detallePedido.setCantidad(p.cantidad);
            detallePedido.setObservaciones(p.observaciones);
            detallesPedidosDao.add(detallePedido);
            int idDetallePedido = detallesPedidosDao.FindAll(null).stream().max(Comparator.comparing(DetallesPedidos::getId_detallePedido)).get().getId_detallePedido();

            // Detalles factura
            double precioUnitario = preciosUnitarios.get(p.id);
            DetallesFacturas detalleFactura = new DetallesFacturas();
            detalleFactura.setId_detalle_pedido(idDetallePedido);
            detalleFactura.setId_factura(idFactura);
            detalleFactura.setPrecioUnitario(precioUnitario);
            detalleFactura.setTotalLinea(precioUnitario * p.cantidad);
            detalleFactura.setDescuento(0);
            detallesFacturasDao.add(detalleFactura);
        }

        // Insertar pago
        Pagos pago = new Pagos();
        pago.setId_factura(idFactura);
        pago.setMetodoPago(pedidoInput.metodoPago);
        pago.setFechaPago(new Date(System.currentTimeMillis()));
        pago.setEstadoPago("PENDIENTE");
        pagosDao.add(pago);

        resp.setStatus(HttpServletResponse.SC_OK);
        out.write("Pedido realizado con éxito.");
    }
}
