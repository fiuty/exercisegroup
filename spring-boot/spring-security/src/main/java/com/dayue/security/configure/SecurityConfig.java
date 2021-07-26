package com.dayue.security.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author zhengdayue
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    //一、引入security默认是Basic auth基本认证，无需配置，不够灵活

    //二、基于内存的方式，调用 AuthenticationManagerBuilder的inMemoryAuthentication方法，也不够灵活
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("USER", "ADMIN");
    }

    //三、基于数据库的方式，调用jdbcAuthentication()方法，需建2个内置表
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from Users " + "where username=?")
//                .authoritiesByUsernameQuery("select username, authority from UserAuthorities " + "where username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

    //四、定制化用户认证方案
//    @Override
//    protected void configure(cc auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl);
//    }

    //授权，通过HttpSecurity对资源访问进行保护
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
