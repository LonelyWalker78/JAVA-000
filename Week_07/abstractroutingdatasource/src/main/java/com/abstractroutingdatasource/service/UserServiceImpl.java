package com.abstractroutingdatasource.service;

import com.abstractroutingdatasource.dataaccess.annotation.DataSource;
import com.abstractroutingdatasource.dataaccess.common.ContextConst;
import com.abstractroutingdatasource.pojo.User;
import com.abstractroutingdatasource.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> queryAll() {
        return userRepository.findAll();
    }

    @Override
    @DataSource(ContextConst.DataSourceType.MASTER)
    public User add(User user) {
        return userRepository.save(user);
    }
}
