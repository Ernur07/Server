/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Address_matrix;
import com.mycompany.server.Entity.Request;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Acer
 */
@Stateless(mappedName = "address_matrixBean")
public class Address_matrixCD implements Address_matrixRemote {
    @PersistenceContext
    EntityManager em;
    
    @EJB
    AddressRemote addressBean;

    @Override
    public void addAddress_matrix(Address i, Address j,Double dis, Request r ) {
        Address_matrix am = new Address_matrix();
        
        em.persist(am);
        em.flush();
        am.setAddress1(i);
        am.setAddress2(j);
        am.setDistance(dis);
        am.setRequest(r);
        em.merge(am);
        em.flush();
    }

    @Override
    public void removeAddress_matrixOfRequest(Request r) {
        List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM address_matrixs WHERE request_id = " + r.getId()).getResultList();
        
        for(Long id: lr) {
            Address_matrix am = getAMBI(id);
            em.merge(am);
            em.flush();
            em.remove(am);
            em.flush(); 
            
        }
    }

    @Override
    public List<Address_matrix> getAllMatricesOfRequest(Request r) {
       List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM address_matrixs WHERE request_id = " + r.getId()).getResultList();
        ArrayList<Address_matrix> results = new ArrayList<>();
        for(Long id: lr) {
            results.add(getAMBI(id));
        }
        return results;
    }

    @Override
    public List<Address_matrix> getAllMatricesOfAddress(String name) {
       List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM address_matrixs WHERE fadd_id = " + addressBean.getAddressByName(name).getId()).getResultList();
        ArrayList<Address_matrix> results = new ArrayList<>();
        for(Long id: lr) {
            results.add(getAMBI(id));
        }
        return results;
    }
    
    /**
     *
     * @param name1
     * @param name2
     * @return
     */
    @Override
    public double getDistance(String name1, String name2){
            Long id = null;
            try{
            id = (Long) em.createNativeQuery("SELECT id FROM address_matrixs WHERE fadd_id = "+addressBean.getAddressByName(name1).getId() +" AND sadd_id = "+addressBean.getAddressByName(name1).getId()).getSingleResult();
            
            }catch(Exception e){e.printStackTrace();}
            System.out.println(id);
            
            Address_matrix v = getAMBI(id);
        
        return v.getDistance();
        
    }
    /*@Override
    public List<Address_matrix> getAllMatricesOfAddress(Address a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public Address_matrix getAMBI(Long id) {
        Address_matrix am = em.find(Address_matrix.class, id);
        return am;
    }
    
}

