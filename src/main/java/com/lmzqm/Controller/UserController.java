package com.lmzqm.Controller;

import com.lmzqm.Model.User;
import com.lmzqm.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lmzqm on 2017/4/20.
 */
@RestController
@RequestMapping(value = "/getUser")
public class UserController {

    @Autowired
    private UserRepository userRepository;




    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public User createUser(@ModelAttribute User user,@PathVariable(value = "id") String id){

        User tmpuser = userRepository.save(user);


//        userRepository.findOne(id);
//        userRepository.delete(id);
//        userRepository.exists(id);
//        userRepository.count();
//        userRepository.getOne();
//        userRepository.
        return  tmpuser;

    }

    @RequestMapping(value = "/id",method = RequestMethod.POST)
    public User getUser(@RequestParam(value = "id" ,required = true) String id, HttpServletRequest request){
        System.out.println("getuser request with id"+id);
       Integer userid = Integer.valueOf(id);
       User user =  userRepository.getOne(userid);
       return user;


    }

    /**
     *  @RequestParam 如果value指定的不是提交上来的，将会报错
     *  通过Request来确定该参数是否一定要传入
     *
     *  但是针对链表的操作的时候
     *
     *  @RequestParam(value="username" ) Stirng[] userNames
     *  @RequestParam(value="list") List<String> list
     *
     *
     *  @PathVariable通过绑定占位符参数到方法中 例如：
     *  @PathVariable("userid") Long userId;
     *
     */


}
