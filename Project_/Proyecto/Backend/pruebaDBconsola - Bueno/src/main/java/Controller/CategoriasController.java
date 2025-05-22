package Controller;

import DAO.CategoriasDao;
import model.Categorias;

import java.util.ArrayList;

public class CategoriasController {
    private CategoriasDao categoriaDao;

    public CategoriasController() {
        categoriaDao = new CategoriasDao();
    }

    public int addCategoria(Categorias categoria) {
        return categoriaDao.add(categoria);
    }

    public int deleteCategoria(Categorias categoria) {
        return categoriaDao.delete(categoria);
    }

    public int updateCategoria(Categorias categoria) {
        return categoriaDao.update(categoria);
    }

    public ArrayList<Categorias> getCategorias(Categorias filtro) {
        return categoriaDao.FindAll(filtro);
    }
}
