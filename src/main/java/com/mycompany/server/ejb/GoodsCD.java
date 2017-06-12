/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Goods;
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
@Stateless(mappedName ="goodsBean")
public class GoodsCD implements GoodsRemote{
@PersistenceContext
EntityManager em;


    public Goods getGoods(Long id){
        Goods g = em.find(Goods.class, id);
        return g;
    }
    @Override
    public void addGoods(Goods g) {
        em.persist(g);
        em.flush();
    }

    @Override
    public List<Goods> getGoodsByAddress(Address a) {
        List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM goods WHERE address_id = " + a.getId()).getResultList();
        ArrayList<Goods> results = new ArrayList<>();
        for(Long id: lr) {
            results.add(getGoods(id));
        }
        return results;
    }

    @Override
    public List<Goods> getGoodsByRequest(Request r) {
        List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM goods WHERE request_id = " + r.getId()).getResultList();
        ArrayList<Goods> results = new ArrayList<>();
        for(Long id: lr) {
            results.add(getGoods(id));
        }
        return results;
    }

    @Override
    public void removeGoodsByRequest(Request r) {
        List<Long> lr = (List<Long>) em.createNativeQuery("SELECT id FROM goods WHERE request_id = " + r.getId()).getResultList();
        for(Long id: lr) {
            Goods g = getGoods(id);
           
        em.merge(g);
        em.flush();
        em.remove(g);
        em.flush(); 
        }
    }
    
}