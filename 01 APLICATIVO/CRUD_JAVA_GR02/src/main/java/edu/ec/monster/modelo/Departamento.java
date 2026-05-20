/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ec.monster.modelo;

import java.io.Serializable;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDEP_DEPAR")
public class Departamento implements Serializable {

    @Id
    @Column(name = "PEDEP_CODIGO", length = 3, nullable = false)
    private String codigo;

    @Column(name = "PEDEP_NOMBRE", length = 50, nullable = false)
    private String nombre;

    // Dependiendo de tu DDL, a veces la descripción permite nulos, lo dejamos en true por si acaso
    @Column(name = "PEDEP_DESCRI", length = 100, nullable = true) 
    private String descripcion;

    @Column(name = "PEDEP_FECHA", nullable = false)
    private Date fecha;

    // Constructor vacío obligatorio para Hibernate
    public Departamento() {
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}