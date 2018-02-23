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
@Table(name = "inquilino")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inquilino.findAll", query = "SELECT i FROM Inquilino i")
    , @NamedQuery(name = "Inquilino.findByIdInquilino", query = "SELECT i FROM Inquilino i WHERE i.idInquilino = :idInquilino")
    , @NamedQuery(name = "Inquilino.findByNombre", query = "SELECT i FROM Inquilino i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Inquilino.findByRuc", query = "SELECT i FROM Inquilino i WHERE i.ruc = :ruc")
    , @NamedQuery(name = "Inquilino.findByDireccion", query = "SELECT i FROM Inquilino i WHERE i.direccion = :direccion")
    , @NamedQuery(name = "Inquilino.findByLogo", query = "SELECT i FROM Inquilino i WHERE i.logo = :logo")
    , @NamedQuery(name = "Inquilino.findByTema", query = "SELECT i FROM Inquilino i WHERE i.tema = :tema")})
public class Inquilino implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inquilino")
    private Integer idInquilino;
    @Size(max = 500)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "ruc")
    private String ruc;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "logo")
    private String logo;
    @Size(max = 45)
    @Column(name = "tema")
    private String tema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInquilino", fetch = FetchType.EAGER)
    private List<Encuesta> encuestaList;

    public Inquilino() {
    }

    public Inquilino(Integer idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Integer getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(Integer idInquilino) {
        this.idInquilino = idInquilino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @XmlTransient
    public List<Encuesta> getEncuestaList() {
        return encuestaList;
    }

    public void setEncuestaList(List<Encuesta> encuestaList) {
        this.encuestaList = encuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInquilino != null ? idInquilino.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inquilino)) {
            return false;
        }
        Inquilino other = (Inquilino) object;
        if ((this.idInquilino == null && other.idInquilino != null) || (this.idInquilino != null && !this.idInquilino.equals(other.idInquilino))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre; // "pe.edu.upn.encuesta.Inquilino[ idInquilino=" + idInquilino + " ]";
    }
    
}
