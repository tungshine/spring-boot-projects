package com.tungshine.mongo.dao;

import com.tungshine.mongo.model.User;

/**
 * @ Author: TungShine
 * @ Description:
 * @ Date: Create in 1:25 2018/7/19
 * @ Modified By:
 */
public interface UserDao {
    User getUser(Integer id);

    void insertUser(User user);
}
