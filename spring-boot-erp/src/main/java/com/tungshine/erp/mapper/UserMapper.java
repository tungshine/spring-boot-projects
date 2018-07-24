package com.tungshine.erp.mapper;

import com.tungshine.erp.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 17:14 2018/5/3
 * @Modified By:
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO t_user(name) VALUES(#{name})")
    int insertUser(User user);

    /**
     * 根据主键查询用户
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User findById(@Param("id") Long id);

    @Select("SELECT * FROM t_user WHERE NAME = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM t_user")
    List<User> getUserList();

    @Select("SELECT * FROM t_user")
    List<User> userPage();

    @Select("SELECT COUNT(*) FROM t_user")
    long countNums();
}
