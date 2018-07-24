package com.tungshine.mongo.dao.impl;

import com.tungshine.mongo.dao.UserDao;
import com.tungshine.mongo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @ Author: TungShine
 * @ Description:
 * @ Date: Create in 1:26 2018/7/19
 * @ Modified By:
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User getUser(Integer id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), User.class);
    }

    @Override
    public void insertUser(User user) {
        mongoTemplate.save(user);
    }
}
