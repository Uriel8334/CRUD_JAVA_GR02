package edu.ec.monster.controlador;

import edu.ec.monster.modelo.Departamento;
import edu.ec.monster.modelo.DepartamentoDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DepartamentoServlet", urlPatterns = {"/departamentos"})
public class DepartamentoServlet extends HttpServlet {

    private DepartamentoDAO dao;

    @Override
    public void init() throws ServletException {
        // Inicializamos el DAO una sola vez al cargar el Servlet
        dao = new DepartamentoDAO();
    }

    // Usaremos doGet para mostrar vistas (listar, formularios) y para eliminar
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            accion = "listar"; // Por defecto, si no hay acción, mostramos la lista
        }

        switch (accion) {
            case "nuevo":
                // Muestra el formulario vacío para crear uno nuevo
                request.getRequestDispatcher("departamento_form.jsp").forward(request, response);
                break;
                
            case "editar":
                // Obtiene el código, busca el registro original y lo manda al formulario
                String codigoEditar = request.getParameter("codigo");
                Departamento deptoExistente = dao.obtenerPorCodigo(codigoEditar);
                request.setAttribute("departamento", deptoExistente);
                request.getRequestDispatcher("departamento_form.jsp").forward(request, response);
                break;
                
            case "eliminar":
                // Obtiene el código, lo elimina en la BD y recarga la lista
                String codigoEliminar = request.getParameter("codigo");
                dao.eliminarDepartamento(codigoEliminar);
                response.sendRedirect("departamentos?accion=listar");
                break;
                
            case "listar":
            default:
                // Obtiene todos los registros y los envía a la vista principal
                List<Departamento> lista = dao.listarDepartamentos();
                request.setAttribute("listaDepartamentos", lista);
                request.getRequestDispatcher("departamentos.jsp").forward(request, response);
                break;
        }
    }

    // Usaremos doPost estrictamente para procesar el guardado de datos del formulario (Insertar/Actualizar)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if ("insertar".equals(accion)) {
            // Creamos un objeto nuevo solo con los datos permitidos
            Departamento nuevoDepto = new Departamento();
            nuevoDepto.setNombre(request.getParameter("txtNombre"));
            nuevoDepto.setDescripcion(request.getParameter("txtDescripcion"));
            
            // El DAO se encarga de generarle el código y la fecha
            dao.guardarDepartamento(nuevoDepto);
            
        } else if ("actualizar".equals(accion)) {
            // Recibimos el código original y los campos editables
            String codigo = request.getParameter("txtCodigo");
            String nombre = request.getParameter("txtNombre");
            String descripcion = request.getParameter("txtDescripcion");
            
            // El DAO actualiza de forma segura respetando las reglas
            dao.actualizarDepartamento(codigo, nombre, descripcion);
        }
        
        // Después de insertar o actualizar, siempre redirigimos a la tabla principal
        response.sendRedirect("departamentos?accion=listar");
    }
}