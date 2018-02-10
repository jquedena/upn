package pe.edu.upn.encuesta.entidad;

// Generated 02/12/2016 05:39:11 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import com.indra.core.domain.Entidad;

/**
 * ClienteNoSectorizado generated by hbm2java
 */
public class ClienteNoSectorizado extends Entidad {

    private static final long serialVersionUID = 1L;
    private Date periodo;
    private String codigoCentral;
    private String documento;
    private String nombres;
    private String coddoc;
    private String tipoDocumento;
    private String corasu;
    private String descripcionCorasu;
    private String ciiu;
    private String pecnares;
    private BigDecimal saldo;

    public Date getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }

    public String getCodigoCentral() {
        return this.codigoCentral;
    }

    public void setCodigoCentral(String codigoCentral) {
        this.codigoCentral = codigoCentral;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCoddoc() {
        return this.coddoc;
    }

    public void setCoddoc(String coddoc) {
        this.coddoc = coddoc;
    }

    public String getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCorasu() {
        return this.corasu;
    }

    public void setCorasu(String corasu) {
        this.corasu = corasu;
    }

    public String getDescripcionCorasu() {
        return this.descripcionCorasu;
    }

    public void setDescripcionCorasu(String descripcionCorasu) {
        this.descripcionCorasu = descripcionCorasu;
    }

    public String getCiiu() {
        return this.ciiu;
    }

    public void setCiiu(String ciiu) {
        this.ciiu = ciiu;
    }

    public String getPecnares() {
        return this.pecnares;
    }

    public void setPecnares(String pecnares) {
        this.pecnares = pecnares;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
