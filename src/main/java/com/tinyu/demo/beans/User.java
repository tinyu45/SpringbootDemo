package com.tinyu.demo.beans;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

public class User {
	@NotBlank(message = "用户名不能为空")
	@Size(min=2, max=30, message = "姓名长度必须在2-30之间") 
	@Pattern(regexp = "^((?!@\\\"\\\\p{P}\\\").)*$", message = "不能包含除的标点符号")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	@Size(min=6, max=18, message = "密码长度必须在6-18之间")
	@Pattern(regexp = "^((?!@\\\"\\\\p{P}\\\").)*$", message = "不能包含除的标点符号")
	private String password;
	
	@Min(value = 10,message = "年龄最小为10")
	@Max(value = 100,message = "年龄最大为100") 
	private Integer age;
	
	@Future 
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") 
	private Date birth;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	
}
