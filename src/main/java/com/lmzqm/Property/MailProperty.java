package com.lmzqm.Property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * mail.host=smtp.qq.com
 mail.username=1056532328@qq.com
 mail.password=zqm2lm
 mail.properties.mail.smtp.auth=true
 mail.properties.mail.smtp.starttls.enable=true
 mail.properties.mail.smtp.starttls.required=true
 * Created by lmzqm on 2017/4/27.
 */
@ConfigurationProperties(prefix = "mail")
@Component
public class MailProperty {


    private String host;


    private String username;

    private String password;

    public MailProperty(){

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    @Override
    public String toString() {
        return "MailProperty{" +
                "host='" + host + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
