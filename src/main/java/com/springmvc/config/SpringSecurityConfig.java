package com.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthenticationSuccess authenticationSuccess;

    @Autowired
    LogoutSuccess logoutSuccess;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("nitin")
                .password("pass")
                .roles("ADMIN")
                .and().withUser("user").password("pass").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().successHandler(authenticationSuccess).loginPage("/login").permitAll()
                .loginProcessingUrl("/loginUrl")
                .and()
                .logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/doLogout", "GET")).logoutSuccessHandler(logoutSuccess)
                .and().csrf().disable();
    }
}
