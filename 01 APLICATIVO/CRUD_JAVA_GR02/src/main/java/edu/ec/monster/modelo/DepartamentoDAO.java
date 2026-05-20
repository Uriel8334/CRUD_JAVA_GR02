/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ec.monster.modelo;

import edu.ec.monster.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.ArrayList;

public class DepartamentoDAO {

    // ==========================================
    // R - READ (Leer todos)
    // ==========================================
    public List<Departamento> listarDepartamentos() {
        List<Departamento> lista = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            lista = session.createQuery("from Departamento", Departamento.class).list();
        } catch (Exception e) {
            System.err.println("Error al listar departamentos: " + e.getMessage());
        }
        return lista;
    }

    // ==========================================
    // C - CREATE (Guardar nuevo con reglas de negocio)
    // ==========================================
    public boolean guardarDepartamento(Departamento depto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            // 1. Generar e inyectar el código autoincremental
            depto.setCodigo(generarNuevoCodigo());
            
            // 2. Inyectar la fecha actual del sistema
            depto.setFecha(new java.sql.Date(System.currentTimeMillis()));
            
            // Guardar en la BD
            session.persist(depto); 
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al guardar departamento: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // Método auxiliar para autoincrementar el CHAR(3)
    // ==========================================
    private String generarNuevoCodigo() {
        String nuevoCodigo = "D01"; // Valor por defecto si la tabla está vacía
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Buscamos el código más alto (alfabéticamente)
            String maxCodigo = session.createQuery("select max(d.codigo) from Departamento d", String.class).uniqueResult();
            
            if (maxCodigo != null) {
                // Extraemos los números (ej. "D05" -> "05"), le sumamos 1, y formateamos a 2 dígitos
                String numeroStr = maxCodigo.replaceAll("[^0-9]", ""); 
                int numero = Integer.parseInt(numeroStr) + 1;
                nuevoCodigo = String.format("D%02d", numero); 
            }
        } catch (Exception e) {
            System.err.println("Error al generar código: " + e.getMessage());
        }
        return nuevoCodigo;
    }

    // ==========================================
    // R - READ (Leer un solo departamento)
    // ==========================================
    public Departamento obtenerPorCodigo(String codigo) {
        Departamento depto = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            depto = session.get(Departamento.class, codigo);
        } catch (Exception e) {
            System.err.println("Error al obtener departamento: " + e.getMessage());
        }
        return depto;
    }

    // ==========================================
    // U - UPDATE (Actualización segura - Solo nombre y descripción)
    // ==========================================
    public boolean actualizarDepartamento(String codigo, String nuevoNombre, String nuevaDescripcion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            // 1. Traemos el original de la BD (con su fecha y código intactos)
            Departamento deptoExistente = session.get(Departamento.class, codigo);
            
            if (deptoExistente != null) {
                // 2. Modificamos ÚNICAMENTE lo permitido
                deptoExistente.setNombre(nuevoNombre);
                deptoExistente.setDescripcion(nuevaDescripcion);
                
                // Hibernate detecta los cambios automáticamente y actualiza al hacer commit
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al actualizar departamento: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // D - DELETE (Eliminar por código)
    // ==========================================
    public boolean eliminarDepartamento(String codigo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Departamento depto = session.get(Departamento.class, codigo);
            if (depto != null) {
                session.remove(depto); 
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al eliminar departamento: " + e.getMessage());
            return false;
        }
    }
}