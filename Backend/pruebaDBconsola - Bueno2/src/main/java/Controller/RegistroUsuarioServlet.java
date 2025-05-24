package Controller;

import model.Usuarios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registroUsuario")
public class RegistroUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String contrasena = request.getParameter("password");

        if (email == null || email.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            response.sendRedirect("registro_error.html");
            return;
        }

        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        Usuarios usuario = new Usuarios();
        usuario.setEmail(email);
        usuario.setContrasena(contrasena); // Puedes mejorar luego hasheando

        if (nombre != null && !nombre.isEmpty()) usuario.setNombre(nombre);
        if (dni != null && !dni.isEmpty()) usuario.setDni(dni);
        if (telefono != null && !telefono.isEmpty()) usuario.setTelefono(telefono);
        if (direccion != null && !direccion.isEmpty()) usuario.setDireccion(direccion);

        try {
            UsuariosController controller = new UsuariosController();
            int resultado = controller.addUsuario(usuario);

            if (resultado > 0) {
                response.sendRedirect("registro_exitoso.html");
            } else {
                System.out.println("Error al insertar usuario: resultado = " + resultado);
                response.sendRedirect("registro_error.html");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Aquí verás el error detallado en la consola
            response.sendRedirect("registro_error.html");
        }
    }
}

