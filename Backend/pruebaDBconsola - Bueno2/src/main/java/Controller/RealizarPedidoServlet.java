package Controller;

import DAO.MotorSql;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Carrito;
import model.DetallesPedidos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet("/realizar-pedido")
public class RealizarPedidoServlet extends HttpServlet {
    private final CompraService compraService = new CompraService();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MotorSql motorSql = new MotorSql();
        try (BufferedReader reader = request.getReader()) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String json = jsonBuilder.toString();

            System.out.println("JSON recibido: " + json); // <-- LOG

            PedidoRequest data = gson.fromJson(json, PedidoRequest.class);

            if (data == null) {
                throw new RuntimeException("El JSON recibido es inválido o está vacío.");
            }

            // Validación de campos obligatorios
            if (data.nombre == null || data.nombre.trim().isEmpty() ||
                    data.email == null || data.email.trim().isEmpty() ||
                    data.metodoPago == null || data.metodoPago.trim().isEmpty() ||
                    data.productos == null || data.productos.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Datos del pedido incompletos.\"}");
                return;
            }

            Carrito carrito = new Carrito();
            for (ProductoDTO producto : data.productos) {
                if (producto.cantidad <= 0) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\": \"Producto con cantidad inválida.\"}");
                    return;
                }
                DetallesPedidos detalle = new DetallesPedidos();
                detalle.setId_producto(1); // ← ID forzado para pruebas
                detalle.setCantidad(producto.cantidad);
                detalle.setObservaciones(producto.observaciones != null ? producto.observaciones.trim() : "");
                Carrito.agregar(detalle);
            }

            // Simulación de ID usuario
            int id_usuario = 1;

            motorSql.connect();
            Connection conn = motorSql.m_Connection;
            if (conn == null) {
                throw new RuntimeException("No se pudo obtener la conexión a la base de datos.");
            }

            CompraService.procesarCompra(carrito, id_usuario, data.id_restaurante, conn);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"mensaje\": \"Pedido procesado con éxito.\"}");

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Throwable rootCause = e;
            while (rootCause.getCause() != null) {
                rootCause = rootCause.getCause();
            }

            StringWriter sw = new StringWriter();
            rootCause.printStackTrace(new PrintWriter(sw));
            String stackTrace = sw.toString().replace("\"", "\\\"").replace("\n", "\\n");

            String errorMsg = rootCause.getMessage() != null ?
                    rootCause.getMessage().replace("\"", "\\\"").replace("\n", "\\n") :
                    "Error desconocido";

            response.getWriter().write("{\"error\": Connection is FUN \"Error al procesar el pedido: " + errorMsg + "\", \"trace\": \"" + stackTrace + "\"}");
        }

        finally {
            if (motorSql.m_Connection != null) {
                try {
                    motorSql.m_Connection.close();
                } catch (Exception ex) {
                    System.err.println("Error cerrando conexión: " + ex.getMessage());
                }
            }
        }
    }

    // DTOs para mapear el JSON recibido
    static class PedidoRequest {
        String nombre;
        String email;
        int id_restaurante;
        String metodoPago;
        List<ProductoDTO> productos;
    }

    static class ProductoDTO {
        int id_producto;
        int cantidad;
        String observaciones;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().write("Este endpoint solo acepta solicitudes POST para crear pedidos.");
    }
}

