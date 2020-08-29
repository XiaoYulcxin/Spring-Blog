package com.lcx.blog.service;


import com.lcx.blog.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    void saveComment(Comment comment);
}
