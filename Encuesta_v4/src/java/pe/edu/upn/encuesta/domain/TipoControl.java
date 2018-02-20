/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JQUEDENA
 */
@Entity
@Table(name = "tipo_control")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoControl.findAll", query = "SELECT t FROM TipoControl t")
    , @NamedQuery(name = "TipoControl.findByIdTipoControl", query = "SELECT t FROM TipoControl t WHERE t.idTipoControl = :idTipoControl")
    , @NamedQuery(name = "TipoControl.findByDescripcion", query = "SELECT t FROM TipoControl t WHERE t.descripcion = :descripcion")})
public class TipoControl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_control")
    private Integer idTipoControl;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoControl", fetch = FetchType.EAGER)
    private List<Pregunta> preguntaList;

    public TipoControl() {
    }

    public TipoControl(Integer idTipoControl) {
        this.idTipoControl = idTipoControl;
    }

    public Integer getIdTipoControl() {
        return idTipoControl;
    }

    public void setIdTipoControl(Integer idTipoControl) {
        this.idTipoControl = idTipoControl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idTipoControl != null ? idTipoControl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoControl)) {
            return false;
        }
        TipoControl other = (TipoControl) object;
        if ((this.idTipoControl == null && other.idTipoControl != null) || (this.idTipoControl != null && !this.idTipoControl.equals(other.idTipoControl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.edu.upn.encuesta.TipoControl[ idTipoControl=" + idTipoControl + " ]";
    }
    
}
