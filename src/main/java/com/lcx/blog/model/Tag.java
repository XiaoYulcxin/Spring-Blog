package com.lcx.blog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created Lcxin
 * 标签
 **/
@Entity
@Table(name = "tab_tag") //生成其对应的表名（如不指定，默认生成实体类名）
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //外键关联
    //多篇博客里面可以同时使用相同的标签,因此使用多对多模式
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

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
}
