/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Container;
import com.mycompany.server.Entity.Request;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Acer
 */
@Stateless(mappedName = "containerBean")
public class ContainerCD implements ContainerRemote{
    @PersistenceContext
    EntityManager em;

    @Override
    public void addContainer(Container c) {
        em.persist(c);
        em.flush();
    }

    @Override
    public void removeContainerByRequest(Request r) {
        Container c = (Container) em.createQuery("SELECT c FROM Container c WHERE c.request_id = :rid").setParameter("rid", r.getId()).getSingleResult();
        em.merge(c);
        em.flush();
        em.remove(c);
        em.flush();
    }

    @Override
    public Container getContainerByRequest(Request r) {
        Container c = (Container) em.createQuery("SELECT c FROM Container c WHERE c.request_id = :rid").setParameter("rid", r.getId()).getSingleResult();
        
        return c;
    }

    @Override
    public Container getContainerById(Long id) {
        Container c = em.find(Container.class, id);
        return c;
    }
    
}

