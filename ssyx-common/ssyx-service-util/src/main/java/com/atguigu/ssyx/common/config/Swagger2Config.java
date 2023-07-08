package com.atguigu.ssyx.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-07 21:13
 **/
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    @Bean
    public Docket webApiConfig() {
        List<Parameter> params = new ArrayList<>();
        ParameterBuilder builder = new ParameterBuilder();
        builder.name("userId")
            .description("用户token")
            .defaultValue("1")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();
        params.add(builder.build());

        Docket webApi = new Docket(DocumentationType.SWAGGER_2)
            .groupName("webApi")
            .apiInfo(webApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.atguigu.ssyx"))
            .paths(PathSelectors.regex("/api/.*"))
            .build()
            .globalOperationParameters(params);
        return webApi;
    }

    @Bean
    public Docket adminApiConfig() {
        List<Parameter> params = new ArrayList<>();
        ParameterBuilder builder = new ParameterBuilder();
        builder.name("adminId")
            .description("用户token")
            .defaultValue("1")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();
        params.add(builder.build());

        Docket webApi = new Docket(DocumentationType.SWAGGER_2)
            .groupName("adminApi")
            .apiInfo(adminApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.atguigu.ssyx"))
            .paths(PathSelectors.regex("/admin/.*"))
            .build()
            .globalOperationParameters(params);
        return webApi;
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
            .title("网站-APi文档")
            .description("尚上优选微服务接口定义")
            .version("1.0")
            .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
            .title("后台管理系统-APi文档")
            .description("尚上优选后台管理系统接口定义")
            .version("1.0")
            .build();
    }

}
