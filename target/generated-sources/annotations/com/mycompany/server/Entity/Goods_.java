package com.mycompany.server.Entity;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Request;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T16:20:39")
@StaticMetamodel(Goods.class)
public class Goods_ { 

    public static volatile SingularAttribute<Goods, Request> request;
    public static volatile SingularAttribute<Goods, Address> address;
    public static volatile SingularAttribute<Goods, Integer> width_x;
    public static volatile SingularAttribute<Goods, Integer> height_y;
    public static volatile SingularAttribute<Goods, Integer> length_z;
    public static volatile SingularAttribute<Goods, Long> id;

}