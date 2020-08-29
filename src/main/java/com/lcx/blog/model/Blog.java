package com.lcx.blog.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created Lcxin
 * 博客详情页实体类
 **/

//对应生成数据表的索引
@Entity
@Table(name = "tab_blog")   //生成其对应的表名（如不指定，默认生成实体类名）
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    // 初始化时候：生成大数据类型
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content; //博客内容
    private String firstPicture; //首图
    private String flag; //标记
    private Integer views; //浏览次数
    private boolean appreciation; //赞赏是否开启
    private boolean shareStatement; //转载声明是否开启
    private boolean commentated; //评论是否开启
    private boolean published; //是否发布
    private boolean recommend; //是否推荐
    @Transient //不会关联数据库
    private String tagIds;
    @Temporal(TemporalType.TIMESTAMP) //在数据表中生成一个以年月日格式的时间
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    //外键关联
    //多对一
    @ManyToOne
    private Type type;
    //多对多:设置发布新博客时，同时新增tag标签，级联新增
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    //多篇博客只能由一名用户管理
    @ManyToOne
    private User user;
    //一篇博客可以有多条comment评论
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    private String description; //博客描述

    public Type getType() {
        return type;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentated() {
        return commentated;
    }

    public void setCommentated(boolean commentated) {
        this.commentated = commentated;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //初始化对象
    public void init(){
        this.tagIds = tagToIds(this.getTags());
    }

    //1,2,3
    private String tagToIds(List<Tag> list) {
        if (!list.isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            boolean flag = false;
            for (Tag tag : list) {
                if (flag) {
                    buffer.append(",");
                } else {
                    flag = true;
                }
                buffer.append(tag.getId());
            }
            //错误问题所在：遍历数据表中遍历出来了
            return buffer.toString();
        } else {
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentated=" + commentated +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", description=" + description +
                '}';
    }
}
