/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.dao;

import grupo10.entity.Estudiobd;
import grupo10.entity.Evento;
import grupo10.entity.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class EventoFacade extends AbstractFacade<Evento> {

    @PersistenceContext(unitName = "grupo10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }
    
    public List<Evento> getByCreador(Usuario creador) {
        Query q = em.createQuery("SELECT e FROM Evento e WHERE e.idcreador = :creador");
        q.setParameter("creador", creador);
        return q.getResultList();
    }
    
    public List<Evento> encontrarEventos(Estudiobd estudio) throws ParseException {
       String peticion = "SELECT c FROM Evento c WHERE ";
       Query q;
       List<Evento> lista = new ArrayList<>();
       DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
       if(estudio.getAnyofiltro()!=fecha.parse("1001-01-01")){
           peticion = peticion + "c.fechaevento = :fechaevento ";
       }
       if(estudio.getAforofiltro()>0){
           if(estudio.getAnyofiltro()!=fecha.parse("1001-01-01")){
               peticion = peticion + " and ";
           }
           peticion = peticion + "c.aforo <= :aforo ";
       }
       
       q = this.em.createQuery(peticion);
       if(estudio.getAnyofiltro()!=fecha.parse("1001-01-01")){
           q.setParameter("fechaevento", estudio.getAnyofiltro());
       }
       if(estudio.getAforofiltro()>0){
           q.setParameter("aforo", estudio.getAforofiltro());
       }
       lista=q.getResultList();
       if(lista!=null && !lista.isEmpty()){
           return lista;
       }else{
           return null;
       }
    }

    public List<Evento> encontrarAforo(Estudiobd estudio) {
        Query q;
        List<Evento> lista = new ArrayList<>();
        q=this.em.createQuery("SELECT e FROM Evento e WHERE e.aforo <= :aforo");
        q.setParameter("aforo", estudio.getAforofiltro());
        lista=q.getResultList();
        if(lista!=null && !lista.isEmpty()){
           return lista;
       }else{
           return null;
       }
    }

    public List<Evento> encontrarFecha(Estudiobd estudio) {
        Query q;
        List<Evento> lista = new ArrayList<>();
        q=this.em.createNamedQuery("Evento.findByFechaevento");
        q.setParameter("fechaevento", estudio.getAnyofiltro());
        lista=q.getResultList();
        if(lista!=null && !lista.isEmpty()){
           return lista;
       }else{
           return null;
       }
    }
    
}
