package com.example.kun_uz_lesson1.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {
//    @Autowired
//    private TokenFilter tokenFilter;
//    @Bean
//    public FilterRegistrationBean<Filter> filterRegistrationBean() {
//        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(tokenFilter);
//        bean.addUrlPatterns("/region/adm");
//        bean.addUrlPatterns("/region/adm/*");
//
//        bean.addUrlPatterns("/email/adm");
//        bean.addUrlPatterns("/email/adm/*");
//
//        bean.addUrlPatterns("/article/mod");
//        bean.addUrlPatterns("/article/mod/*");
//
//        bean.addUrlPatterns("/article/pub");
//        bean.addUrlPatterns("/article/pub/*");
//
//        return bean;
//    }

}
