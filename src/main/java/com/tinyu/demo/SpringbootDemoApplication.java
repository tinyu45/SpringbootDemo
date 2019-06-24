package com.tinyu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//指定主类启动时扫描包 【默认仅仅扫描主类所在包及其子包】@component标注
@SpringBootApplication(scanBasePackages={"com.tinyu.demo.**"})   
@EnableSwagger2
public class SpringbootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
