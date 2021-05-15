/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "ETIQUETAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etiquetas.findAll", query = "SELECT e FROM Etiquetas e")
    , @NamedQuery(name = "Etiquetas.findByIdevento", query = "SELECT e FROM Etiquetas e WHERE e.etiquetasPK.idevento = :idevento")
    , @NamedQuery(name = "Etiquetas.findByEtiqueta", query = "SELECT e FROM Etiquetas e WHERE e.etiquetasPK.etiqueta = :etiqueta")})
public class Etiquetas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EtiquetasPK etiquetasPK;
    @JoinColumn(name = "IDEVENTO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento;

    public Etiquetas() {
    }

    public Etiquetas(EtiquetasPK etiquetasPK) {
        this.etiquetasPK = etiquetasPK;
    }

    public Etiquetas(int idevento, String etiqueta) {
        this.etiquetasPK = new EtiquetasPK(idevento, etiqueta);
    }

    public EtiquetasPK getEtiquetasPK() {
        return etiquetasPK;
    }

    public void setEtiquetasPK(EtiquetasPK etiquetasPK) {
        this.etiquetasPK = etiquetasPK;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etiquetasPK != null ? etiquetasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etiquetas)) {
            return false;
        }
        Etiquetas other = (Etiquetas) object;
        if ((this.etiquetasPK == null && other.etiquetasPK != null) || (this.etiquetasPK != null && !this.etiquetasPK.equals(other.etiquetasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.Etiquetas[ etiquetasPK=" + etiquetasPK + " ]";
    }
    
}
