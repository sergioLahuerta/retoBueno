package Controller;

import DAO.Productos_IngredientesDao;
import model.Productos_Ingredientes;

import java.util.ArrayList;

public class Productos_IngredientesController {
    private Productos_IngredientesDao dao;

    public Productos_IngredientesController() {
        dao = new Productos_IngredientesDao();
    }

    public int addProductoIngrediente(Productos_Ingredientes pi) {
        return dao.add(pi);
    }

    public int deleteProductoIngrediente(Productos_Ingredientes pi) {
        return dao.delete(pi);
    }

    public int updateProductoIngrediente(Productos_Ingredientes pi) {
        return dao.update(pi);
    }

    public ArrayList<Productos_Ingredientes> getProductosIngredientes(Productos_Ingredientes filtro) {
        return dao.FindAll(filtro);
    }
}
