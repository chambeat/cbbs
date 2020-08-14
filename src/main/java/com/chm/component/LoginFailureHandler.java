package com.chm.component;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义登录失败的提示信息
 */
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public LoginFailureHandler() {
        this.setDefaultFailureUrl("/index.html?error");
    }
}
