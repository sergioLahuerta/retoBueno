package Controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Ofertas;
import model.Productos;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/api/ofertas")
public class OfertasServlet extends HttpServlet {

    private OfertasController ofertasController;

    @Override
    public void init() throws ServletException {
        super.init();
        ofertasController = new OfertasController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ofertas filtro = new Ofertas();

        ArrayList<Ofertas> ofertas = ofertasController.getOfertas(filtro);
        System.out.println("Ofertas: " + ofertas.size());
        System.out.println("Productos encontrados: " + ofertas.size());
        for (Ofertas o : ofertas) {
            System.out.println(o);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String json = gson.toJson(ofertas);
        response.getWriter().write(json);
    }
}
