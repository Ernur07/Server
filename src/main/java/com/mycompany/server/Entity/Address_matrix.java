/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.Entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Alimkhanov Ernur, Stakhan Temirlan, Serikuly Orynbek
 */
@Entity
@Table(name = "address_matrixs")
public class Address_matrix implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "address_matrix_id_gen", sequenceName = "address_matrix_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_matrix_id_gen")
    private Long id;
    
    private Double distance;

    
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "fadd_id")
    private Address address1;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "sadd_id")
    private Address address2;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "request_id")
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Address getAddress1() {
        return address1;
    }

    public Address getAddress2() {
        return address2;
    }

    public void setAddress1(Address address1) {
        this.address1 = address1;
    }

    public void setAddress2(Address address2) {
        this.address2 = address2;
    }
    


    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
