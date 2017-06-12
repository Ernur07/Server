/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Acer
 */
@Stateless(mappedName = "clientBean")
public class ClientCD implements ClientRemote{
    @PersistenceContext
    EntityManager em;

    @Override
    public void addClient(Client c) {
        em.persist(c);
        em.flush();
    }

    @Override
    public void changeStatus(Client c) {
        em.merge(c);
        em.flush();
    }

    @Override
    public boolean checkKey(String key) {
        
        Client c = null;
        try{
        c = (Client) em.createQuery("SELECT c FROM Client c where "
                + "c.key = :key")
                .setParameter("key", key).getSingleResult();
        }catch(Exception e){e.printStackTrace();}
        if(c == null){
            return false;
        }
        else if(c.isStatus()==true){
            return true;
        }else
        {
            return false;
        }
    }

    @Override
    public void removeClient(Long id) {
        Client c = getClientById(id);
        em.merge(c);
        em.flush();
        em.remove(c);
        em.flush(); 
        
    }

    @Override
    public Client getClientById(Long id) {
            Client c = em.find(Client.class, id);
        return c;
    }

    @Override
    public Client getClientBYKey(String key) {
        Client c = null;
        try{
        c = (Client) em.createQuery("SELECT c FROM Client c where "
                + "c.key = :key")
                .setParameter("key", key).getSingleResult();
        }catch(Exception e){e.printStackTrace();}
        
        return c;
    }
    
}

