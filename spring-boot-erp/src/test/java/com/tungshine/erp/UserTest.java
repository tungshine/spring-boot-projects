package com.tungshine.erp;

import com.tungshine.erp.service.UserService;
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
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {
        System.out.println(userService.getUser(1L));
    }
}
