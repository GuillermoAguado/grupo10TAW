/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "CONVERSACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversacion.findAll", query = "SELECT c FROM Conversacion c")
    , @NamedQuery(name = "Conversacion.findById", query = "SELECT c FROM Conversacion c WHERE c.id = :id")})
public class Conversacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "IDEVENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Evento idevento;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "IDTELEOPERADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idteleoperador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idconversacion")
    private List<Mensaje> mensajeList;

    public Conversacion() {
    }

    public Conversacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evento getIdevento() {
        return idevento;
    }

    public void setIdevento(Evento idevento) {
        this.idevento = idevento;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario getIdteleoperador() {
        return idteleoperador;
    }

    public void setIdteleoperador(Usuario idteleoperador) {
        this.idteleoperador = idteleoperador;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
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
        if (!(object instanceof Conversacion)) {
            return false;
        }
        Conversacion other = (Conversacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.Conversacion[ id=" + id + " ]";
    }
    
}
