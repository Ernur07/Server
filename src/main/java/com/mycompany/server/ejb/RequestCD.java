/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Client;
import com.mycompany.server.Entity.Request;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Acer
 */
@Stateless(mappedName = "requestBean")
public class RequestCD implements RequestRemote {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void addRequest(Client c) {
        
        Request r=new Request();
        em.persist(r);
        em.flush();
        r.setClient(c);
        em.merge(r);
        em.flush();
        
    
    }

    public Request findRequest(Long id){
        Request r = em.find(Request.class, id);
        return r;        
    }
    
    @Override
    public void updateRequest(Request r) {
        em.merge(r);
        em.flush();
    }

    @Override
    public List<Request> getAllRequestsOfClient(Client c) {
        List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM requests WHERE client_id = " + c.getId()).getResultList();
        ArrayList<Request> results = new ArrayList<>();
        for(Long id: lr) {
            results.add(findRequest(id));
        }
        return results;
        
    }

    @Override
    public List<Request> getAllRequests() {
        
        
        List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM requests ");
        ArrayList<Request> results = new ArrayList<>();
        for(Long id: lr) {
            results.add(findRequest(id));
        }
        return results;
    }

    @Override
    public void removeRequest(Long id) {
       Request r = findRequest(id);
        em.merge(r);
        em.flush();
        em.remove(r);
        em.flush();  
    }
    
}

