package com.lmzqm.Web.Controller.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lmzqm on 2017/4/19.
 */

@Controller
@RequestMapping(value = "/web")
public class LoginController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String loginAction(){
        return "login";
    }

}
