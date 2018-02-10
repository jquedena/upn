package pe.edu.upn.encuesta.entidad;

// Generated 02/12/2016 05:39:11 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import com.indra.core.domain.Entidad;

/**
 * ConsolidadoBsi generated by hbm2java
 */
public class ConsolidadoBsi extends Entidad {

    private static final long serialVersionUID = 1L;
    private Date periodo;
    private String cuentaContable;
    private BigDecimal saldoIniBc;
    private BigDecimal saldoFinBc;
    private BigDecimal saldoIni;
    private BigDecimal saldoFin;
    private String codigoSector;
    private String opcion;
    private BigDecimal diferencia;
    
    private String pestania;
    private String versionBsi;
    
    private String ColOrden;
    private String Orden;





	public String getColOrden() {
		return ColOrden;
	}

	public void setColOrden(String colOrden) {
		ColOrden = colOrden;
	}

	public String getOrden() {
		return Orden;
	}

	public void setOrden(String orden) {
		Orden = orden;
	}

	public Date getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }

    public String getCuentaContable() {
        return this.cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public BigDecimal getSaldoIniBc() {
        return this.saldoIniBc;
    }

    public void setSaldoIniBc(BigDecimal saldoIniBc) {
        this.saldoIniBc = saldoIniBc;
    }

    public BigDecimal getSaldoFinBc() {
        return this.saldoFinBc;
    }

    public void setSaldoFinBc(BigDecimal saldoFinBc) {
        this.saldoFinBc = saldoFinBc;
    }

    public BigDecimal getSaldoIni() {
        return this.saldoIni;
    }

    public void setSaldoIni(BigDecimal saldoIni) {
        this.saldoIni = saldoIni;
    }

    public BigDecimal getSaldoFin() {
        return this.saldoFin;
    }

    public void setSaldoFin(BigDecimal saldoFin) {
        this.saldoFin = saldoFin;
    }

    public String getCodigoSector() {
        return this.codigoSector;
    }

    public void setCodigoSector(String codigoSector) {
        this.codigoSector = codigoSector;
    }

    public String getOpcion() {
        return this.opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

	public BigDecimal getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}

	public String getPestania() {
		return pestania;
	}

	public void setPestania(String pestania) {
		this.pestania = pestania;
	}

	public String getVersionBsi() {
		return versionBsi;
	}

	public void setVersionBsi(String versionBsi) {
		this.versionBsi = versionBsi;
	}
    

}
