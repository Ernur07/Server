/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Goods;
import com.mycompany.server.Entity.Request;
import javax.ejb.Remote;
import java.util.List;

/**
 *
 * @author Acer
 */
@Remote
public interface GoodsRemote {
    void addGoods(Goods g);
    Goods getGoods(Long id); 
    List<Goods> getGoodsByAddress(Address a);
    List<Goods> getGoodsByRequest(Request r);
    void removeGoodsByRequest(Request r);
  
}
