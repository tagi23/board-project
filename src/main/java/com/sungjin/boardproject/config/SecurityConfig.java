package com.sungjin.boardproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {   //스프링부트 2.7에 대응하는 security 코드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth->auth.anyRequest().permitAll())  //auth의 어떤 request든 다 열겠다.
                .formLogin().and()
                .build();
    }

}
