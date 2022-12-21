package com.nox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.nox.security.JwtFilter;

@SpringBootApplication
public class SmoothieServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmoothieServiceApplication.class, args);
    }

    /*
     * Define the bean for Filter registration. Create a new FilterRegistrationBean object and use setFilter() method to set new instance of JwtFilter
     * object. Also specifies the Url patterns for registration bean.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/v1/api/*");
        return filterRegistrationBean;
    }

}
