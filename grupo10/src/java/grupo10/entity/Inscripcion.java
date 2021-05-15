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
@Table(name = "INSCRIPCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscripcion.findAll", query = "SELECT i FROM Inscripcion i")
    , @NamedQuery(name = "Inscripcion.findByIdusuario", query = "SELECT i FROM Inscripcion i WHERE i.inscripcionPK.idusuario = :idusuario")
    , @NamedQuery(name = "Inscripcion.findByIdevento", query = "SELECT i FROM Inscripcion i WHERE i.inscripcionPK.idevento = :idevento")
    , @NamedQuery(name = "Inscripcion.findByFilaasiento", query = "SELECT i FROM Inscripcion i WHERE i.inscripcionPK.filaasiento = :filaasiento")
    , @NamedQuery(name = "Inscripcion.findByColumnaasiento", query = "SELECT i FROM Inscripcion i WHERE i.inscripcionPK.columnaasiento = :columnaasiento")})
public class Inscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InscripcionPK inscripcionPK;
    @JoinColumn(name = "IDEVENTO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Inscripcion() {
    }

    public Inscripcion(InscripcionPK inscripcionPK) {
        this.inscripcionPK = inscripcionPK;
    }

    public Inscripcion(int idusuario, int idevento, int filaasiento, int columnaasiento) {
        this.inscripcionPK = new InscripcionPK(idusuario, idevento, filaasiento, columnaasiento);
    }

    public InscripcionPK getInscripcionPK() {
        return inscripcionPK;
    }

    public void setInscripcionPK(InscripcionPK inscripcionPK) {
        this.inscripcionPK = inscripcionPK;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscripcionPK != null ? inscripcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscripcion)) {
            return false;
        }
        Inscripcion other = (Inscripcion) object;
        if ((this.inscripcionPK == null && other.inscripcionPK != null) || (this.inscripcionPK != null && !this.inscripcionPK.equals(other.inscripcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.Inscripcion[ inscripcionPK=" + inscripcionPK + " ]";
    }
    
}
