/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Request;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Acer
 */
@Remote
public interface AddressRemote {
    Address getAddressById(Long id);
    void addAddress(Request r, String name);
    List<Address> getAllAddresses();
    List<Address> getAllAddressesOfRequest(Request r);
    void removeAddressesofRequest(Request r);
    public Address getAddressByName(String name);
    
}
