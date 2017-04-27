package com.lmzqm.Controller;

import com.lmzqm.Model.ErrorInfo;
import com.lmzqm.Service.MailServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lmzqm on 2017/4/27.
 */
@RestController
@RequestMapping(value = "mail")
public class MailViewController {


//    @Autowired
//    private  JavaMailSender mailSender;



    @Autowired
    private MailServer mailServer;

    @RequestMapping(value = "/text")
    public ErrorInfo<String> sendTextEmail(@RequestParam("email") String email) throws Exception{

       ErrorInfo<String> info =  mailServer.sendMail(email);

       return info;

    }
}
