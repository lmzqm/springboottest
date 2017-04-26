package com.lmzqm.Model;

import com.sun.javafx.scene.layout.region.Margins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.Serializable;

//public class RedisObjectSerializer implements RedisSerializer<Object>{
//
//
//
//}

public class RedisObjectSerializer implements RedisSerializer<Object> {

 static final  byte[] EMPTY_ARRAY = new byte[0];

 private Converter<Object,byte[]> serializer = new SerializingConverter();
 private Converter<byte[],Object> deserializer = new DeserializingConverter();

 @Override
 public byte[] serialize(Object obj) throws SerializationException {

  if(obj == null){
    return  EMPTY_ARRAY;
  }

  try {

   return serializer.convert(obj);

  }catch (Exception e){
     return EMPTY_ARRAY;
  }

 }

 @Override
 public Object deserialize(byte[] bytes) throws SerializationException {

  if(isEmpry(bytes)){
   return null;
  }

  try {
   return deserializer.convert(bytes);
  }catch (Exception e){
   throw  new SerializationException("cannot deserialize ",e);
  }

 }

 private boolean isEmpry(byte[] data){
  return (data == null || data.length == 0);
 }
}

/**
 * Created by lmzqm on 2017/4/24.
 */

/**
 *  不知道什么原因，导致导入失败 暂时现在这里做下笔记
 *
 *  加入依赖
 *  <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-data-redis</artifactId>
 </dependency>
 *
 *  首先序列化对象
 *
 *  public class User implements Serializable {

 private static final long serialVersionUID = -1L;

 private String username;
 private Integer age;

 public User(String username, Integer age) {
 this.username = username;
 this.age = age;
 }

 // 省略getter和setter

 }

 然后实现对象的序列化接口

 public class RedisObjectSerializer implements RedisSerializer<Object> {

 private Converter<Object, byte[]> serializer = new SerializingConverter();
 private Converter<byte[], Object> deserializer = new DeserializingConverter();

 static final byte[] EMPTY_ARRAY = new byte[0];

 public Object deserialize(byte[] bytes) {
 if (isEmpty(bytes)) {
 return null;
 }

 try {
 return deserializer.convert(bytes);
 } catch (Exception ex) {
 throw new SerializationException("Cannot deserialize", ex);
 }
 }

 public byte[] serialize(Object object) {
 if (object == null) {
 return EMPTY_ARRAY;
 }

 try {
 return serializer.convert(object);
 } catch (Exception ex) {
 return EMPTY_ARRAY;
 }
 }

 private boolean isEmpty(byte[] data) {
 return (data == null || data.length == 0);
 }
 }

 *
 *
 * 配置针对User的RedisTemplate的实例
 *
 * @Configuration
public class RedisConfig {

@Bean
JedisConnectionFactory jedisConnectionFactory() {
return new JedisConnectionFactory();
}

@Bean
public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
RedisTemplate<String, User> template = new RedisTemplate<String, User>();
template.setConnectionFactory(jedisConnectionFactory());
template.setKeySerializer(new StringRedisSerializer());
template.setValueSerializer(new RedisObjectSerializer());
return template;
}


}


完成后测试工作，编写用例

 @RunWith(SpringJUnit4ClassRunner.class)
 @SpringApplicationConfiguration(Application.class)
 public class ApplicationTests {

 @Autowired
 private RedisTemplate<String, User> redisTemplate;

 @Test
 public void test() throws Exception {

 // 保存对象
 User user = new User("超人", 20);
 redisTemplate.opsForValue().set(user.getUsername(), user);

 user = new User("蝙蝠侠", 30);
 redisTemplate.opsForValue().set(user.getUsername(), user);

 user = new User("蜘蛛侠", 40);
 redisTemplate.opsForValue().set(user.getUsername(), user);

 Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
 Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
 Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

 }

 }

 *
 * */


