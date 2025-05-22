package Controller;

import DAO.DetallesFacturasDao;
import model.DetallesFacturas;

import java.util.ArrayList;

public class DetallesFacturasController {
    private DetallesFacturasDao dao;

    public DetallesFacturasController() {
        dao = new DetallesFacturasDao();
    }

    public int addDetalleFactura(DetallesFacturas df) {
        return dao.add(df);
    }

    public int deleteDetalleFactura(DetallesFacturas df) {
        return dao.delete(df);
    }

    public int updateDetalleFactura(DetallesFacturas df) {
        return dao.update(df);
    }

    public ArrayList<DetallesFacturas> getDetallesFacturas(DetallesFacturas filtro) {
        return dao.FindAll(filtro);
    }
}
