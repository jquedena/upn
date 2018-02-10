package pe.edu.upn.encuesta.entidad;

// Generated 02/12/2016 05:39:11 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import com.indra.core.domain.Entidad;

/**
 * ConsolidadoInputsHost generated by hbm2java
 */
public class ConsolidadoInputsHost extends Entidad {

    private static final long serialVersionUID = 1L;
    private String fuente;
    private String cuentaContable;
    private String descripcion;
    private BigDecimal saldo;
    private String divisa;
    private String codigoCentral;
    private String doi;
    private Date periodo;
    private String cuentaContableArchmodel;

    public String getFuente() {
        return this.fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getCuentaContable() {
        return this.cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getDivisa() {
        return this.divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getCodigoCentral() {
        return this.codigoCentral;
    }

    public void setCodigoCentral(String codigoCentral) {
        this.codigoCentral = codigoCentral;
    }

    public String getDoi() {
        return this.doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Date getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }

	public String getCuentaContableArchmodel() {
		return cuentaContableArchmodel;
	}

	public void setCuentaContableArchmodel(String cuentaContableArchmodel) {
		this.cuentaContableArchmodel = cuentaContableArchmodel;
	}

}
