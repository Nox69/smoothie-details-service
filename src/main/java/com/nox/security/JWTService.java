package com.nox.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JWTService {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_SEPERATOR = " ";

    public Map<String, String> getAuthenticatedCustomer() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Map<String, String> user = new HashMap<>();
            user.put("userId", getUsernameFromToken(Arrays.asList(request.getHeader(AUTHORIZATION_HEADER).split(TOKEN_SEPERATOR)).get(1)));
            user.put("role", getRoleFromToken(Arrays.asList(request.getHeader(AUTHORIZATION_HEADER).split(TOKEN_SEPERATOR)).get(1)));
            return user;
        }
        return null;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JwtFilter.SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JwtFilter.SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.containsKey("role") ? claims.get("role").toString() : null;
    }

}
