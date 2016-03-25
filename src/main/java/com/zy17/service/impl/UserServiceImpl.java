package com.zy17.service.impl;

import com.zy17.dao.UserDao;
import com.zy17.entity.UserEntity;
import com.zy17.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void createUser(UserEntity userEntity) {
        // 调用持久层，完成数据的保存
        userDao.saveUser(userEntity);
    }
}