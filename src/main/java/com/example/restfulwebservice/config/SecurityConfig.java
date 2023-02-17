package com.example.restfulwebservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        // form login 해제
        // httpSecurity.formLogin().disable();

//        httpSecurity.authorizeHttpRequests()
//                .requestMatchers("/**").hasRole("USER")
//                .requestMatchers("/h2-console/**").permitAll();

        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/**").permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() throws Exception {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("username")
                .password("test123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
