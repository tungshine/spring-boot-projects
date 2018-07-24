package com.tungshine.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 16:30 2018/5/8
 * @Modified By:
 */
@Configuration
@EnableSwagger2
public class APIDocSwagger2Config {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.tungshine")).paths(PathSelectors.any()).build();
    }

    private ApiInfo buildApiInfo() {
        String title = "Spring Boot 中使用Swagger2 UI构建API文档";
        return new ApiInfoBuilder().title(title).description("erp API文档").contact(new Contact("tungshine", "http://www.baidu.com", "tangxushine@163.com")).version("1.0").build();
    }
}
