package com.lcx.blog.service;

import com.lcx.blog.model.User;

public interface UserService {
    User checkUser(String username, String password);
}
