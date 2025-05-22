package Controller;

import DAO.EmpleadosDao;
import model.Empleados;

import java.util.ArrayList;

public class EmpleadosController {
    private EmpleadosDao dao;

    public EmpleadosController() {
        dao = new EmpleadosDao();
    }

    public int addEmpleado(Empleados empleado) {
        return dao.add(empleado);
    }

    public int deleteEmpleado(Empleados empleado) {
        return dao.delete(empleado);
    }

    public int updateEmpleado(Empleados empleado) {
        return dao.update(empleado);
    }

    public ArrayList<Empleados> getEmpleados(Empleados filtro) {
        return dao.FindAll(filtro);
    }
}
