package com.mycompany.server.Entity;

import com.mycompany.server.Entity.Address_matrix;
import com.mycompany.server.Entity.Request;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T16:20:39")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, Request> request;
    public static volatile ListAttribute<Address, Address_matrix> matrix2;
    public static volatile ListAttribute<Address, Address_matrix> matrix1;
    public static volatile SingularAttribute<Address, String> name;
    public static volatile SingularAttribute<Address, Long> id;

}