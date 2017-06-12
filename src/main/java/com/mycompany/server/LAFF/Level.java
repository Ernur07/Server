/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.LAFF;

/**
 *
 * @author Alimkhanov Ernur, Stakhan Temirlan, Serikuly Orynbek
 */
import java.util.ArrayList;

/**
 * A level within a container
 * 
 */

public class Level extends ArrayList<Box>{
	
	public int getHeight() {
		int height = 0;
		
		for(Box box : this) {
			if(box.getHeight() > height) {
				height = box.getHeight();
			}
		}
		
		return height;
	}
	
	
}
