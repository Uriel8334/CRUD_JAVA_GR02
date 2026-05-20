package edu.ec.monster.controlador;

import edu.ec.monster.modelo.Usuario;
import edu.ec.monster.modelo.UsuarioDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UsuarioDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("txtCodigo");
        String password = request.getParameter("txtPassword");

        Usuario usuarioValidado = dao.validarLogin(codigo, password);

        if (usuarioValidado != null) {
            // ÉXITO: Creamos sesión y vamos a la pantalla de bienvenida
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuarioValidado);
            response.sendRedirect("bienvenida.jsp");
        } else {
            // FALLO: Regresamos al index (que ahora es nuestro login) con un error
            request.setAttribute("error", "Credenciales incorrectas. Intente de nuevo.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}