package com.mycompany.server.Entity;

import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Address_matrix;
import com.mycompany.server.Entity.Client;
import com.mycompany.server.Entity.Container;
import com.mycompany.server.Entity.Goods;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T16:20:39")
@StaticMetamodel(Request.class)
public class Request_ { 

    public static volatile SingularAttribute<Request, Container> container;
    public static volatile ListAttribute<Request, Address> addresses;
    public static volatile SingularAttribute<Request, String> json_cont;
    public static volatile ListAttribute<Request, Address_matrix> address_matrices;
    public static volatile SingularAttribute<Request, String> json_path;
    public static volatile SingularAttribute<Request, Client> client;
    public static volatile ListAttribute<Request, Goods> goods;
    public static volatile SingularAttribute<Request, Long> id;
    public static volatile SingularAttribute<Request, Date> creationDate;
    public static volatile SingularAttribute<Request, Boolean> status;

}