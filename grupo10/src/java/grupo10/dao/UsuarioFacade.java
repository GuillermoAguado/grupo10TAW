/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo10.dao;

import grupo10.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
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
    
}
