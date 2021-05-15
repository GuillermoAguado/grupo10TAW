/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.dao;

import grupo10.entity.Conversacion;
import grupo10.entity.Usuario;
import grupo10.util.StringUtils;
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
public class ConversacionFacade extends AbstractFacade<Conversacion> {

    @PersistenceContext(unitName = "grupo10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConversacionFacade() {
        super(Conversacion.class);
    }
    
    public List<Conversacion> findByUsuario(Usuario yo, String substrOtro, String substrEvento) {
        
        if (substrOtro != null) {
            substrOtro = "%" + StringUtils.escaparSQLLike(substrOtro) + "%";
        } else {
            substrOtro = "%";
        }
        
        if (substrEvento != null) {
            substrEvento = "%" + StringUtils.escaparSQLLike(substrEvento) + "%";
        } else {
            substrEvento = "%";
        }
        
        Query q = em.createQuery("SELECT c FROM Conversacion c WHERE "
                + "((c.idteleoperador = :yo AND "
                + "LOWER(CONCAT(c.idusuario.nombre, ' ', c.idusuario.apellidos)) LIKE LOWER(:otro) ESCAPE '.') "
                + "OR (c.idusuario = :yo AND "
                + "LOWER(CONCAT(c.idteleoperador.nombre, ' ', c.idteleoperador.apellidos)) LIKE LOWER(:otro) ESCAPE '.')) "
                + "AND LOWER(c.idevento.nombre) LIKE LOWER(:evento) ESCAPE '.'");
        
        q.setParameter("yo", yo);
        q.setParameter("otro", substrOtro);
        q.setParameter("evento", substrEvento);
        
        return q.getResultList();
        
    }
    
}
