package Controller;

import DAO.ProductosDao;
import model.Productos;

import java.util.ArrayList;

public class ProductosController {
    private ProductosDao productosDao;

    public ProductosController() {
        productosDao = new ProductosDao();
    }

    public int addProducto(Productos producto) {
        return productosDao.add(producto);
    }

    public int deleteProducto(Productos producto) {
        return productosDao.delete(producto);
    }

    public int updateProducto(Productos producto) {
        return productosDao.update(producto);
    }

    public ArrayList<Productos> getProductos(Productos filtro) {
        return productosDao.FindAll(filtro);
    }
}
