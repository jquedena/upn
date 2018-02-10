package pe.edu.upn.encuesta.entidad;

import com.indra.core.domain.Entidad;

public class Modulo extends Entidad {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String descripcion;
    private String url;
    private String idHtml;
    private String iconoHtml;
    private Modulo modulo;
    private Long idModulo;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getIdHtml() {
        return idHtml;
    }

    public void setIdHtml(String idHtml) {
        this.idHtml = idHtml;
    }

    public String getIconoHtml() {
        return iconoHtml;
    }

    public void setIconoHtml(String iconoHtml) {
        this.iconoHtml = iconoHtml;
    }

}
