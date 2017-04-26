package com.lmzqm.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lmzqm on 2017/4/20.
 */
@Entity
public class Person  {

    @Id
    private String id;

    private  String  firstname;

    private  String lastname;

//    private Addrss address;


}
