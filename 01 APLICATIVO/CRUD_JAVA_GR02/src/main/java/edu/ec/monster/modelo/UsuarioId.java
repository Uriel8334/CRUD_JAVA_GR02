/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ec.monster.modelo;
import java.io.Serializable;
import java.util.Objects;

// Debe implementar Serializable para que Hibernate pueda manejarla en memoria
public class UsuarioId implements Serializable {

    private String codigoEmpleado;
    private String password;

    // Constructor vacío obligatorio
    public UsuarioId() {
    }

    public UsuarioId(String codigoEmpleado, String password) {
        this.codigoEmpleado = codigoEmpleado;
        this.password = password;
    }

    // Getters y Setters
    public String getCodigoEmpleado() { return codigoEmpleado; }
    public void setCodigoEmpleado(String codigoEmpleado) { this.codigoEmpleado = codigoEmpleado; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // Métodos obligatorios para comparar las llaves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioId usuarioId = (UsuarioId) o;
        return Objects.equals(codigoEmpleado, usuarioId.codigoEmpleado) && 
               Objects.equals(password, usuarioId.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoEmpleado, password);
    }
}