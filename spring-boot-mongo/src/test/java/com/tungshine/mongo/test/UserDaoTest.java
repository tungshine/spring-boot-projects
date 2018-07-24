package com.tungshine.mongo.test;

import com.tungshine.mongo.dao.UserDao;
import com.tungshine.mongo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ Author: TungShine
 * @ Description:
 * @ Date: Create in 1:51 2018/7/19
 * @ Modified By:
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
        userDao.insertUser(user);
    }

    @Test
    public void testGetUser() {
        System.out.println(userDao.getUser(1));
    }
}
