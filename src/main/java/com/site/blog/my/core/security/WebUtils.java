package com.site.blog.my.core.security;

import com.site.blog.my.core.security.jwt.JwtContents;
import com.site.blog.my.core.util.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
public class WebUtils {

    public String getToken(HttpServletRequest request) {
        return Strings.isNotEmpty(request.getHeader(JwtContents.AUTH_TOKEN)) ?
                request.getHeader(JwtContents.AUTH_TOKEN) : getFromCookie(request, JwtContents.AUTH_TOKEN);
    }

    public String getFromCookie(HttpServletRequest request, String key) {
        if (key == null) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        return Arrays.stream(cookies).filter(cookie -> key.equals(cookie.getName())).findFirst().map(Cookie::getValue).orElse(null);
    }

}
