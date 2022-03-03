package com.site.blog.my.core.security.jwt;

import com.site.blog.my.core.entity.AdminUser;
import com.site.blog.my.core.security.WebUtils;
import com.site.blog.my.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebFilter(filterName = "JwtTokenFilter", urlPatterns = "/loner/*")
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    WebUtils webUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String auth_token = webUtils.getToken(request);
        String userName = jwtUtils.getUsernameForToken(auth_token);
        if (Strings.isNotEmpty(userName)) {
            AdminUser adminUser = jwtUtils.getAdminUser(auth_token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminUser, null, getAuthorities(adminUser));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            return ;
        }
        filterChain.doFilter(request, response);
    }

    public Collection<? extends GrantedAuthority> getAuthorities(AdminUser adminUser) {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(adminUser.getLoginUserName()));
        return collection;
    }
}
