package com.lmzqm.Repository;

import com.lmzqm.Model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 这里是针对MongoDb的相关操作，只是简单的，后续将花费更大的精力来进行完善操作
 *
 * 首先 添加依赖
 * <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-data-mongodb</artifactId>
 </dependency>

 实现User的数据访问对象：UserRepository


 * Created by lmzqm on 2017/4/25.
 */
public interface DoctorRepository extends MongoRepository<Doctor,Long> {

    Doctor findByname(String name);
}
