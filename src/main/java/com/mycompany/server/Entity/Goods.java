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
@Table(name = "goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "goods_id_gen", sequenceName = "goods_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_id_gen")
    private Long id;
    private int width_x;
    private int height_y;
    private int length_z;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "request_id")
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getWidth_x() {
        return width_x;
    }

    public void setWidth_x(int width_x) {
        this.width_x = width_x;
    }

    public int getHeight_y() {
        return height_y;
    }

    public void setHeight_y(int height_y) {
        this.height_y = height_y;
    }

    public int getLength_z() {
        return length_z;
    }

    public void setLength_z(int length_z) {
        this.length_z = length_z;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
