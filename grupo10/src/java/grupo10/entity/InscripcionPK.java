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

/**
 *
 * @author Usuario
 */
@Embeddable
public class InscripcionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUSUARIO")
    private int idusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEVENTO")
    private int idevento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FILAASIENTO")
    private int filaasiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COLUMNAASIENTO")
    private int columnaasiento;

    public InscripcionPK() {
    }

    public InscripcionPK(int idusuario, int idevento, int filaasiento, int columnaasiento) {
        this.idusuario = idusuario;
        this.idevento = idevento;
        this.filaasiento = filaasiento;
        this.columnaasiento = columnaasiento;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    public int getFilaasiento() {
        return filaasiento;
    }

    public void setFilaasiento(int filaasiento) {
        this.filaasiento = filaasiento;
    }

    public int getColumnaasiento() {
        return columnaasiento;
    }

    public void setColumnaasiento(int columnaasiento) {
        this.columnaasiento = columnaasiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idusuario;
        hash += (int) idevento;
        hash += (int) filaasiento;
        hash += (int) columnaasiento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionPK)) {
            return false;
        }
        InscripcionPK other = (InscripcionPK) object;
        if (this.idusuario != other.idusuario) {
            return false;
        }
        if (this.idevento != other.idevento) {
            return false;
        }
        if (this.filaasiento != other.filaasiento) {
            return false;
        }
        if (this.columnaasiento != other.columnaasiento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.InscripcionPK[ idusuario=" + idusuario + ", idevento=" + idevento + ", filaasiento=" + filaasiento + ", columnaasiento=" + columnaasiento + " ]";
    }
    
}
