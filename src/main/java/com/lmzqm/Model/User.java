package com.lmzqm.Model;

import org.hibernate.annotations.NamedQuery;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lmzqm on 2017/4/18.
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findByEmainAddress",query = "select u from User u where u.id = ?1")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;//用户名
    private String password;//密码
    private String phone;
    private Integer status;
    private Date createData;

    public User(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
