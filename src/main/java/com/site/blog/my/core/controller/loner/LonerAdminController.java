package com.site.blog.my.core.controller.loner;

import com.site.blog.my.core.controller.response.ResponseHandler;
import com.site.blog.my.core.entity.AdminUser;
import com.site.blog.my.core.security.jwt.JwtUtils;
import com.site.blog.my.core.service.AdminUserService;
import com.site.blog.my.core.service.loner.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/loner")
public class LonerAdminController {

    @Resource
    UserService userService;

    @PostMapping(value = "/login")
    public ResponseHandler login(@RequestBody AdminUser adminUser) {
        try{
            return new ResponseHandler().setToken(userService.getToken(adminUser));
        } catch (UsernameNotFoundException usernameNotFoundException) {
            System.out.println("用户名或者密码错误：" + usernameNotFoundException.getMessage());
        }
        return new ResponseHandler().setStatus(HttpStatus.FORBIDDEN).setMessage("用户名或密码错误");
    }

    @GetMapping(value = "/use")
    public ResponseHandler getUserDetailById(@RequestBody AdminUser adminUser) {
        return new ResponseHandler().setBean(userService.getUser(adminUser));
    }


}
