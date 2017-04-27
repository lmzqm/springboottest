package com.lmzqm.Service;

import com.lmzqm.Model.ErrorInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


/**
 * Created by lmzqm on 2017/4/27.
 */
@Service
public class MailServer {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private JavaMailSender mailSender;

    public ErrorInfo<String> sendMail(String email) throws Exception{

        logger.info("sendMail to "+ email);

        ErrorInfo<String> info = new ErrorInfo<>();
        info.setMessage("send email to " + email);

        SimpleMailMessage message = new SimpleMailMessage();

        if(email == null || email.equals("")){
            email = "1056532328@qq.com";
        }

        message.setFrom("1056532328@qq.com");
        message.setTo(email);
        message.setSubject("主题：简答邮件");
        message.setText("测试邮件内容");
        try {
            mailSender.send(message);
            info.setCode(ErrorInfo.OK);
            logger.info("send eamil to "+ email + "successful");
        }catch (Exception e){
            info.setCode(ErrorInfo.ERROR);
            logger.error("send email to " + email + "fail");
        }


        return info;
    }
}
