package com.zy17.dao.impl;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;
import com.zy17.dao.UserDao;
import com.zy17.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public void saveUser(User user) {
        super.save(user);
    }

    //    @PersistenceContext
    //    private EntityManager em;

}