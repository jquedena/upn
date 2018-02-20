/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JQUEDENA
 */
@Entity
@Table(name = "encuesta_respuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncuestaRespuesta.findAll", query = "SELECT e FROM EncuestaRespuesta e")
    , @NamedQuery(name = "EncuestaRespuesta.findByIdEncuestaResultado", query = "SELECT e FROM EncuestaRespuesta e WHERE e.idEncuestaResultado = :idEncuestaResultado")
    , @NamedQuery(name = "EncuestaRespuesta.findByFechaRegistro", query = "SELECT e FROM EncuestaRespuesta e WHERE e.fechaRegistro = :fechaRegistro")})
public class EncuestaRespuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encuesta_resultado")
    private Integer idEncuestaResultado;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncuestaResultado", fetch = FetchType.EAGER)
    private List<PreguntaRespuesta> preguntaRespuestaList;
    @JoinColumn(name = "id_encuesta", referencedColumnName = "id_encuesta")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Encuesta idEncuesta;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona idPersona;

    public EncuestaRespuesta() {
    }

    public EncuestaRespuesta(Integer idEncuestaResultado) {
        this.idEncuestaResultado = idEncuestaResultado;
    }

    public Integer getIdEncuestaResultado() {
        return idEncuestaResultado;
    }

    public void setIdEncuestaResultado(Integer idEncuestaResultado) {
        this.idEncuestaResultado = idEncuestaResultado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @XmlTransient
    public List<PreguntaRespuesta> getPreguntaRespuestaList() {
        return preguntaRespuestaList;
    }

    public void setPreguntaRespuestaList(List<PreguntaRespuesta> preguntaRespuestaList) {
        this.preguntaRespuestaList = preguntaRespuestaList;
    }

    public Encuesta getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Encuesta idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuestaResultado != null ? idEncuestaResultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaRespuesta)) {
            return false;
        }
        EncuestaRespuesta other = (EncuestaRespuesta) object;
        if ((this.idEncuestaResultado == null && other.idEncuestaResultado != null) || (this.idEncuestaResultado != null && !this.idEncuestaResultado.equals(other.idEncuestaResultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.edu.upn.encuesta.EncuestaRespuesta[ idEncuestaResultado=" + idEncuestaResultado + " ]";
    }
    
}
