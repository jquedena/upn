/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JQUEDENA
 */
@Entity
@Table(name = "pregunta_respuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreguntaRespuesta.findAll", query = "SELECT p FROM PreguntaRespuesta p")
    , @NamedQuery(name = "PreguntaRespuesta.findByIdPreguntaRespuesta", query = "SELECT p FROM PreguntaRespuesta p WHERE p.idPreguntaRespuesta = :idPreguntaRespuesta")
    , @NamedQuery(name = "PreguntaRespuesta.findByRespuesta", query = "SELECT p FROM PreguntaRespuesta p WHERE p.respuesta = :respuesta")})
public class PreguntaRespuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta_respuesta")
    private Integer idPreguntaRespuesta;
    @Size(max = 50)
    @Column(name = "respuesta")
    private String respuesta;
    @JoinColumn(name = "id_encuesta_resultado", referencedColumnName = "id_encuesta_resultado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EncuestaRespuesta idEncuestaResultado;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pregunta idPregunta;

    public PreguntaRespuesta() {
    }

    public PreguntaRespuesta(Integer idPreguntaRespuesta) {
        this.idPreguntaRespuesta = idPreguntaRespuesta;
    }

    public Integer getIdPreguntaRespuesta() {
        return idPreguntaRespuesta;
    }

    public void setIdPreguntaRespuesta(Integer idPreguntaRespuesta) {
        this.idPreguntaRespuesta = idPreguntaRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public EncuestaRespuesta getIdEncuestaResultado() {
        return idEncuestaResultado;
    }

    public void setIdEncuestaResultado(EncuestaRespuesta idEncuestaResultado) {
        this.idEncuestaResultado = idEncuestaResultado;
    }

    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPreguntaRespuesta != null ? idPreguntaRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaRespuesta)) {
            return false;
        }
        PreguntaRespuesta other = (PreguntaRespuesta) object;
        if ((this.idPreguntaRespuesta == null && other.idPreguntaRespuesta != null) || (this.idPreguntaRespuesta != null && !this.idPreguntaRespuesta.equals(other.idPreguntaRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.edu.upn.encuesta.PreguntaRespuesta[ idPreguntaRespuesta=" + idPreguntaRespuesta + " ]";
    }
    
}
