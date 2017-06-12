/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;
import com.mycompany.server.Entity.Client;
import javax.ejb.Remote;

/**
 *
 * @author Acer
 */
@Remote
public interface ClientRemote {
    void addClient(Client c);
    void changeStatus(Client c);
    boolean checkKey(String key);
    void removeClient(Long id);
    Client getClientById(Long id);
    Client getClientBYKey(String key);
}
