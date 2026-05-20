package edu.ec.monster.modelo;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "XEUSU_USUAR")
@IdClass(UsuarioId.class) // Vinculamos la llave compuesta aquí
public class Usuario implements Serializable {

    @Id
    @Column(name = "PEEMP_CODIGO", length = 6, nullable = false)
    private String codigoEmpleado;

    @Id
    @Column(name = "XEUSU_PASWD", length = 16, nullable = false)
    private String password;

    @Column(name = "XEEST_CODIGO", length = 1, nullable = false)
    private String estadoCodigo;

    @Column(name = "XEUSU_FECCRE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "XEUSU_FECMOD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "XEUSU_PIEFIR", length = 100, nullable = false)
    private String pieFirma;

    // Constructor vacío
    public Usuario() {
    }

    // Getters y Setters
    public String getCodigoEmpleado() { return codigoEmpleado; }
    public void setCodigoEmpleado(String codigoEmpleado) { this.codigoEmpleado = codigoEmpleado; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEstadoCodigo() { return estadoCodigo; }
    public void setEstadoCodigo(String estadoCodigo) { this.estadoCodigo = estadoCodigo; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Date getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(Date fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public String getPieFirma() { return pieFirma; }
    public void setPieFirma(String pieFirma) { this.pieFirma = pieFirma; }
}