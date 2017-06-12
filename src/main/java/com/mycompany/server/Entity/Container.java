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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Alimkhanov Ernur, Stakhan Temirlan, Serikuly Orynbek
 */
@Entity
@Table(name = "containers")
public class Container implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name= "container_id_gen", sequenceName = "container_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_id_gen")
    private Long id;
    
    @OneToOne(mappedBy = "container")
    @PrimaryKeyJoinColumn
    private Request request;
    
    private int width_x;
    private int height_y;
    private int length_z;
    
    
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
