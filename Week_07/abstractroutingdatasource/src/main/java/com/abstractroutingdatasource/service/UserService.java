package com.abstractroutingdatasource.service;

import com.abstractroutingdatasource.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();

    User add(User user);
}
