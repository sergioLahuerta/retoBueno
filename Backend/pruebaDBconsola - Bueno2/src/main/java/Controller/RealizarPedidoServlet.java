package Controller;

import DAO.MotorSql;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Carrito;
import model.DetallesPedidos;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/realizar-pedido")
public class RealizarPedidoServlet extends HttpServlet {
    private final CompraService compraService = new CompraService();
    private final Gson gson = new Gson();

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }


    // Manejo de petición POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            PedidoRequest data = gson.fromJson(reader, PedidoRequest.class);

            Carrito carrito = new Carrito();
            for (ProductoDTO producto : data.productos) {
                DetallesPedidos detalle = new DetallesPedidos();
                detalle.setId_producto(producto.id);
                detalle.setCantidad(producto.cantidad);
                detalle.setObservaciones(producto.observaciones);
                carrito.agregar(detalle);
            }

            int idUsuario = 1; // Aquí deberías obtenerlo de sesión o JWT si aplicas autenticación
            MotorSql motorSql = new MotorSql();
            motorSql.connect();
            Connection conn = motorSql.m_Connection;
            compraService.procesarCompra(carrito, idUsuario, data.id_restaurante, conn);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Pedido procesado con éxito.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al procesar el pedido: " + e.getMessage());
        }
    }

    // Agrega los headers necesarios para permitir CORS
    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); // O usa * si no necesitas credenciales
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Si tu frontend envía cookies
    }

    // Clases auxiliares para el JSON
    static class PedidoRequest {
        String nombre;
        String email;
        int id_restaurante;
        String metodoPago;
        List<ProductoDTO> productos;
    }

    static class ProductoDTO {
        int id;
        int cantidad;
        String observaciones;
    }
}
