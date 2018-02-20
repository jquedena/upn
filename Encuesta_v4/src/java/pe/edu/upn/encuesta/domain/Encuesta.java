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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JQUEDENA
 */
@Entity
@Table(name = "encuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e")
    , @NamedQuery(name = "Encuesta.findByIdEncuesta", query = "SELECT e FROM Encuesta e WHERE e.idEncuesta = :idEncuesta")
    , @NamedQuery(name = "Encuesta.findByNombre", query = "SELECT e FROM Encuesta e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Encuesta.findByFechaInicio", query = "SELECT e FROM Encuesta e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Encuesta.findByFechaFin", query = "SELECT e FROM Encuesta e WHERE e.fechaFin = :fechaFin")
    , @NamedQuery(name = "Encuesta.findByDuracion", query = "SELECT e FROM Encuesta e WHERE e.duracion = :duracion")
    , @NamedQuery(name = "Encuesta.findByClave", query = "SELECT e FROM Encuesta e WHERE e.clave = :clave")})
public class Encuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encuesta")
    private Integer idEncuesta;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "duracion")
    private Integer duracion;
    @Size(max = 45)
    @Column(name = "clave")
    private String clave;
    @JoinColumn(name = "id_inquilino", referencedColumnName = "id_inquilino")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Inquilino idInquilino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncuesta", fetch = FetchType.EAGER)
    private List<EncuestaRespuesta> encuestaRespuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncuesta", fetch = FetchType.EAGER)
    private List<Pregunta> preguntaList;

    public Encuesta() {
    }

    public Encuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Inquilino getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(Inquilino idInquilino) {
        this.idInquilino = idInquilino;
    }

    @XmlTransient
    public List<EncuestaRespuesta> getEncuestaRespuestaList() {
        return encuestaRespuestaList;
    }

    public void setEncuestaRespuestaList(List<EncuestaRespuesta> encuestaRespuestaList) {
        this.encuestaRespuestaList = encuestaRespuestaList;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuesta != null ? idEncuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.idEncuesta == null && other.idEncuesta != null) || (this.idEncuesta != null && !this.idEncuesta.equals(other.idEncuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.edu.upn.encuesta.Encuesta[ idEncuesta=" + idEncuesta + " ]";
    }
    
}
