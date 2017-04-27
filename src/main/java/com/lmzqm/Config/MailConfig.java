package com.lmzqm.Config;

import com.lmzqm.Property.MailProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by lmzqm on 2017/4/27.
 */
@Configuration
public class MailConfig {

    @Autowired
    private MailProperty property;

    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost(property.getHost());
        javaMailSender.setUsername(property.getUsername());
        javaMailSender.setPassword(property.getPassword());
        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties(){
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.starttls.enable","true");
        properties.setProperty("mail.debug","false");
        return properties;
    }
}
