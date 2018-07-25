package com.tungshine.mongo.service;

import com.tungshine.mongo.model.User;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 1:17 2018/7/19
 * @Modified By:
 */
public interface UserService {

    User getUser(Integer id);

    void addUser(User user);
}
