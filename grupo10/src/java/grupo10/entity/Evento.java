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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Fran
 */
@Entity
@Table(name = "EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findById", query = "SELECT e FROM Evento e WHERE e.id = :id")
    , @NamedQuery(name = "Evento.findByNombre", query = "SELECT e FROM Evento e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Evento.findByDescripcion", query = "SELECT e FROM Evento e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Evento.findByFechaevento", query = "SELECT e FROM Evento e WHERE e.fechaevento = :fechaevento")
    , @NamedQuery(name = "Evento.findByFechalimiteentradas", query = "SELECT e FROM Evento e WHERE e.fechalimiteentradas = :fechalimiteentradas")
    , @NamedQuery(name = "Evento.findByPrecioentrada", query = "SELECT e FROM Evento e WHERE e.precioentrada = :precioentrada")
    , @NamedQuery(name = "Evento.findByAforo", query = "SELECT e FROM Evento e WHERE e.aforo = :aforo")
    , @NamedQuery(name = "Evento.findByMaximoentradasusuario", query = "SELECT e FROM Evento e WHERE e.maximoentradasusuario = :maximoentradasusuario")
    , @NamedQuery(name = "Evento.findByNfilas", query = "SELECT e FROM Evento e WHERE e.nfilas = :nfilas")
    , @NamedQuery(name = "Evento.findByAsientosfila", query = "SELECT e FROM Evento e WHERE e.asientosfila = :asientosfila")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE", nullable=false, length=200)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION", nullable=false, length=200)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "FECHAEVENTO", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fechaevento;
    @Basic(optional = false)
    @Column(name = "FECHALIMITEENTRADAS", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fechalimiteentradas;
    @Basic(optional = false)
    @Column(name = "PRECIOENTRADA", nullable=false)
    private double precioentrada;
    @Basic(optional = false)
    @Column(name = "AFORO", nullable=false)
    private int aforo;
    @Basic(optional = false)
    @Column(name = "MAXIMOENTRADASUSUARIO", nullable=false)
    private int maximoentradasusuario;
    @Basic(optional = false)
    @Column(name = "NFILAS", nullable=false)
    private int nfilas;
    @Basic(optional = false)
    @Column(name = "ASIENTOSFILA", nullable=false)
    private int asientosfila;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevento")
    private List<Conversacion> conversacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Inscripcion> inscripcionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Etiquetas> etiquetasList;
    @JoinColumn(name = "IDCREADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idcreador;

    public Evento() {
    }

    public Evento(Integer id) {
        this.id = id;
    }

    public Evento(Integer id, String nombre, String descripcion, Date fechaevento, Date fechalimiteentradas, double precioentrada, int aforo, int maximoentradasusuario, int nfilas, int asientosfila) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaevento = fechaevento;
        this.fechalimiteentradas = fechalimiteentradas;
        this.precioentrada = precioentrada;
        this.aforo = aforo;
        this.maximoentradasusuario = maximoentradasusuario;
        this.nfilas = nfilas;
        this.asientosfila = asientosfila;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getFechaevento() {
        return fechaevento;
    }

    public void setFechaevento(Date fechaevento) {
        this.fechaevento = fechaevento;
    }

    public Date getFechalimiteentradas() {
        return fechalimiteentradas;
    }

    public void setFechalimiteentradas(Date fechalimiteentradas) {
        this.fechalimiteentradas = fechalimiteentradas;
    }

    public double getPrecioentrada() {
        return precioentrada;
    }

    public void setPrecioentrada(double precioentrada) {
        this.precioentrada = precioentrada;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public int getMaximoentradasusuario() {
        return maximoentradasusuario;
    }

    public void setMaximoentradasusuario(int maximoentradasusuario) {
        this.maximoentradasusuario = maximoentradasusuario;
    }

    public int getNfilas() {
        return nfilas;
    }

    public void setNfilas(int nfilas) {
        this.nfilas = nfilas;
    }

    public int getAsientosfila() {
        return asientosfila;
    }

    public void setAsientosfila(int asientosfila) {
        this.asientosfila = asientosfila;
    }

    @XmlTransient
    public List<Conversacion> getConversacionList() {
        return conversacionList;
    }

    public void setConversacionList(List<Conversacion> conversacionList) {
        this.conversacionList = conversacionList;
    }

    @XmlTransient
    public List<Inscripcion> getInscripcionList() {
        return inscripcionList;
    }

    public void setInscripcionList(List<Inscripcion> inscripcionList) {
        this.inscripcionList = inscripcionList;
    }

    @XmlTransient
    public List<Etiquetas> getEtiquetasList() {
        return etiquetasList;
    }

    public void setEtiquetasList(List<Etiquetas> etiquetasList) {
        this.etiquetasList = etiquetasList;
    }

    public Usuario getIdcreador() {
        return idcreador;
    }

    public void setIdcreador(Usuario idcreador) {
        this.idcreador = idcreador;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.Evento[ id=" + id + " ]";
    }
    
}
