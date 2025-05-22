package Controller;

import DAO.ResenasDao;
import model.Resenas;

import java.util.ArrayList;

public class ResenasController {
    private ResenasDao dao;

    public ResenasController() {
        dao = new ResenasDao();
    }

    public int addResena(Resenas resena) {
        return dao.add(resena);
    }

    public int deleteResena(Resenas resena) {
        return dao.delete(resena);
    }

    public int updateResena(Resenas resena) {
        return dao.update(resena);
    }

    public ArrayList<Resenas> getResenas(Resenas filtro) {
        return dao.FindAll(filtro);
    }
}