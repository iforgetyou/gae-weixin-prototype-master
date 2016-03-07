package com.zy17.dao.impl;

import com.zy17.dao.UserDao;
import com.zy17.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    public PersistenceManagerFactory persistenceManagerFactory;

//    @PersistenceContext
//    private EntityManager em;

    public User save(User user) {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            User userDb = pm.makePersistent(user);
            return userDb;
        } finally {
            pm.close();
        }
    }
}