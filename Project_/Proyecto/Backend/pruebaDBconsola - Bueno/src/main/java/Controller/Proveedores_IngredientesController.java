package Controller;

import DAO.Proveedores_IngredientesDao;
import model.Proveedores_Ingredientes;

import java.util.ArrayList;

public class Proveedores_IngredientesController {
    private Proveedores_IngredientesDao dao;

    public Proveedores_IngredientesController() {
        dao = new Proveedores_IngredientesDao();
    }

    public int addProveedorIngrediente(Proveedores_Ingredientes pi) {
        return dao.add(pi);
    }

    public int deleteProveedorIngrediente(Proveedores_Ingredientes pi) {
        return dao.delete(pi);
    }

    public int updateProveedorIngrediente(Proveedores_Ingredientes pi) {
        return dao.update(pi);
    }

    public ArrayList<Proveedores_Ingredientes> getProveedoresIngredientes(Proveedores_Ingredientes filtro) {
        return dao.FindAll(filtro);
    }
}
