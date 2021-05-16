/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "ESTUDIOBD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiobd.findAll", query = "SELECT e FROM Estudiobd e")
    , @NamedQuery(name = "Estudiobd.findById", query = "SELECT e FROM Estudiobd e WHERE e.id = :id")
    , @NamedQuery(name = "Estudiobd.findByNombre", query = "SELECT e FROM Estudiobd e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estudiobd.findByEdadfiltro", query = "SELECT e FROM Estudiobd e WHERE e.edadfiltro = :edadfiltro")
    , @NamedQuery(name = "Estudiobd.findBySexofiltro", query = "SELECT e FROM Estudiobd e WHERE e.sexofiltro = :sexofiltro")
    , @NamedQuery(name = "Estudiobd.findByCiudadusuariofiltro", query = "SELECT e FROM Estudiobd e WHERE e.ciudadusuariofiltro = :ciudadusuariofiltro")
    , @NamedQuery(name = "Estudiobd.findByAnyofiltro", query = "SELECT e FROM Estudiobd e WHERE e.anyofiltro = :anyofiltro")
    , @NamedQuery(name = "Estudiobd.findByLocalizacionfiltro", query = "SELECT e FROM Estudiobd e WHERE e.localizacionfiltro = :localizacionfiltro")
    , @NamedQuery(name = "Estudiobd.findByAforofiltro", query = "SELECT e FROM Estudiobd e WHERE e.aforofiltro = :aforofiltro")})
public class Estudiobd implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "EDADFILTRO")
    private int edadfiltro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SEXOFILTRO")
    private String sexofiltro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CIUDADUSUARIOFILTRO")
    private String ciudadusuariofiltro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANYOFILTRO")
    @Temporal(TemporalType.DATE)
    private Date anyofiltro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "LOCALIZACIONFILTRO")
    private String localizacionfiltro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AFOROFILTRO")
    private int aforofiltro;
    @Column(name = "EDADMENORQUEFILTRO")
    private Integer edadmenorquefiltro;
    @JoinTable(name = "PARTICIPACIONESTUDIOEVENTO", joinColumns = {@JoinColumn(name = "IDESTUDIO", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "IDEVENTO", referencedColumnName = "ID")})
    @ManyToMany
    private List<Evento> eventoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinTable(name = "PARTICIPACIONESTUDIO", joinColumns = {
        @JoinColumn(name = "IDESTUDIO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "IDUSUARIO", referencedColumnName = "ID")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinColumn(name = "IDAUTOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idautor;

    public Estudiobd() {
    }

    public Estudiobd(Integer id) {
        this.id = id;
    }

    public Estudiobd(Integer id, String nombre, int edadfiltro, String sexofiltro, String ciudadusuariofiltro, Date anyofiltro, String localizacionfiltro, int aforofiltro) {
        this.id = id;
        this.nombre = nombre;
        this.edadfiltro = edadfiltro;
        this.sexofiltro = sexofiltro;
        this.ciudadusuariofiltro = ciudadusuariofiltro;
        this.anyofiltro = anyofiltro;
        this.localizacionfiltro = localizacionfiltro;
        this.aforofiltro = aforofiltro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getIdautor() {
        return idautor;
    }

    public void setIdautor(Usuario idautor) {
        this.idautor = idautor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiobd)) {
            return false;
        }
        Estudiobd other = (Estudiobd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.Estudiobd[ id=" + id + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdadfiltro() {
        return edadfiltro;
    }

    public void setEdadfiltro(int edadfiltro) {
        this.edadfiltro = edadfiltro;
    }

    public String getSexofiltro() {
        return sexofiltro;
    }

    public void setSexofiltro(String sexofiltro) {
        this.sexofiltro = sexofiltro;
    }

    public String getCiudadusuariofiltro() {
        return ciudadusuariofiltro;
    }

    public void setCiudadusuariofiltro(String ciudadusuariofiltro) {
        this.ciudadusuariofiltro = ciudadusuariofiltro;
    }

    public Date getAnyofiltro() {
        return anyofiltro;
    }

    public void setAnyofiltro(Date anyofiltro) {
        this.anyofiltro = anyofiltro;
    }

    public String getLocalizacionfiltro() {
        return localizacionfiltro;
    }

    public void setLocalizacionfiltro(String localizacionfiltro) {
        this.localizacionfiltro = localizacionfiltro;
    }

    public int getAforofiltro() {
        return aforofiltro;
    }

    public void setAforofiltro(int aforofiltro) {
        this.aforofiltro = aforofiltro;
    }

    public Integer getEdadmenorquefiltro() {
        return edadmenorquefiltro;
    }

    public void setEdadmenorquefiltro(Integer edadmenorquefiltro) {
        this.edadmenorquefiltro = edadmenorquefiltro;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }
    
}
