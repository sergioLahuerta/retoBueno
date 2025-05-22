package Controller;

import DAO.UsuariosDao;
import model.Usuarios;

import java.util.ArrayList;

public class UsuariosController {
    private UsuariosDao dao;

    public UsuariosController() {
        dao = new UsuariosDao();
    }

    public int addUsuario(Usuarios usuario) {
        return dao.add(usuario);
    }

    public int deleteUsuario(Usuarios usuario) {
        return dao.delete(usuario);
    }

    public int updateUsuario(Usuarios usuario) {
        return dao.update(usuario);
    }

    public ArrayList<Usuarios> getUsuarios(Usuarios filtro) {
        return dao.FindAll(filtro);
    }
}
