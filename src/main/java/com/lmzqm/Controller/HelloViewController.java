package com.lmzqm.Controller;

import com.lmzqm.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmzqm on 2017/3/27.
 */

/**
*  这里需要对SPringMVC的注解进行详解
 *
 *
* */
@RestController
@RequestMapping("/users")
public class HelloViewController {




    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Map<String,Object>  getAllUser(){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","lmzqm");
        map.put("pwd","123456");
        map.put("age",20);

        if(map.containsKey("name")) {
              map.replace("name","llllll");

        }

        return  map;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public void addUser(@ModelAttribute User user){





    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable long id){



        return new User();

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  String  putUser(@PathVariable Long id,@ModelAttribute User user){
        return "success";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id){

        return "success";
    }
}
