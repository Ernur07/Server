package com.mycompany.server.Entity;

import com.mycompany.server.Entity.Request;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T16:20:39")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> name;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile ListAttribute<Client, Request> requests;
    public static volatile SingularAttribute<Client, String> key;
    public static volatile SingularAttribute<Client, Boolean> status;

}