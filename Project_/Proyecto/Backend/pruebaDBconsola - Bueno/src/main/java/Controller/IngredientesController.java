package Controller;

import DAO.IngredientesDao;
import model.Ingredientes;
import java.util.ArrayList;

public class IngredientesController {
    private IngredientesDao ingredientesDao;

    public IngredientesController() {
        ingredientesDao = new IngredientesDao();
    }

    public int addIngrediente(Ingredientes ingrediente) {
        return ingredientesDao.add(ingrediente);
    }

    public int deleteIngrediente(Ingredientes ingrediente) {
        return ingredientesDao.delete(ingrediente);
    }

    public int updateIngrediente(Ingredientes ingrediente) {
        return ingredientesDao.update(ingrediente);
    }

    public ArrayList<Ingredientes> getIngredientes(Ingredientes filtro) {
        return ingredientesDao.FindAll(filtro);
    }
}
