package com.tinyu.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tinyu.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot-demo")
                .description("学习使用springboot及swagger文档生成")
                .termsOfServiceUrl("http://www.tinyu.cn")
                .contact(new Contact("孤风", "远影国际", "gufeng@tinyu.cn"))//作者
                .version("1.0")
                .build();
    }
    /**
    @SuppressWarnings("unchecked")
       @Bean
       public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                   .groupName("common")
                   .genericModelSubstitutes(DeferredResult.class)
//                   .genericModelSubstitutes(ResponseEntity.class)
                   .useDefaultResponseMessages(false)
                   .forCodeGeneration(true)
                   .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                   .select()
                   .paths(or(regex("/common/.*")))//过滤的接口
                   .build()
                   .apiInfo(commonApiInfo());
    }
    
    private ApiInfo commonApiInfo() {
        return new ApiInfoBuilder()
                .title("common页面API")//大标题
                .description("springboot平台的REST API")//详细描述
                .version("1.0")//版本
                .contact(new Contact("lyon", "", ""))//作者
                .build();
    }
    **/
}
