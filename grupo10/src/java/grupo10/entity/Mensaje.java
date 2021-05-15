/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "MENSAJE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m")
    , @NamedQuery(name = "Mensaje.findByFechahora", query = "SELECT m FROM Mensaje m WHERE m.fechahora = :fechahora")
    , @NamedQuery(name = "Mensaje.findByContenido", query = "SELECT m FROM Mensaje m WHERE m.contenido = :contenido")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CONTENIDO")
    private String contenido;
    @JoinColumn(name = "IDCONVERSACION", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Conversacion idconversacion;
    @JoinColumn(name = "IDEMISOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idemisor;

    public Mensaje() {
    }

    public Mensaje(Date fechahora) {
        this.fechahora = fechahora;
    }

    public Mensaje(Date fechahora, String contenido) {
        this.fechahora = fechahora;
        this.contenido = contenido;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Conversacion getIdconversacion() {
        return idconversacion;
    }

    public void setIdconversacion(Conversacion idconversacion) {
        this.idconversacion = idconversacion;
    }

    public Usuario getIdemisor() {
        return idemisor;
    }

    public void setIdemisor(Usuario idemisor) {
        this.idemisor = idemisor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechahora != null ? fechahora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.fechahora == null && other.fechahora != null) || (this.fechahora != null && !this.fechahora.equals(other.fechahora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.Mensaje[ fechahora=" + fechahora + " ]";
    }
    
}
