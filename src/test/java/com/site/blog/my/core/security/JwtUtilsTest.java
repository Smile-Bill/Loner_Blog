package com.site.blog.my.core.security;

import com.site.blog.my.core.entity.AdminUser;
import com.site.blog.my.core.security.jwt.JwtUtils;
import org.junit.Before;
import org.junit.Test;

public class JwtUtilsTest {

    private String token = "Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkRFRiJ9.eJxNjEEKgCAUBe_y1wpa39TO0B1C8Qm2qMiCILp7LlvOMMxDV8VcEo1aUL0ijTRtKw4StJyl0ZAToo4sGa6T7LsgnXZZWgtEnxgc0OISzrYY2PRWGa0E4d7_4v0AO0Ucgw.VjFSZXgakhJ7u7s6jcNzzmWHJJxnCc_ycVuduSfw724";

    private JwtUtils jwtUtils;

    @Before
    public void before() {
        jwtUtils = new JwtUtils();
    }

    @Test
    public void testGetToken() {
        AdminUser adminUser = new AdminUser();
        adminUser.setAdminUserId(1);
        adminUser.setLoginUserName("Loner");
//        System.out.println(jwtUtils.getToken(adminUser));
    }

    @Test
    public void testGetUsername() {
//        String userName = jwtUtils.getUsernameForToken(token);
//        System.out.println("userName = " + userName);
    }

}
