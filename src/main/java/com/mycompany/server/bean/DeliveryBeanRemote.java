/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.bean;

import com.google.maps.model.LatLng;

import java.util.List;

/**
 *
 * @author Acer
 */
public interface DeliveryBeanRemote {
   // public void addOrder(Long p, Long d, String name, String address, Integer number);
    
    //public void addDistrict(String name);
    
  //  public void addProduct(String name, Long price);
    
    //public List<District> getDistrict();
    
   // public List<Product> getProduct();
    
    public String getLocation(String address);
    
    public LatLng getLocationLatLng(String address);
    
    public List<Integer> getShortestHamiltonianCycle(int[][] dist);
    
    public List<String> getNavigationCycle(List<String> add);
    
    public List<String> getNavigationGA(List<String> add, String key)throws Exception;
        
    public String getPacking(int[][] prod, List<Integer> con, List<String> add);
    
    public String getPackingIP(int[][] prod, List<Integer> con, List<String> add);
    
}
