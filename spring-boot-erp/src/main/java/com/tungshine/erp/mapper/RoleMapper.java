package com.tungshine.erp.mapper;

import com.tungshine.erp.model.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 17:30 2018/5/4
 * @Modified By:
 */
public interface RoleMapper {
    @Select("SELECT r.id, r.name FROM t_user_role ur LEFT JOIN t_role r ON r.id = ur.rid WHERE r.id = #{id}")
    List<Role> findRoleByUserId(Long id);
}
