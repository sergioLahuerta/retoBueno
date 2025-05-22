package Controller;

import DAO.RestauranteDao;
import model.Restaurante;

import java.util.ArrayList;

public class RestauranteController {
    private RestauranteDao dao;

    public RestauranteController() {
        dao = new RestauranteDao();
    }

    public int addRestaurante(Restaurante restaurante) {
        return dao.add(restaurante);
    }

    public int deleteRestaurante(Restaurante restaurante) {
        return dao.delete(restaurante);
    }

    public int updateRestaurante(Restaurante restaurante) {
        return dao.update(restaurante);
    }

    public ArrayList<Restaurante> getRestaurantes(Restaurante filtro) {
        return dao.FindAll(filtro);
    }
}
