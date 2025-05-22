package Controller;

import DAO.ProveedoresDao;
import model.Proveedores;

import java.util.ArrayList;

public class ProveedoresController {
    private ProveedoresDao dao;

    public ProveedoresController() {
        dao = new ProveedoresDao();
    }

    public int addProveedor(Proveedores p) {
        return dao.add(p);
    }

    public int deleteProveedor(Proveedores p) {
        return dao.delete(p);
    }

    public int updateProveedor(Proveedores p) {
        return dao.update(p);
    }

    public ArrayList<Proveedores> getProveedores(Proveedores filtro) {
        return dao.FindAll(filtro);
    }
}
