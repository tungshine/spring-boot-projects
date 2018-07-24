package com.tungshine.erp.mapper;

import com.tungshine.erp.model.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 17:31 2018/5/4
 * @Modified By:
 */
public interface PermissionMapper {

    @Select("SELECT p.id, p.name,p.url FROM t_user_role ur LEFT JOIN t_role_permission rp ON ur.rid = rp.rid LEFT JOIN t_permission p ON p.id = rp.pid WHERE ur.uid = #{0}")
    List<Permission> findPermissionByUserId(Long id);
}
