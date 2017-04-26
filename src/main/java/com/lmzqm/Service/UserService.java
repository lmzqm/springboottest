package com.lmzqm.Service;

import com.lmzqm.Model.Person;
import com.lmzqm.Model.User;

/**
 * Created by lmzqm on 2017/4/19.
 */
public interface UserService {


    void createUser(User user);

    void deleteUser(Integer id);

    Integer getAllUsers();

    void deleteAllUsers();

    Person login(String name,String password);
}
