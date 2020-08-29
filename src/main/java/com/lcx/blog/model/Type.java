package com.lcx.blog.model;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created Lcxin
 * 分类
 **/
@Entity
@Table(name = "tab_type") //生成其对应的表名（如不指定，默认生成实体类名）
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //修改默认数据库生成自增主键策略
    private Long id;
    //设置数据表的name值不能为空（后端验证）
    @NotBlank(message = "分类字段不能为空")
    private String name;

    //外键关联
    //一对多模式:同一个类别下，可以对应多篇同类型的博客
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Type() {
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
