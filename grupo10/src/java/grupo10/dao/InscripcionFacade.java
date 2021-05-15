/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.dao;

import grupo10.entity.Inscripcion;
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
public class InscripcionFacade extends AbstractFacade<Inscripcion> {

    @PersistenceContext(unitName = "grupo10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionFacade() {
        super(Inscripcion.class);
    }
    
    public List<Inscripcion> findByIdUsuario(Integer id){
        Query q;
        List<Inscripcion> inscripcion;
        
        q = this.em.createNamedQuery("Inscripcion.findByIdusuario");
        q.setParameter("idusuario", id);
        inscripcion = q.getResultList();
        
        if (inscripcion == null || inscripcion.isEmpty()){
            return null;
        } else {
            return inscripcion;
        }
    }
         
    public List<Inscripcion> findByEventoYUsuario(Integer evento, Integer usuario){
        Query q;
        List<Inscripcion> inscripcion;
        
        q = this.em.createQuery("select i from Inscripcion i where i.inscripcionPK.idevento = :idEvento and i.inscripcionPK.idusuario = :idUsuario");
        q.setParameter("idEvento", evento);
        q.setParameter("idUsuario", usuario);
        inscripcion = q.getResultList();
        
        if (inscripcion == null || inscripcion.isEmpty()){
            return null;
        } else {
            return inscripcion;
        }
    }
    
    public Inscripcion findByAll(Integer evento, Integer usuario, Integer filaAsiento, Integer columnaAsiento){
        Query q;
        List<Inscripcion> inscripcion;
        
        q = this.em.createQuery("select i from Inscripcion i where i.inscripcionPK.idevento = :idEvento and i.inscripcionPK.idusuario = :idUsuario and i.inscripcionPK.filaasiento = :filaAsiento and i.inscripcionPK.columnaasiento = :columnaAsiento");
        q.setParameter("idEvento", evento);
        q.setParameter("idUsuario", usuario);
        q.setParameter("filaAsiento", filaAsiento);
        q.setParameter("columnaAsiento", columnaAsiento);
        inscripcion = q.getResultList();
        
        if (inscripcion == null || inscripcion.isEmpty()){
            return null;
        } else {
            return inscripcion.get(0);
        }
    }
    
    public List<Inscripcion> findByIdEvento(Integer idEvento){
        Query q;
        List<Inscripcion> listaInscripcion;
            
        q = this.em.createNamedQuery("Inscripcion.findByIdevento");
        q.setParameter("idevento", idEvento);
        listaInscripcion = q.getResultList();
        if (listaInscripcion == null || listaInscripcion.isEmpty()){
            return null;
        } else {
            return listaInscripcion;
        }
    }
    
    public List<Inscripcion> findByIdUsuarioYFiltro(Integer id, String filtro){
        Query q;
        List<Inscripcion> lista;
        
        q = this.em.createQuery("select i from Inscripcion i where i.inscripcionPK.idusuario = :id and lower(i.evento.nombre) like lower(:filtro)");
        q.setParameter("id", id);
        q.setParameter("filtro", "%" + filtro + "%");
        lista = q.getResultList();
        if (lista == null || lista.isEmpty()){
            return null;
        } else {
            return lista;
        }
    }
    
}
