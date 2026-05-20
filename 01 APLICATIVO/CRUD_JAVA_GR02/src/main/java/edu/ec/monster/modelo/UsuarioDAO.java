package edu.ec.monster.modelo;

import edu.ec.monster.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UsuarioDAO {

    /**
     * Valida las credenciales en la base de datos.
     * Retorna el objeto Usuario si es exitoso, o null si las credenciales son incorrectas.
     */
    public Usuario validarLogin(String codigoEmpleado, String password) {
        Usuario usuarioAutenticado = null;
        
        // Abrimos la sesión con Hibernate
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            // Creamos la consulta HQL buscando coincidencia exacta
            // Recuerda: En HQL usamos los nombres de los atributos de la clase Java, no de la tabla
            String hql = "FROM Usuario u WHERE u.codigoEmpleado = :codigo AND u.password = :pass";
            
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("codigo", codigoEmpleado);
            query.setParameter("pass", password);
            
            // uniqueResult() devuelve el usuario si lo encuentra, o null si no existe
            usuarioAutenticado = query.uniqueResult();
            
        } catch (Exception e) {
            System.err.println("Error al validar login en la base de datos: " + e.getMessage());
        }
        
        return usuarioAutenticado;
    }
}