package com.shardingsphere.service;

import com.shardingsphere.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();

    User add(User user);
}
