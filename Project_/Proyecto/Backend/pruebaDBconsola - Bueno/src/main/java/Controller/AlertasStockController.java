package Controller;

import DAO.AlertasStockDao;
import model.AlertasStock;

import java.util.ArrayList;

public class AlertasStockController {
    private AlertasStockDao dao;

    public AlertasStockController() {
        dao = new AlertasStockDao();
    }

    public ArrayList<AlertasStock> getAlertasStock(AlertasStock filtro) {
        return dao.FindAll(filtro);
    }
}
