package Controller;

import DAO.AlmacenDao;
import model.Almacen;

import java.util.ArrayList;

public class AlmacenController {
    private AlmacenDao dao;

    public AlmacenController() {
        dao = new AlmacenDao();
    }

    public int addAlmacen(Almacen a) {
        return dao.add(a);
    }

    public int deleteAlmacen(Almacen a) {
        return dao.delete(a);
    }

    public int updateAlmacen(Almacen a) {
        return dao.update(a);
    }

    public ArrayList<Almacen> getAlmacenes(Almacen filtro) {
        return dao.FindAll(filtro);
    }
}
