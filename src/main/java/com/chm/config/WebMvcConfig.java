package com.chm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /* 添加视图控制 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main/index");
        registry.addViewController("/index.html").setViewName("main/login");
        registry.addViewController("/main.html").setViewName("main/index");
        registry.addViewController("/feedback.html").setViewName("main/feedback");
        registry.addViewController("/gotoList").setViewName("main/goToListPage");
        registry.addViewController("/gotoMain").setViewName("main/goToMainPage");
        registry.addViewController("/gotoLogin").setViewName("main/goToLoginPage");
        registry.addViewController("/afterEmail").setViewName("main/goToListAfterEmail");
        registry.addViewController("/FAQ").setViewName("main/FAQ");//FAQ(常见问题)
        registry.addViewController("/gotoNoticeBoard").setViewName("main/goToNoticeBoard");
    }

    /* 设置静态资源缓存 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(31556926); //缓存有效期：一年
    }
}
