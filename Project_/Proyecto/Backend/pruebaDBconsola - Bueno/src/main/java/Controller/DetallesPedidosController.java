package Controller;

import DAO.DetallesPedidosDao;
import model.DetallesPedidos;

import java.util.ArrayList;

public class DetallesPedidosController {
    private DetallesPedidosDao dao;

    public DetallesPedidosController() {
        dao = new DetallesPedidosDao();
    }

    public int addDetallePedido(DetallesPedidos dp) {
        return dao.add(dp);
    }

    public int deleteDetallePedido(DetallesPedidos dp) {
        return dao.delete(dp.getId_detallePedido());
    }

    public int updateDetallePedido(DetallesPedidos dp) {
        return dao.update(dp);
    }

    public ArrayList<DetallesPedidos> getDetallesPedidos(DetallesPedidos filtro) {
        return dao.FindAll(filtro);
    }
}
