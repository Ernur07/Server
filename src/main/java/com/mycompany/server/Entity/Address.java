/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Alimkhanov Ernur, Stakhan Temirlan, Serikuly Orynbek
 */
@Entity
@Table(name= "addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "address_id_gen", sequenceName = "address_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_gen")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "request_id")
    private Request request;
    
    @OneToMany(mappedBy = "address1",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Address_matrix> matrix1;
    
    @OneToMany(mappedBy = "address2",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Address_matrix> matrix2;

    public List<Address_matrix> getMatrix1() {
        return matrix1;
    }

    public void setMatrix1(List<Address_matrix> matrix1) {
        this.matrix1 = matrix1;
    }

    public List<Address_matrix> getMatrix2() {
        return matrix2;
    }

    public void setMatrix2(List<Address_matrix> matrix2) {
        this.matrix2 = matrix2;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
