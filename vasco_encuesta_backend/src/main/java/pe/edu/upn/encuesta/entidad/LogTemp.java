package pe.edu.upn.encuesta.entidad;

// Generated 02/12/2016 05:39:11 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import com.indra.core.domain.Entidad;

/**
 * LogTemp generated by hbm2java
 */
public class LogTemp extends Entidad {

    private static final long serialVersionUID = 1L;
    private BigDecimal jobInstanceId;
    private String tipoArchivo;
    private Date fechaRegistro;
    private String descripcionError;
    private String tipoError;

    public BigDecimal getJobInstanceId() {
        return this.jobInstanceId;
    }

    public void setJobInstanceId(BigDecimal jobInstanceId) {
        this.jobInstanceId = jobInstanceId;
    }

    public String getTipoArchivo() {
        return this.tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcionError() {
        return this.descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getTipoError() {
        return this.tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

}