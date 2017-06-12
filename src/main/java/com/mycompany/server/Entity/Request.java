/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alimkhanov Ernur, Stakhan Temirlan, Serikuly Orynbek
 */
@Entity
@Table(name = "requests")
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "request_id_gen", sequenceName = "request_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_id_gen")
    private Long id;
    private String json_path;
    private String json_cont;
    private boolean status;
    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client client;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Container container; 

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
    
    @OneToMany(mappedBy = "request", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Address> addresses;
    @OneToMany(mappedBy = "request", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Goods> goods;
    
    @OneToMany(mappedBy = "request", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Address_matrix> address_matrices;

    public List<Address_matrix> getAddress_matrices() {
        return address_matrices;
    }

    public void setAddress_matrices(List<Address_matrix> address_matrices) {
        this.address_matrices = address_matrices;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    

    public String getJson_path() {
        return json_path;
    }

    public void setJson_path(String json_path) {
        this.json_path = json_path;
    }

    public String getJson_cont() {
        return json_cont;
    }

    public void setJson_cont(String json_cont) {
        this.json_cont = json_cont;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
