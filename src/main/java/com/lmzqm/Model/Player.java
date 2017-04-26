package com.lmzqm.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lmzqm on 2017/4/21.
 */
@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
