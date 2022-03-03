package com.site.blog.my.core.security.jwt;

import com.site.blog.my.core.entity.AdminUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private final Key KEY = Keys.hmacShaKeyFor(JwtContents.SECRET.getBytes(StandardCharsets.UTF_8));

    private final String USE_ID = "use_id";

    public String getUsernameForToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public String getToken(AdminUser adminUser) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put(USE_ID, adminUser.getAdminUserId());
        return generateToken(adminUser.getLoginUserName(), claims, 86400L);
    }

    public AdminUser getAdminUser(String token) {
        AdminUser adminUser = new AdminUser();
        Claims claims = getClaimsFromToken(token);
        adminUser.setAdminUserId(Integer.parseInt(claims.get(USE_ID).toString()));
        adminUser.setLoginUserName(claims.getSubject());
        return adminUser;
    }

    private Date expirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return JwtContents.TOKEN_HEAD + Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(JwtContents.SYSTEM_UUID)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(KEY, SIGNATURE_ALGORITHM)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        if(token.startsWith(JwtContents.TOKEN_HEAD)) {
            token = token.substring(JwtContents.TOKEN_HEAD.length());
        }
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
