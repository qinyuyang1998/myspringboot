package com.sinosoft.configuration.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //开始Swagger2
public class SwaggerConfig {
	
	//配置了Swagger的Docket的bean实例
	@Bean
	public Docket docket(Environment environment){
		
		boolean flag = environment.acceptsProfiles("dev","test");
		
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
		.enable(flag)
		.select()
        .apis(RequestHandlerSelectors.basePackage("com.sinosoft.myspringboot.controller"))
        .build();
	}
	
	//配置Swagger信息apiInfo
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
        .title("Swagger API文档")
        .version("1.0")
        .build();
	}
}
