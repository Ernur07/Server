/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Address_matrix;
import com.mycompany.server.Entity.Request;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Acer
 */
@Remote
public interface Address_matrixRemote {
    void addAddress_matrix(Address i, Address j,Double dis,Request r );
    void removeAddress_matrixOfRequest(Request r);
    Address_matrix getAMBI(Long id);
    List<Address_matrix> getAllMatricesOfRequest(Request r);
    //List<Address_matrix> getAllMatricesOfAddress(Address a);
    public List<Address_matrix> getAllMatricesOfAddress(String name);
    public double getDistance(String name1, String name2);
    
            
    
}

