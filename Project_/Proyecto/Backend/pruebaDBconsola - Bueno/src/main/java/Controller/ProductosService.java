package Controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Productos;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/api/productos/*")
public class ProductosService extends HttpServlet {
    private ProductosController productosController;

    @Override
    public void init() throws ServletException {
        super.init();
        productosController = new ProductosController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo(); // ej: /1 o null
        String categoriaStr = req.getParameter("categoria");

        Gson gson = new Gson();

        try {
            if (categoriaStr != null) {
                // Listar productos por categoría
                int categoriaId = Integer.parseInt(categoriaStr);
                Productos filtro = new Productos();
                filtro.setId_categoria(categoriaId);

                ArrayList<Productos> productos = productosController.getProductos(filtro);
                String json = gson.toJson(productos);
                resp.getWriter().write(json);
                return;
            }

            if (pathInfo == null || pathInfo.equals("/")) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Falta el ID del producto en la URL\"}");
                return;
            }

            int id = Integer.parseInt(pathInfo.substring(1));
            if (id <= 0) throw new NumberFormatException();

            Productos filtro = new Productos();
            filtro.setId_producto(id);

            ArrayList<Productos> productos = productosController.getProductos(filtro);
            if (productos.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Producto no encontrado\"}");
                return;
            }
            String json = gson.toJson(productos.get(0));
            resp.getWriter().write(json);

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"ID o categoría inválida\"}");
        }
    }
}
