/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Client;
import com.mycompany.server.Entity.Request;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Acer
 */
@Remote
public interface RequestRemote {
    void addRequest(Client c);
    Request findRequest(Long id);
    void updateRequest(Request r);
    void removeRequest(Long id);
    List<Request> getAllRequestsOfClient(Client c);
    List<Request> getAllRequests();
    //void addJSONSToRequest(String jsonp, String jsonc);
}
