package com.tungshine.mongo.test;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tungshine.mongo.dao.UserDao;
import com.tungshine.mongo.model.User;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 1:51 2018/7/19
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setId(2);
        user.setName("不言");
        user.setAge(28);
        userDao.save(user);
    }

    @Test
    public void testGetUser() {
        System.out.println(userDao.findById(1));
    }

    @Test
    public void testFindAndOperation() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        // map.put("id", 1);
        map.put("name", "不言");
        // map.put("age", 28);
        List<User> list = userDao.findOne(map);
        for (User user : list) {
            System.out.println(user);
        }
    }
}
