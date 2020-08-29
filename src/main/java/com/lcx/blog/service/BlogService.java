package com.lcx.blog.service;

import com.lcx.blog.model.Blog;
import com.lcx.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlogs(Pageable pageable, BlogQuery blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Blog blog, Long id);

    void deleteBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId ,Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable); //搜索,分页查询

    List<Blog> listRecommendBlogTop(Integer size); //推荐

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();
}
