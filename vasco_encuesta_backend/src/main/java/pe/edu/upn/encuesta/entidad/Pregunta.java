package pe.edu.upn.encuesta.entidad;
// Generated 14/02/2018 06:34:20 AM by Hibernate Tools 4.3.1

import com.indra.core.domain.Entidad;
import java.util.HashSet;
import java.util.Set;

/**
 * Pregunta generated by hbm2java
 */
public class Pregunta extends Entidad {

    private Integer idPregunta;
    private Encuesta encuesta;
    private TipoControl tipoControl;
    private String descripcion;
    private byte[] valores;
    private Set preguntaRespuestas = new HashSet(0);

    public Pregunta() {
    }

    public Pregunta(Encuesta encuesta, TipoControl tipoControl) {
        this.encuesta = encuesta;
        this.tipoControl = tipoControl;
    }

    public Pregunta(Encuesta encuesta, TipoControl tipoControl, String descripcion, byte[] valores, Set preguntaRespuestas) {
        this.encuesta = encuesta;
        this.tipoControl = tipoControl;
        this.descripcion = descripcion;
        this.valores = valores;
        this.preguntaRespuestas = preguntaRespuestas;
    }

    public Integer getIdPregunta() {
        return this.idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Encuesta getEncuesta() {
        return this.encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public TipoControl getTipoControl() {
        return this.tipoControl;
    }

    public void setTipoControl(TipoControl tipoControl) {
        this.tipoControl = tipoControl;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getValores() {
        return this.valores;
    }

    public void setValores(byte[] valores) {
        this.valores = valores;
    }

    public Set getPreguntaRespuestas() {
        return this.preguntaRespuestas;
    }

    public void setPreguntaRespuestas(Set preguntaRespuestas) {
        this.preguntaRespuestas = preguntaRespuestas;
    }

}
