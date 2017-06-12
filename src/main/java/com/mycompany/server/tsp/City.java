/*
* City.java
* Models a city
*/

package com.mycompany.server.tsp;

import com.mycompany.server.ejb.Address_matrixRemote;
import javax.ejb.EJB;
/**
 *
 * @author Alimkhanov Ernur, Stakhan Temirlan, Serikuly Orynbek
 */


public class City {
    
    @EJB
    Address_matrixRemote matrixBean;
            
    
    String name;
    double x;
    double y;
    int i;
    
    // Constructs a randomly placed city
    public City(){
        this.x = (double)(Math.random()*200);
        this.y = (double)(Math.random()*200);
        this.name = null;
        this.i = 0;
    }
    
    // Constructs a city at chosen x, y location
    public City(double x, double y, String name, int i){
        this.x = x;
        this.y = y;
        this.name = name;
        this.i = i;
    }
    
    // Gets city's x coordinate
    public double getX(){
        return this.x;
    }
    
    // Gets city's y coordinate
    public double getY(){
        return this.y;
    }
    
        // Gets city's x coordinate
    public String getName(){
        return this.name;
    }
    
    // Gets city's y coordinate
    public void setName(String name){
        this.name = name;
    }
    
    public int getIndex(){
        return this.i;
    }
    // Gets the distance to given city
    public double distanceTo(City city) throws Exception{             
        
        return DistanceArray.latLngmat[this.i][city.getIndex()];
    }
    
    @Override
    public String toString(){
        return getX()+", "+getY();
    }
}
