package com.nox.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JWTService {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_SEPERATOR = " ";

    public String getAuthenticatedCustomer() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return getUsernameFromToken(Arrays.asList(request.getHeader(AUTHORIZATION_HEADER).split(TOKEN_SEPERATOR)).get(1));
        }
        return null;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JwtFilter.SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
