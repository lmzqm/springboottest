package com.lmzqm.Controller;

import com.lmzqm.Model.Doctor;
import com.lmzqm.Property.BlogProperty;
import com.lmzqm.Repository.DoctorRepository;
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

    @RequestMapping(value = "/blog")
    public BlogProperty getBlogInfo(){

        blogProperty.setName("Hello world");
        return blogProperty;
    }


}
