package com.tungshine.mongo.service.impl;

import com.tungshine.mongo.dao.UserDao;
import com.tungshine.mongo.model.User;
import com.tungshine.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 1:18 2018/7/19
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }
}
