package pe.edu.upn.encuesta.entidad;

// Generated 02/12/2016 05:39:11 PM by Hibernate Tools 4.3.1

import com.indra.core.domain.Entidad;

/**
 * ParametroInputsHost generated by hbm2java
 */
public class ParametroInputsHost extends Entidad {

    private static final long serialVersionUID = 1L;
    private String tipoParametro;
    private String digito;
    private String producto;
    private String moneda;
    private String tipoDerivado;
    private String cuentaContable;
    private String emisor;
    private String codigoCentral;
    private String doi;
    private String descripcion;
    private String codigoProducto;

    public String getTipoParametro() {
        return this.tipoParametro;
    }

    public void setTipoParametro(String tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public String getDigito() {
        return this.digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getProducto() {
        return this.producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMoneda() {
        return this.moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTipoDerivado() {
        return this.tipoDerivado;
    }

    public void setTipoDerivado(String tipoDerivado) {
        this.tipoDerivado = tipoDerivado;
    }

    public String getCuentaContable() {
        return this.cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getEmisor() {
        return this.emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
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

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

}