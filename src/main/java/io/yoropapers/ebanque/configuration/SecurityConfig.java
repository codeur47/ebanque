package io.yoropapers.ebanque.configuration;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import io.yoropapers.ebanque.service.serviceImpl.UserSecurityServiceImpl;

/**
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private static final String SALT = "salt";
    

    private UserSecurityServiceImpl userSecurityServiceImpl;

    @Autowired
    public SecurityConfig(UserSecurityServiceImpl userSecurityServiceImpl){
        this.userSecurityServiceImpl = userSecurityServiceImpl;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    private static final String[] PUBLIC_MATCHERS = {
        "/bundles/**",
        "/css/**",
        "/fonts/**",
        "/images/**",
        "/js/**",
        "/vendor/**",
        "/",
        "/login",
        "/page-forgot-password",
        "/page-register"
    };

    private static final String[] SECURE_URLS = {
        "/dashboard/**",
        "/page-lockscreen/**",
        "/addUser/**",
        "/userList/**",
        "/addEmployee/**",
        "/listEmployee/**",
        "/collectiveScore/**",
        "/individualScore/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated()
            .and()
            .csrf().disable().cors().disable().formLogin().failureUrl("/login?error").defaultSuccessUrl("/dashboard").loginPage("/login").permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityServiceImpl).passwordEncoder(passwordEncoder());
    }
    
}