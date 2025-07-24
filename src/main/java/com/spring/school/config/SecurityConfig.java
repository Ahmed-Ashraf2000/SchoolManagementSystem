package com.spring.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/logout").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dashboard").authenticated()
                        .requestMatchers(HttpMethod.GET, "/displayMessages").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/closeMsg/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/assets/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        from -> from.loginPage("/login").loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/dashboard")
                                .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .logoutRequestMatcher(
                                request -> request.getMethod().equals("GET") &&
                                           request.getRequestURI()
                                                   .equals(request.getContextPath() + "/logout"))
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"))
                .httpBasic(Customizer.withDefaults())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers(
                        "/saveMsg").ignoringRequestMatchers("/public/createUser"));

        return http.build();
    }

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_name, password, active FROM users WHERE user_name = ?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.user_name, CONCAT('ROLE_', r.role_name) as authority " +
                "FROM users u " +
                "JOIN user_roles ur ON u.user_id = ur.user_id " +
                "JOIN roles r ON ur.role_id = r.role_id " +
                "WHERE u.user_name = ?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
