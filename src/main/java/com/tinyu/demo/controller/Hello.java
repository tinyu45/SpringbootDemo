package com.tinyu.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@RestController
public class Hello {
	/**
	 * 测试一  输出
	 * @return
	 */
	@ApiOperation(value = "测试", notes = "测试swagger功能")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String say() {
		return "hello world!";
	}
	
	/*
	 * 测试二  传参
	 */
	@ApiOperation(value = "接受", notes = "输出对话内容")
	@ApiImplicitParam(name="title", value = "对话标题", dataType = "string", required = true)
	@RequestMapping(value = "listen", method = RequestMethod.POST)
	public String listen(String title) {
		return title;
	}

	@ApiOperation(value = "登录", notes = "系统登陆")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "用户名", dataType = "string", paramType="query"),
		@ApiImplicitParam(name = "password", value = "密    码", dataType = "string", paramType="query")
	})
	@RequestMapping(value = "login", method = {RequestMethod.POST,RequestMethod.GET})
	public String login(HttpServletRequest req) {
		String pwd=req.getParameter("password");
		String uname=req.getParameter("username");
		return uname+","+pwd;
	}
		
}
