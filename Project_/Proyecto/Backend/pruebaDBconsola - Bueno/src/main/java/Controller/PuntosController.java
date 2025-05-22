package Controller;

import DAO.PuntosDao;
import model.Puntos;

import java.util.ArrayList;

public class PuntosController {
    private PuntosDao dao;

    public PuntosController() {
        dao = new PuntosDao();
    }

    public int addPuntos(Puntos puntos) {
        return dao.add(puntos);
    }

    public int deletePuntos(Puntos puntos) {
        return dao.delete(puntos);
    }

    public int updatePuntos(Puntos puntos) {
        return dao.update(puntos);
    }

    public ArrayList<Puntos> getPuntos(Puntos filtro) {
        return dao.FindAll(filtro);
    }
}
