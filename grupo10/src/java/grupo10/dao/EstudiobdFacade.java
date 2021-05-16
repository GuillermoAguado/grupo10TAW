/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.dao;

import grupo10.entity.Estudiobd;
import grupo10.entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
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
public class EstudiobdFacade extends AbstractFacade<Estudiobd> {

    @PersistenceContext(unitName = "grupo10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudiobdFacade() {
        super(Estudiobd.class);
    }
    
    
    public List<Usuario> encontrarUsuarios(Estudiobd estudio) {
       String peticion = "SELECT c FROM USUARIO c WHERE ";
       Query q;
       List<Usuario> lista = new ArrayList<>();
       if(estudio.getEdadfiltro()>0){
           peticion = peticion + "edad >= :edadmayor ";
       }
       if(estudio.getEdadmenorquefiltro()>0){
           if(estudio.getEdadfiltro()>0){
               peticion = peticion + " and ";
           }
           peticion = peticion + "edad <= :edadmenor ";
       }
       
       if(estudio.getSexofiltro()!=null){
           if(estudio.getEdadfiltro()>0 || estudio.getEdadmenorquefiltro()>0){
               peticion = peticion + " and ";
           }
           peticion = peticion + "sexo = :sexo ";
       }
       
       if(estudio.getCiudadusuariofiltro()!=null){
           if(estudio.getEdadfiltro()>0 || estudio.getEdadmenorquefiltro()>0 || estudio.getSexofiltro()!=null){
               peticion = peticion + " and ";
           }
           peticion = peticion + "ciudad_res = :ciudad_res";
       }
       
       q = this.em.createQuery(peticion);
       if(estudio.getEdadfiltro()>0){
           q.setParameter("edadmayor", estudio.getEdadfiltro());
       }
       if(estudio.getEdadmenorquefiltro()>0){
           q.setParameter("edadmenor", estudio.getEdadmenorquefiltro());
       }
       if(estudio.getSexofiltro()!=null){
           q.setParameter("sexo", estudio.getSexofiltro());
       }
       if(estudio.getCiudadusuariofiltro()!=null){
           q.setParameter("ciudad_res", estudio.getCiudadusuariofiltro());
       }
       lista=q.getResultList();
       if(lista!=null && !lista.isEmpty()){
           return lista;
       }else{
           return null;
       }
    }
    
    public void guardarEstudio(Estudiobd estudio){
        Query q;
        q = this.em.createQuery("UPDATE Estudiobd c SET c.Nombre = 'a'");
        q.executeUpdate();
    }

    public void actualizarValores(Date fecha2, Integer aforo2, String nombreestudio, String idestudio) {
        Query q;
        q=this.em.createQuery("UPDATE Estudiobd c SET c.nombre = :nombre , c.aforo = :aforo , c.fecha = :fecha WHERE c.id = :id");
        q.setParameter("nombre", nombreestudio);
        q.setParameter("aforo", aforo2);
        q.setParameter("fecha", fecha2);
        q.setParameter("id", idestudio);
        q.executeUpdate();
    }
}
