package com.tungshine.erp.shiro;

import com.tungshine.erp.mapper.PermissionMapper;
import com.tungshine.erp.mapper.RoleMapper;
import com.tungshine.erp.mapper.UserMapper;
import com.tungshine.erp.model.Permission;
import com.tungshine.erp.model.Role;
import com.tungshine.erp.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TungShine
 * @Description:获取用户的角色和权限信息
 * @Date: Create in 16:49 2018/5/4
 * @Modified By:
 */
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + token.toString());
        User user = userMapper.findByUsername(token.getUsername());
//        String md5Pwd = new Md5Hash("123", "shine", 2).toString();
        if (null != user) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            List<Role> roleList = roleMapper.findRoleByUserId(user.getId());//获取用户角色
            List<Permission> permissioinList = permissionMapper.findPermissionByUserId(user.getId());//获取用户权限
            List<String> roleNameList = new ArrayList<String>();////用户的角色集合
            List<String> permissionNameList = new ArrayList<String>();//用户的权限集合
            for (Role role : roleList) {
                roleNameList.add(role.getName());
            }
            for (Permission permission : permissioinList) {
                permissionNameList.add(permission.getName());
            }
            user.setRoleNameList(roleNameList);
            user.setPermissionNameList(permissionNameList);
//            Session session = SecurityUtils.getSubject().getSession();
//            session.setAttribute("user", hasUser);//成功则放入session
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }

        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
