package com.zy17.service.impl;

import com.zy17.dao.UserDao;
import com.zy17.dao.impl.UserDaoImpl;
import com.zy17.model.User;
import com.zy17.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User createUser(User user) {
        // 调用持久层，完成数据的保存
        return userDao.save(user);
    }
}