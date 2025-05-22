package Controller;

import com.google.gson.Gson;
import model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "RestauranteServlet", urlPatterns = {"/api/restaurantes"})
public class RestauranteServlet extends HttpServlet {

    private RestauranteController controller;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        controller = new RestauranteController();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String nombre = req.getParameter("nombre");
        String direccion = req.getParameter("direccion");

        Restaurante filtro = new Restaurante();
        if (nombre != null && !nombre.trim().isEmpty()) filtro.setNombre(nombre);
        if (direccion != null && !direccion.trim().isEmpty()) filtro.setDireccion(direccion);

        try {
            ArrayList<Restaurante> restaurantes = controller.getRestaurantes(filtro);
            System.out.println("Restaurantes encontrados: " + restaurantes.size());
            for (Restaurante r : restaurantes) {
                System.out.println(r);
            }

            String json = gson.toJson(restaurantes);

            PrintWriter out = resp.getWriter();
            out.print(json);
            out.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Error al obtener restaurantes\"}");
            e.printStackTrace();
        }
    }
}