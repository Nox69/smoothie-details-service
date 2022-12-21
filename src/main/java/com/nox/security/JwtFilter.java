package com.nox.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import com.nox.exception.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/*
 * This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean. Override the doFilter method with
 * ServletRequest, ServletResponse and FilterChain. This is used to authorize the API access for the application.
 */

public class JwtFilter extends GenericFilterBean {

    /*
     * Override the doFilter method of GenericFilterBean. Retrieve the "authorization" header from the HttpServletRequest object. Retrieve the
     * "Bearer" token from "authorization" header. If authorization header is invalid, throw Exception with message. Parse the JWT token and get
     * claims from the token using the secret key Set the request attribute with the retrieved claims Call FilterChain object's doFilter() method
     */

    protected static final String SECRET_KEY = "smoothie-service"; // TODO to make it more dynamic

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletReq = (HttpServletRequest) request;
        String authenticateHeader = servletReq.getHeader("authorization");
        if ("OPTIONS".equals(servletReq.getMethod())) {
            chain.doFilter(request, response);
        } else {
            if (authenticateHeader == null || !(authenticateHeader.startsWith("Bearer "))) {
                throw new UnAuthorizedException("Authentication HEADER provided is missing or INVALID");
            }
            final String token = authenticateHeader.substring(7);
            final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            servletReq.setAttribute("claims", claims);
            chain.doFilter(request, response);
        }
    }

}
