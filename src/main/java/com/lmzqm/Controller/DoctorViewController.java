package com.lmzqm.Controller;

import com.lmzqm.Model.Doctor;
import com.lmzqm.Property.BlogProperty;
import com.lmzqm.Property.MailProperty;
import com.lmzqm.Repository.DoctorRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.utilities.Assert;

/**
 * Created by lmzqm on 2017/4/25.
 */
@RestController
@RequestMapping(value = "doctor")
public class DoctorViewController {

    @Autowired
    private RedisTemplate<String ,Doctor> redisTemplate ;

    @Autowired
    private DoctorRepository doctorRepository;

    private  static final Logger logger = LogManager.getLogger(DoctorViewController.class);

    @RequestMapping(value = "/{id}")
    public Doctor getDoctorInfo(@PathVariable Long id){
        Doctor doctor = new Doctor();
        doctor.setId( 1743887349593489L );
        doctor.setAge(23);
        doctor.setName("jdjdjdjd");

        doctorRepository.save(new Doctor(1L,"name",12));

        redisTemplate.opsForValue().set(doctor.getName(),doctor);



        return doctor;
    }

    @Autowired
    private BlogProperty blogProperty;

    @Autowired
    private MailProperty mailProperty;

    @RequestMapping(value = "/blog")
    public MailProperty getBlogInfo(){

        logger.info("getBlogInfo requst start 开始请求啦！");
        logger.debug("hello wrold");
        logger.error("这是一个错误的发生");

        blogProperty.setName("中国");
        return mailProperty;
    }


}
