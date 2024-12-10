package com.example.road_control_system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.road_control_system.filters.JwtFilter;
import com.example.road_control_system.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    @Autowired
     private final JwtFilter jwtFilter;

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtAuthEntryPoint jwtAuthEntryPoint, CustomUserDetailsService customUserDetailsService, JwtFilter jwtFilter) {
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtFilter = jwtFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(jwtAuthEntryPoint))
                .sessionManagement(management -> management
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(
                                authz -> {
                                    authz
                                            .requestMatchers("/api/v1/users/login", "/api/v1/users/register").permitAll()
                                            .anyRequest().authenticated();
                                }
                        )
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); 
                // .formLogin(
                //         formLogin -> formLogin
                //                 .loginPage("/login")
                //                 .permitAll()
                // );                  
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();            
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;          
    }

    @Bean

    public AuthenticationProvider authentificationProvider() {
        DaoAuthenticationProvider authentificationProvider = new DaoAuthenticationProvider();
        authentificationProvider.setUserDetailsService(userDetailsService());
        authentificationProvider.setPasswordEncoder(passwordEncoder());
        return authentificationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();  // Provide an AuthenticationManager bean
    }
}
