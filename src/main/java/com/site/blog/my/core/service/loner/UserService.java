package com.site.blog.my.core.service.loner;

import com.site.blog.my.core.entity.AdminUser;

public interface UserService {

    String getToken(AdminUser adminUser);

    AdminUser getUser(AdminUser adminUser);

}
