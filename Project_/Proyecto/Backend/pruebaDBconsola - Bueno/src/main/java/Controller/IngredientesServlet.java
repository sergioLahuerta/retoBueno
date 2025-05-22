package Controller;

import DAO.IngredientesDao;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Ingredientes;
import model.Productos_Ingredientes;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/api/ingredientes")
public class IngredientesServlet extends HttpServlet {

    private Productos_IngredientesController productosIngredientesController;
    private IngredientesDao ingredientesDao;

    @Override
    public void init() {
        productosIngredientesController = new Productos_IngredientesController();
        ingredientesDao = new IngredientesDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idProductoStr = request.getParameter("producto");

        if (idProductoStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro 'producto' es requerido.");
            return;
        }

        int idProducto;
        try {
            idProducto = Integer.parseInt(idProductoStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID de producto debe ser un número entero.");
            return;
        }

        try {
            Productos_Ingredientes filtro = new Productos_Ingredientes();
            filtro.setId_producto(idProducto);
            ArrayList<Productos_Ingredientes> relaciones = productosIngredientesController.getProductosIngredientes(filtro);

            System.out.println("Relaciones encontradas para producto " + idProducto + ": " + relaciones.size());

            ArrayList<Ingredientes> ingredientes = new ArrayList<>();
            for (Productos_Ingredientes pi : relaciones) {
                System.out.println("Relacion: id_ingrediente = " + pi.getId_ingrediente());

                Ingredientes filtroIng = new Ingredientes();
                filtroIng.setId_ingrediente(pi.getId_ingrediente());

                ArrayList<Ingredientes> resultado = ingredientesDao.FindAll(filtroIng);

                System.out.println("Ingredientes encontrados: " + resultado.size());

                if (!resultado.isEmpty()) {
                    ingredientes.add(resultado.get(0));
                }
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(ingredientes));
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
            e.printStackTrace();
        }
    }
}
