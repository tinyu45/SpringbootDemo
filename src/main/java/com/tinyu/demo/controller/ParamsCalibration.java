package com.tinyu.demo.controller;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tinyu.demo.beans.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "参数校验相关")
@Controller
public class ParamsCalibration {
	
	/***
	 * 在方法参数上使用 参数校验注解
	 * @param name
	 * @param password
	 * @param age
	 * @param birth
	 * @return
	 */
	@RequestMapping(value = "adduser1", method = RequestMethod.GET)
	@ApiOperation(value = "方法参数", notes = "方法参数上校验")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "用户名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "password", value = "密码", dataType = "string", paramType = "query", format = "password"),
		@ApiImplicitParam(name="age", value = "年龄", dataType = "integer", paramType = "query"),
		@ApiImplicitParam(name = "date", value = "日期", dataType="date", paramType = "query")
		})
	public String adduser1(
			@NotBlank(message = "用户名不能为空")
			@Size(min=2, max=30, message = "姓名长度必须在2-30之间") 
			//@Pattern(regexp = "^((?!@\\\"\\\\p{P}\\\").)*$", message = "不能包含除的标点符号")
			String name,
			
			@NotBlank(message = "密码不能为空")
			@Size(min=6, max=18, message = "密码长度必须在6-18之间")
			//@Pattern(regexp = "^((?!@\\\"\\\\p{P}\\\").)*$", message = "不能包含除的标点符号")
			String password,
			
			@Min(value = 10,message = "年龄最小为10")
			@Max(value = 100,message = "年龄最大为100") 
			Integer age,
			
            @Future 
			@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
			@JsonFormat(pattern="yyyy-MM") 
			Date birth
			) {
		return "参数校验通过";
	}
	
	
	@PostMapping("adduser2")
	@ApiOperation(value = "方法参数", notes = "方法参数上校验")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "用户名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "password", value = "密码", dataType = "string", paramType = "query", format = "password"),
		@ApiImplicitParam(name="age", value = "年龄", dataType = "integer", paramType = "query"),
		@ApiImplicitParam(name="date", value = "日期")
	})
	public String adduser2(@Valid @RequestBody User user) {
		return "验证通过";
	}
	
	
	
}
