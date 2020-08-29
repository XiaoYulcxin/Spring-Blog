package com.lcx.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * created Lcxin
 * Web页面拦截器
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加要处理拦截的类
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")  //拦截掉除登陆页面/处理以外的所有后台页面
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
        super.addInterceptors(registry);
    }
}
