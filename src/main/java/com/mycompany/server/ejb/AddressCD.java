/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Address;

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
@Stateless( mappedName = "addressBean")
public class AddressCD implements AddressRemote {
    @PersistenceContext
    EntityManager em;

    @Override
    public void addAddress(Request r, String name) {
        Address a = new Address();
        
        em.persist(a);
        em.flush();
        a.setRequest(r);
        a.setName(name);
        em.merge(a);
        em.flush();
        
        
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM addresses");
        ArrayList<Address> results = new ArrayList<>();
        for(Long id: lr) {
            results.add(getAddressById(id));
        }
        return results;
    }

    @Override
    public List<Address> getAllAddressesOfRequest(Request r) {
        
        List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM addresses WHERE request_id = " + r.getId()).getResultList();
        ArrayList<Address> results = new ArrayList<>();
        for(Long id: lr) {
            results.add(getAddressById(id));
        }
        return results;
    }

    @Override
    public void removeAddressesofRequest(Request r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address getAddressById(Long id) {
        Address a = em.find(Address.class, id);
        return a;
    }
    
    @Override
    public Address getAddressByName(String name) {
        /*
                try{
        c = (Client) em.createQuery("SELECT c FROM Client c where "
                + "c.key = :key")
                .setParameter("key", key).getSingleResult();
        }catch(Exception e){e.printStackTrace();}
        */
        Address a = null;
        try{
        a = (Address) em.createQuery("SELECT a FROM Address a WHERE "
                + "a.name = :name").setParameter("name", name).getSingleResult();
        }catch(Exception e){e.printStackTrace();}
        return a;
    }
    
}

