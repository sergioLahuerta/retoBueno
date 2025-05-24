package Controller;

import DAO.HistorialPuntosDao;
import model.HistorialPuntos;
import java.util.ArrayList;

public class HistorialPuntosController {
    private HistorialPuntosDao dao;

    public HistorialPuntosController() {
        dao = new HistorialPuntosDao();
    }

    public int addHistorial(HistorialPuntos historial) {
        return dao.add(historial);
    }

    public int deleteHistorial(HistorialPuntos historial) {
        return dao.delete(historial);
    }

    public int updateHistorial(HistorialPuntos historial) {
        return dao.update(historial);
    }

    public ArrayList<HistorialPuntos> getHistorial(HistorialPuntos filtro) {
        return dao.FindAll(filtro);
    }
}
