package com.chm.config;

import com.chm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* 登录 */
        http.formLogin().loginPage("/index.html");

        /* 资源授权规则，权限由低到高分配如下所示 */
        http.authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/", "/main.html", "/user/pwd", "/booking", "/booking/*", "/FAQ", "/noticeBoard").hasAnyRole("user", "admin")
                .antMatchers("/toBooking", "/feedback.html").hasRole("user")
                .antMatchers("/bookings", "/users", "/users/*", "/user", "/defaultBookings", "/successBookings", "/failBookings", "/feedbacks", "/toNotice", "/notice", "/notice/*").hasRole("admin");

        /* 关闭防跨站请求伪造的功能，因为它可能会造成退出失败(系统对安全性要求不高，干脆关闭)。 */
        http.csrf().disable();

        /* 退出 */
        http.logout().logoutSuccessUrl("/");
    }
}
