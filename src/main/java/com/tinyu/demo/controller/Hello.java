package com.tinyu.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
@RestController
public class Hello {
	@ApiOperation(value = "测试", notes = "测试swagger功能")
	@RequestMapping(value = "/")
	public String say() {
		return "hello world!";
	}
}
