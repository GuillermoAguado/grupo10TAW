/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.dao;

import grupo10.entity.Estudiobd;
import grupo10.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dperez
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "grupo10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario findById (int id)
    {
        Query q;
        
        q = this.em.createNamedQuery("Usuario.findById");
        q.setParameter("id", id);
        
        Usuario resultado; 
        
        if(q.getResultList().size() > 0)
        {
            resultado = (Usuario) q.getResultList().get(0);
        }else
        {
            resultado = null;
        }
        return resultado;
    }
    
    public List<Usuario> findUsuarios (String correo)
    {
        Query q;
        q = this.em.createQuery("SELECT u FROM Usuario u WHERE u.email LIKE :correo");
        q.setParameter("correo", "%" + correo + "%");
        List<Usuario> resultados;
        resultados = q.getResultList();
        return resultados;
    }
    
    public Usuario findByEmailAndPassword(String email, String contrasenia){
        Query q;
        List<Usuario> lista;
        
        q = this.em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.contrasenia = :contrasenia");
        q.setParameter("email", email);
        q.setParameter("contrasenia", contrasenia);
        lista = q.getResultList();
        
        if (lista == null || lista.isEmpty()){
            // No existe ese usuario
            return null;
        } else {
            return lista.get(0);
        }
    }
    
    public List<Usuario> findAllCreadores() {
        Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.tipousuario=2");
        return q.getResultList();
    }
    
    public List<Usuario> encontrarUsuarios(Estudiobd estudio) {
       String peticion = "SELECT c FROM Usuario c WHERE ";
       Query q;
       List<Usuario> lista = new ArrayList<>();
       if(estudio.getEdadfiltro()>0){
           peticion = peticion + "c.edad >= :edadmayor ";
       }
       if(estudio.getEdadmenorquefiltro()>0){
           if(estudio.getEdadfiltro()>0){
               peticion = peticion + " and ";
           }
           peticion = peticion + "c.edad <= :edadmenor ";
       }
       
       if(estudio.getSexofiltro()!=null && estudio.getSexofiltro().length()>0){
           if(estudio.getEdadfiltro()>0 || estudio.getEdadmenorquefiltro()>0){
               peticion = peticion + " and ";
           }
           peticion = peticion + "c.sexo = :sexo ";
       }
       
       if(estudio.getCiudadusuariofiltro()!=null && estudio.getCiudadusuariofiltro().length()>0){
           if(estudio.getEdadfiltro()>0 || estudio.getEdadmenorquefiltro()>0 || estudio.getSexofiltro()!=null){
               peticion = peticion + " and ";
           }
           peticion = peticion + "c.ciudadRes = :ciudad_res";
       }
       
       q = this.em.createQuery(peticion);
       if(estudio.getEdadfiltro()>0){
           q.setParameter("edadmayor", estudio.getEdadfiltro());
       }
       if(estudio.getEdadmenorquefiltro()>0){
           q.setParameter("edadmenor", estudio.getEdadmenorquefiltro());
       }
       if(estudio.getSexofiltro()!=null && estudio.getSexofiltro().length()>0){
           q.setParameter("sexo", estudio.getSexofiltro());
       }
       if(estudio.getCiudadusuariofiltro()!=null && estudio.getCiudadusuariofiltro().length()>0){
           q.setParameter("ciudad_res", estudio.getCiudadusuariofiltro());
       }
       lista=q.getResultList();
       if(lista!=null && !lista.isEmpty()){
           return lista;
       }else{
           return null;
       }
    }
    
}
