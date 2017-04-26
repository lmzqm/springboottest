package com.lmzqm.Model;

import javax.persistence.*;

/**
 * Created by lmzqm on 2017/4/21.
 */

/**
 *  关于jpa注解的使用
 *
 *  @Entity 将领域对象标注为一个实体，需要保存到数据库中
 *  @Table @Table(name="table_name") 用name来指定对应的表
 *  @Id 表示对应的字段是表的主键
 * */
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;



}
