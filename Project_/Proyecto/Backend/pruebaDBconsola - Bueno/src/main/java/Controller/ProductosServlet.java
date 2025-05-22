package Controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Productos;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/api/productos")
public class ProductosServlet extends HttpServlet {
    private ProductosController productosController;

    @Override
    public void init() throws ServletException {
        super.init();
        productosController = new ProductosController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            String categoriaParam = req.getParameter("categoria");
            int categoriaId;

            if (categoriaParam == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Parámetro 'categoria' es obligatorio\"}");
                return;
            }

            try {
                categoriaId = Integer.parseInt(categoriaParam);
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Parámetro 'categoria' debe ser un número válido\"}");
                return;
            }

            // Validar que la categoría esté entre 1 y 4, por ejemplo:
            if (categoriaId < 1 || categoriaId > 4) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Categoría inválida. Debe ser un número entre 1 y 4\"}");
                return;
            }

            Productos filtro = new Productos();
            filtro.setId_categoria(categoriaId);

            ArrayList<Productos> productos = productosController.getProductos(filtro);
            System.out.println("Productos encontrados en categoría " + categoriaId + ": " + productos.size());

            Gson gson = new Gson();
            String json = gson.toJson(productos);

            resp.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Error al obtener productos\"}");
        }
    }
}
