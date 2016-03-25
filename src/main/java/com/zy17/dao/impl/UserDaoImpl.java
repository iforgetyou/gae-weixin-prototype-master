package com.zy17.dao.impl;

import org.springframework.stereotype.Repository;

import com.zy17.dao.UserDao;
import com.zy17.entity.UserEntity;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao {
    @Override
    public void saveUser(UserEntity userEntity) {
        super.save(userEntity);
    }

    //    @PersistenceContext
    //    private EntityManager em;

}