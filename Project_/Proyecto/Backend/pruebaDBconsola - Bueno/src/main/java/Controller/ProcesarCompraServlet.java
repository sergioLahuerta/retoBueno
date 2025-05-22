package Controller;

import com.google.gson.Gson;
import model.Carrito;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/procesarCompra")
public class ProcesarCompraServlet extends HttpServlet {

    private CompraService compraService = new CompraService();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Leer JSON del body
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // Convertir JSON a objeto Carrito
            Carrito carrito = gson.fromJson(sb.toString(), Carrito.class);

            // Aquí deberías recibir también idUsuario e idRestaurante
            // Podrías pasar por parámetros (query o body) o por sesión
            int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
            int idRestaurante = Integer.parseInt(req.getParameter("idRestaurante"));

            // Procesar la compra
            compraService.procesarCompra(carrito, idUsuario, idRestaurante);

            // Devolver respuesta JSON éxito
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"status\":\"success\"}");

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
