package com.zy17.dao.impl;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zy17.dao.UserDao;
import com.zy17.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    //    @PersistenceContext
    //    private EntityManager em;

    public User save(User user) {
        User save = super.save(user);
        return save;
    }
}