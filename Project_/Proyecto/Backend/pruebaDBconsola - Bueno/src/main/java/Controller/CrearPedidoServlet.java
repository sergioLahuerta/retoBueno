package Controller;

import DAO.MotorSql;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.DetallesPedidos;
import model.Pedidos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;

@WebServlet("/crearPedido")
public class CrearPedidoServlet extends HttpServlet {

    private PedidosController pedidosController = new PedidosController();
    private DetallesPedidosController detallesController = new DetallesPedidosController();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            JsonObject pedidoJson = jsonObject.getAsJsonObject("pedido");
            JsonArray detallesJson = jsonObject.getAsJsonArray("detalles");

            // 1. Crear la factura (ejemplo: suma fija o calcula el importe real)
            double importeTotal = 100.00; // Ajusta esto según lógica real
            int idFactura = insertarFactura(importeTotal);

            if (idFactura <= 0) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"error\":\"No se pudo crear la factura.\"}");
                return;
            }

            // 2. Crear el pedido con la factura recién generada
            Pedidos pedido = new Pedidos();
            pedido.setId_factura(idFactura);
            pedido.setId_restaurante(pedidoJson.get("id_restaurante").getAsInt());
            pedido.setId_usuario(pedidoJson.get("id_usuario").getAsInt());
            pedido.setNumero(pedidoJson.get("numero").getAsInt());

            int idPedidoGenerado = insertarPedidoRetornandoId(pedido);

            if (idPedidoGenerado <= 0) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"error\":\"No se pudo crear el pedido.\"}");
                return;
            }

            // 3. Insertar los detalles
            for (JsonElement elem : detallesJson) {
                JsonObject detalleJson = elem.getAsJsonObject();

                DetallesPedidos detalle = new DetallesPedidos();
                detalle.setId_pedido(idPedidoGenerado);
                detalle.setId_producto(detalleJson.get("id_producto").getAsInt());
                detalle.setCantidad(detalleJson.get("cantidad").getAsInt());
                detalle.setObservaciones(
                        detalleJson.has("observaciones") ? detalleJson.get("observaciones").getAsString() : ""
                );

                detallesController.addDetallePedido(detalle);
            }

            out.print("{\"success\":true, \"idPedido\": " + idPedidoGenerado + "}");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private int insertarFactura(double importeTotal) throws SQLException {
        String sql = "INSERT INTO facturas (FechaFactura, ImporteTotal) VALUES (?, ?)";
        try (Connection con = MotorSql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setDouble(2, importeTotal);

            int filas = ps.executeUpdate();
            if (filas == 0) return -1;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    private int insertarPedidoRetornandoId(Pedidos pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (id_factura, id_restaurante, id_usuario, numero) VALUES (?, ?, ?, ?)";
        try (Connection con = MotorSql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pedido.getId_factura());
            ps.setInt(2, pedido.getId_restaurante());
            ps.setInt(3, pedido.getId_usuario());
            ps.setInt(4, pedido.getNumero());

            int filas = ps.executeUpdate();
            if (filas == 0) return -1;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }
}
