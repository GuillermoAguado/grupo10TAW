/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Embeddable
public class EtiquetasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEVENTO")
    private int idevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ETIQUETA")
    private String etiqueta;

    public EtiquetasPK() {
    }

    public EtiquetasPK(int idevento, String etiqueta) {
        this.idevento = idevento;
        this.etiqueta = etiqueta;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idevento;
        hash += (etiqueta != null ? etiqueta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtiquetasPK)) {
            return false;
        }
        EtiquetasPK other = (EtiquetasPK) object;
        if (this.idevento != other.idevento) {
            return false;
        }
        if ((this.etiqueta == null && other.etiqueta != null) || (this.etiqueta != null && !this.etiqueta.equals(other.etiqueta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.EtiquetasPK[ idevento=" + idevento + ", etiqueta=" + etiqueta + " ]";
    }
    
}
