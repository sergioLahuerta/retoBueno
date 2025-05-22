package Controller;

import DAO.PedidosDao;
import model.Pedidos;

import java.util.ArrayList;

public class PedidosController {
    private PedidosDao dao;

    public PedidosController() {
        dao = new PedidosDao();
    }

    public int addPedido(Pedidos pedido) {
        return dao.add(pedido);
    }

    public int deletePedido(Pedidos pedido) {
        return dao.delete(pedido);
    }

    public int updatePedido(Pedidos pedido) {
        return dao.update(pedido);
    }

    public ArrayList<Pedidos> getPedidos(Pedidos filtro) {
        return dao.FindAll(filtro);
    }
}
