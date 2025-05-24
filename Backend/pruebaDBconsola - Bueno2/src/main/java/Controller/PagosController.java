package Controller;

import DAO.PagosDao;
import model.Pagos;

import java.util.ArrayList;

public class PagosController {
    private PagosDao dao;

    public PagosController() {
        dao = new PagosDao();
    }

    public int addPago(Pagos pago) {
        return dao.add(pago);
    }

    public int deletePago(Pagos pago) {
        return dao.delete(pago);
    }

    public int updatePago(Pagos pago) {
        return dao.update(pago);
    }

    public ArrayList<Pagos> getPagos(Pagos filtro) {
        return dao.FindAll(filtro);
    }
}
