package pe.edu.upn.encuesta.entidad;

// Generated 02/12/2016 05:39:11 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import com.indra.core.domain.Entidad;

/**
 * Anexo8 generated by hbm2java
 */
public class Anexo8 extends Entidad {

    private static final long serialVersionUID = 1L;
    private BigDecimal jobInstanceId;
    private String producto;
    private String divisa;
    private BigDecimal valorMercado;
    private BigDecimal saldoValorMercadoPosi;
    private BigDecimal saldoValorMercadoNega;
    private String codigoCentral;
    private String tipoDerivado;
    private Date periodo;

    public BigDecimal getJobInstanceId() {
        return this.jobInstanceId;
    }

    public void setJobInstanceId(BigDecimal jobInstanceId) {
        this.jobInstanceId = jobInstanceId;
    }

    public String getProducto() {
        return this.producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDivisa() {
        return this.divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public BigDecimal getValorMercado() {
        return this.valorMercado;
    }

    public void setValorMercado(BigDecimal valorMercado) {
        this.valorMercado = valorMercado;
    }

    public BigDecimal getSaldoValorMercadoPosi() {
        return this.saldoValorMercadoPosi;
    }

    public void setSaldoValorMercadoPosi(BigDecimal saldoValorMercadoPosi) {
        this.saldoValorMercadoPosi = saldoValorMercadoPosi;
    }

    public BigDecimal getSaldoValorMercadoNega() {
        return this.saldoValorMercadoNega;
    }

    public void setSaldoValorMercadoNega(BigDecimal saldoValorMercadoNega) {
        this.saldoValorMercadoNega = saldoValorMercadoNega;
    }

    public String getCodigoCentral() {
        return this.codigoCentral;
    }

    public void setCodigoCentral(String codigoCentral) {
        this.codigoCentral = codigoCentral;
    }

    public Date getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }

	public String getTipoDerivado() {
		return tipoDerivado;
	}

	public void setTipoDerivado(String tipoDerivado) {
		this.tipoDerivado = tipoDerivado;
	}

}