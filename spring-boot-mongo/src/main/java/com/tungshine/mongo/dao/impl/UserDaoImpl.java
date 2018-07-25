package com.tungshine.mongo.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.tungshine.mongo.core.BaseDaoImpl;
import com.tungshine.mongo.dao.UserDao;
import com.tungshine.mongo.model.User;

/**
 * @Author: TungShine
 * @Description:
 * @Date: 2018年7月25日
 * @Modified By:
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Serializable> implements UserDao {

}
