package Controller;

import DAO.FacturasDao;
import model.Facturas;

import java.util.ArrayList;

public class FacturasController {
    private FacturasDao dao;

    public FacturasController() {
        dao = new FacturasDao();
    }

    public int addFactura(Facturas factura) {
        return dao.add(factura);
    }

    public int deleteFactura(Facturas factura) {
        return dao.delete(factura);
    }

    public int updateFactura(Facturas factura) {
        return dao.update(factura);
    }

    public ArrayList<Facturas> getFacturas(Facturas filtro) {
        return dao.FindAll(filtro);
    }
}
