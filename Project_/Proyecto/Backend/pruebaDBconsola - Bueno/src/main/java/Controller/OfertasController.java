package Controller;

import DAO.OfertasDao;
import model.Ofertas;

import java.util.ArrayList;

public class OfertasController {
    private OfertasDao ofertasDao;

    public OfertasController() {
        ofertasDao = new OfertasDao();
    }

    public int addOferta(Ofertas oferta) {
        return ofertasDao.add(oferta);
    }

    public int deleteOferta(Ofertas oferta) {
        return ofertasDao.delete(oferta);
    }

    public int updateOferta(Ofertas oferta) {
        return ofertasDao.update(oferta);
    }

    public ArrayList<Ofertas> getOfertas(Ofertas filtro) {
        return ofertasDao.FindAll(filtro);
    }
}
