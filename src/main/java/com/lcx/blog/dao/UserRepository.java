package com.lcx.blog.dao;

import com.lcx.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring JPA关联数据库参数，自动根据条件进行查询
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
