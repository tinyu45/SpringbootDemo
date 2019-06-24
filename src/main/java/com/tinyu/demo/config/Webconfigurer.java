package com.tinyu.demo.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class Webconfigurer extends WebMvcConfigurerAdapter {
	//配置静态资源加载，如:html, js, css
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		super.addResourceHandlers(registry);
	}
	
	
	//注册拦截器，需要先写好拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
	}
}
