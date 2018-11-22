package com.tungshine.erp.service;

import com.github.pagehelper.PageHelper;
import com.tungshine.erp.mapper.UserMapper;
import com.tungshine.erp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 17:37 2018/5/3
 * @Modified By:
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据主键查询用户
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#id", cacheNames = "user")
    public User getUser(Long id) {
        return userMapper.findById(id);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
//    @CachePut(key = "#user.id", cacheNames = "user")
    public int addUser(User user) {
        System.out.println("------------" + user.getId());
        return userMapper.insertUser(user);
    }

    public User findByName(String username) {
        return userMapper.findByUsername(username);
    }

//    @Cacheable(key = "#currentPage", cacheNames = "user_list")
    public List<User> findUserByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<User> users = userMapper.getUserList();
        return users;
    }
}
