package com.site.blog.my.core.service.loner.impl;

import com.site.blog.my.core.dao.AdminUserMapper;
import com.site.blog.my.core.entity.AdminUser;
import com.site.blog.my.core.security.jwt.JwtUtils;
import com.site.blog.my.core.service.loner.UserService;
import com.site.blog.my.core.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IUserService implements UserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public String getToken(AdminUser adminUser) {

        String passwordMd5 = MD5Util.MD5Encode(adminUser.getLoginPassword(), "UTF-8");

        AdminUser user = adminUserMapper.login(adminUser.getLoginUserName(), passwordMd5);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在或密码错误");
        }

        return jwtUtils.getToken(user);
    }

    @Override
    public AdminUser getUser(AdminUser adminUser) {
        return adminUserMapper.selectByPrimaryKey(adminUser.getAdminUserId());
    }

}
