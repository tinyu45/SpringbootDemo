package com.tinyu.demo.intercepors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆拦截
 * @author tinyu
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	//访问接口前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token=request.getHeader("token");
		if(token!=null && isValid(token))
			return true;
		return false;
	}

	/***
	 * 检验token是否有效
	 * @param token
	 * @return
	 */
	private boolean isValid(String token) {
		int len=token.length();
		if(len==32 && token.matches("^[a-z0-9]+$"))    //判断长度及格式
			return true;
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//访问接口后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
