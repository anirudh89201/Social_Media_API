package com.rest_service.REST_API.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //All requests must be authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        //If a request is not authenticated,a web page is shown
        http.httpBasic(Customizer.withDefaults());
        //CSRF -> Post,PUT
        http.csrf().disable();
        return http.build();
    }
}
