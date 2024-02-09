package com.example.kun_uz_lesson1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication
        String password = UUID.randomUUID().toString();
        System.out.println("User Pasword mazgi: " + password);

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}" + password)
                .roles("USER")
                .build();

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));
        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorization
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/register/**").permitAll()
                    .requestMatchers("/verification/**").permitAll()
                    .requestMatchers("/init/admin").permitAll()
                    .requestMatchers("/init/**").permitAll()
//                    .requestMatchers(HttpMethod.GET,"/region/getByLang").permitAll()
                    .requestMatchers("/region/getByLang").permitAll()
                    .requestMatchers("/region/adm/**").hasRole("ADMIN")
                    .requestMatchers("/profile","/profile/*").hasRole("ADMIN")
                    .requestMatchers("/profile/adm","/profile/adm/*").hasAnyRole("ADMIN")
                    .anyRequest()
                    .authenticated();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        return http.build();
    }


}
