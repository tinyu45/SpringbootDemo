package com.tinyu.demo.controller;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinyu.demo.config.ErrorCode;
import com.tinyu.demo.dao.DataBaseDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "数据库相关")
@RequestMapping(value = "/user")
@RestController
public class SqlRelated {
	@GetMapping(value = "/show")
	@ApiOperation(value = "查看用户", notes = "查看所有用户信息")
	public String showUser() {
		JSONArray jarr;
		try {
			jarr=DataBaseDao.showUser();
			return ErrorCode.ok(jarr);
		} catch (Exception e) {
			return ErrorCode.err(ErrorCode.SYSTEM_ERR, e.toString());
		}
		
	}
}
