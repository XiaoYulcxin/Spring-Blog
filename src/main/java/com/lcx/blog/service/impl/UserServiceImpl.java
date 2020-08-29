package com.lcx.blog.service.impl;

import com.lcx.blog.dao.UserRepository;
import com.lcx.blog.model.User;
import com.lcx.blog.service.UserService;
import com.lcx.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created Lcxin
 * 登录验证
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, MD5Utils.stringToMD5(password));
    }
}
