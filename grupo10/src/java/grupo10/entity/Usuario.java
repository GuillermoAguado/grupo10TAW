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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByContrasenia", query = "SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByDomicilio", query = "SELECT u FROM Usuario u WHERE u.domicilio = :domicilio")
    , @NamedQuery(name = "Usuario.findByCiudadRes", query = "SELECT u FROM Usuario u WHERE u.ciudadRes = :ciudadRes")
    , @NamedQuery(name = "Usuario.findByEdad", query = "SELECT u FROM Usuario u WHERE u.edad = :edad")
    , @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo")
    , @NamedQuery(name = "Usuario.findByTipousuario", query = "SELECT u FROM Usuario u WHERE u.tipousuario = :tipousuario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Column(name = "EMAIL", length=50, nullable = false)
    private String email;
    @Basic(optional = false)
    @Column(name = "CONTRASENIA", length=50, nullable = false)
    private String contrasenia;
    @Basic(optional = false)
    @Column(name = "NOMBRE", length=50, nullable = false)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDOS", length=200, nullable = false)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "DOMICILIO", length=200, nullable = false)
    private String domicilio;
    @Basic(optional = false)
    @Column(name = "CIUDAD_RES", length=50, nullable = false)
    private String ciudadRes;
    @Basic(optional = false)
    @Column(name = "EDAD", nullable = false)
    private int edad;
    @Basic(optional = false)
    @Column(name = "SEXO", length=1, nullable = false)
    private String sexo;
    @Basic(optional = false)
    @Column(name = "TIPOUSUARIO", nullable = false)
    private int tipousuario;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Estudiobd> estudiobdList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Conversacion> conversacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idteleoperador")
    private List<Conversacion> conversacionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Inscripcion> inscripcionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautor")
    private List<Estudiobd> estudiobdList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcreador")
    private List<Evento> eventoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idemisor")
    private List<Mensaje> mensajeList;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String email, String contrasenia, String nombre, String apellidos, String domicilio, String ciudadRes, int edad, String sexo, int tipousuario) {
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.ciudadRes = ciudadRes;
        this.edad = edad;
        this.sexo = sexo;
        this.tipousuario = tipousuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudadRes() {
        return ciudadRes;
    }

    public void setCiudadRes(String ciudadRes) {
        this.ciudadRes = ciudadRes;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(int tipousuario) {
        this.tipousuario = tipousuario;
    }

    @XmlTransient
    public List<Estudiobd> getEstudiobdList() {
        return estudiobdList;
    }

    public void setEstudiobdList(List<Estudiobd> estudiobdList) {
        this.estudiobdList = estudiobdList;
    }

    @XmlTransient
    public List<Conversacion> getConversacionList() {
        return conversacionList;
    }

    public void setConversacionList(List<Conversacion> conversacionList) {
        this.conversacionList = conversacionList;
    }

    @XmlTransient
    public List<Conversacion> getConversacionList1() {
        return conversacionList1;
    }

    public void setConversacionList1(List<Conversacion> conversacionList1) {
        this.conversacionList1 = conversacionList1;
    }

    @XmlTransient
    public List<Inscripcion> getInscripcionList() {
        return inscripcionList;
    }

    public void setInscripcionList(List<Inscripcion> inscripcionList) {
        this.inscripcionList = inscripcionList;
    }

    @XmlTransient
    public List<Estudiobd> getEstudiobdList1() {
        return estudiobdList1;
    }

    public void setEstudiobdList1(List<Estudiobd> estudiobdList1) {
        this.estudiobdList1 = estudiobdList1;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo10.entity.Usuario[ id=" + id + " ]";
    }
    
}
