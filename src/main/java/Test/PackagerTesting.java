
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author User
 */

import java.util.ArrayList;
import java.util.List;


import com.mycompany.server.LAFF.Box;
import com.mycompany.server.LAFF.Container;
import com.mycompany.server.LAFF.Packager;
import com.mycompany.server.LAFF.Dimension;

public class PackagerTesting {

	public void testStackingSquaresOnSquare() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 3));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("Product 1", 6, 10, 2));
                products.add(new Box("Product 2", 4, 10, 1));
                products.add(new Box("Product 3", 4, 10, 2));
		
		Container fits = packager.pack(products);
                
            
               if(fits!=null){ System.out.print(fits.printLevels());
                }
               else System.out.print("No solution!");
                
                
                
	}
	
}
