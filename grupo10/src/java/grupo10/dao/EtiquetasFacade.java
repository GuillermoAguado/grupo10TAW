/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.dao;

import grupo10.entity.Etiquetas;
import grupo10.entity.Evento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class EtiquetasFacade extends AbstractFacade<Etiquetas> {

    @PersistenceContext(unitName = "grupo10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtiquetasFacade() {
        super(Etiquetas.class);
    }
    
    public List<String> getEtiquetasDistintas() {
        Query q;
        q = em.createQuery("SELECT DISTINCT e.etiquetasPK.etiqueta FROM Etiquetas e");
        return (List<String>) q.getResultList();
    } 
    
    public Etiquetas findByEtiquetaYEvento(String e, Evento evento) {
        Query q;
        q = em.createQuery("SELECT e FROM Etiquetas e WHERE e.evento = :evento AND e.etiquetasPK.etiqueta = :etiqueta");
        q.setParameter("etiqueta", e);
        q.setParameter("evento", evento);
        try {
            return (Etiquetas) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
        
    }
    
}
