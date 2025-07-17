package com.spring.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/home", "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/contact").permitAll()
                        .requestMatchers(HttpMethod.GET, "/holidays/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/saveMsg").permitAll()
                        .requestMatchers(HttpMethod.GET, "/courses").permitAll()
                        .requestMatchers(HttpMethod.GET, "/about").permitAll()
                        .requestMatchers(HttpMethod.GET, "/assets/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        from -> from.loginPage("/login").loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/dashboard")
                                .permitAll())
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"))
                .httpBasic(Customizer.withDefaults())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers(
                        "/saveMsg"));

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user =
                User.withUsername("user").password("{noop}12345").roles("USER").build();

        UserDetails admin =
                User.withUsername("admin").password("{noop}12345").roles("USER", "ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
