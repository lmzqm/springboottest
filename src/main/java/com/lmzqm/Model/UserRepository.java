package com.lmzqm.Model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  首先这里使用的是JPA来针对数据进行处理
 *  首先通过：解析方法名创建查询
 *  然后通过@Query注解来查询，使用JPQL语句 并通过：name来映射@Param指定的参数
 *
 *  主要的接口：
 *      Repository:仅仅是一个标识，没有任何方法，方便Spring自动识别扫描
 *      CurdRepository:继承Repository,实现一组CRUD相关的方法
 *      PagingAndSortingRepository:继承CrudRepository,实现分组排序的相关方法
 *      JpaRepository:继承PagingAndSortingRepository,实现JPA规范的相关方法
 *
 *  基本查询：
 *      1.预先生成的
 *      通过继承的操作我们知道，Respostory提供了一些默认的操作
 *      findAll();
 *      findOne();
 *      save();
 *      delete();
 *      exists();
 *
 *   创建查询方法
 *      1.解析方法名
 *       框架在解析方法名的时候会将多余的前缀截取掉，比如find,findBy,read,readBy,get,getBy,然后对剩下的部分进行解析，
 *       如果方法的最后一个是Sort,Pageable类型，会提取相关的信息，以便按规则进行排序或者分页查询
 *
 *       And findByLastnameAndFirstName
 *       Or  findByLastnameOrFirstName
 *       Is,Equals  findByFirstName,findByFistnameIS,findByFirstnameEquals
 *       Between findByStartDataBetween
 *       LessThan findByAgeLessThan
 *       LessThanEqual
 *       GreaterThan
 *       GreaterThanEqual
 *       After findByStartDateAfter
 *       Before
 *       IsNull findByAgeIsNull
 *       IsNotNull, NotNull
 *       Like findByFirstnameLike
 *       NotLike
 *       StartingWith findByFirstnameStartWith
 *       EndingWith
 *       Containing findByFirstnameContaining
 *       OrderBy findByAgeOrderByLastnameDesc
 *       Not
 *       in findByAgeIn(collection)
 *       NotIn
 *       TRUE findByActiveTrue()
 *       FALSE
 *       IgnoreCase
 *
 *       复杂的查询：
 *          分页查询：
 *
 *          //分页拉去的操作
 *           int page = 1,size = 10;
 *          Sort sort = new Sort(Sort.Direction.DESC,"id");
 *           Pageable pageable = new PageRequest(page,size,sort);
 *           userRepository.findAll(pageable);
 *
 *           userRepository.findByUserName("testName",pageable);
 *
 *       限制查询：
 *          User findFirstByOrderByLastnameAsc()
 *          User findTopByOrderByAgeDesc()
 *          Page<User> queryFirst10ByLastname(String lastname,Pageable pageable);
 *          List<User> findFirst10ByLastname(String lastname,Sort sort);
 *
 *       自定义查询：
 *          使用@Query 注解来操作，如果在删除和修改的方法可以使用@Modifying也可以通过添加@Transactional对事物的支持
 *
 *
 *       多表查询：
 *          首先是创建一个结果集的接口来接受链表查询后的结果
 *
 *          public interface HotelSummary{
 *              City getCity();
 *              String getName();
 *              Double getAverageRating();
 *              default Integer getAverageRatingRounded(){
 *                  return getAverageRating() == null
 *              }
 *
 *
 *          }
 *
 *         @Query（"select h.city as city,h.name as name,avg(r.rating) as averageRating from Hotel h left outer join
 *          h.reviews r where h.city = ?1 group by h"）
 *          Page<HotelSummary> findByCity(City city,Pageable pageable);
 *
 *          @Query（"select h.name as name ,avg(r.rationg) as averageRating from Hotel h left outer join h.reviews r group by h"）
 *          Page<HotelSummary> findByCity(Pageable pageable);
 *
 *          Page<HotelSummary>
 *
 *
 *       异构数据库多源支持：
 *          实体类声明@Entity关系型数据库支持类型，声明为@Docuemnt为mongoDb支持类型，不同的数据源使用不同的实体就可以了
 *          如果需要两个一起使用过的话，可以使用
 *          @Entity
 *          @Document
 *
 *         使用枚举：
 *          使用枚举的时候，我们希望数据库中存储的是枚举对应的String类型，而不是枚举的索引值，需要在属性上添加@Enumerated(EnumType.STRING)注解
 *
 *          @Enumerated(EnumType.STRING)
 *          @Column(nullable=true)
 *          private UserType type;
 *
 *         不需要和数据库映射的属性
 *         只需要加上@Transient属性就不会加入到数据库中
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * */

/**
 * Created by lmzqm on 2017/4/20.
 */
//采用注解的方法 添加repository的操作
//@RepositoryDefinition(domainClass = User.class,idClass = Long.class)
public interface UserRepository extends JpaRepository<User,Integer> {

//    User findByUserName(String name);

    @Query(" select  '*' from  User u where u.username like ?1 and u.status = ?2 ")
    List<User> findByUsernameAndStatus(String name, Integer status);


//    @Query("select '*' from user u where  u.username like :username and u.status = :status")
//    User findBynameLikeAndStatusequal(@Param("username") String name,@Param("status") Integer status);

    //修改和删除 使用@Modifying
    @Modifying
    @Query("update User u set u.username=?1 where u.id = ?2")
    public int updateUsername(String username,Integer id);

    User findByEmainAddress(Integer id);

    //分页操作
    Page<User> findAll(Pageable pageable);

    Page<User> findByUsername(String username,Pageable pageable);

    //自定义sql操作
//    @Modifying
//    @Query("update user u set u.username = ?1 where u.id = ?2")
//    public int modifyByUsernameAndId(String username,Long id);

    @Transactional
    @Modifying
    @Query("delete FROM  User u where u.id = ?1")
    void deleteByUserId(Long id);

    @Transactional(timeout = 10)
    @Query("select  u from  User u where u.username = ?1")
    User findByUsername(String username);




    //





//    @Query(value = "From user where username=:name",nativeQuery = true)
//    User findUser(@Param("name") String name);

}
