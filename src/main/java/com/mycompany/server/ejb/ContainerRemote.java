/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.ejb;

import com.mycompany.server.Entity.Container;
import com.mycompany.server.Entity.Request;
import javax.ejb.Remote;

/**
 *
 * @author Acer
 */
@Remote 
public interface ContainerRemote {
    void addContainer(Container c);
    void removeContainerByRequest(Request r);
    Container getContainerByRequest(Request r);
    Container getContainerById(Long id);
}
