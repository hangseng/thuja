package com.thuja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: Ansen
 * @create: 2022-01-09 11:16
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    /**
     * @Description: 创建API基本信息
     * @param :
     * @return springfox.documentation.spring.web.plugins.Docket
     * @Author: Ansen
     * @Date: 2022/1/9
     */
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .apis(RequestHandlerSelectors.basePackage("com.ansen.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @Description: 创建API的基本信息，这些信息会在Swagger UI中进行显示
     * @param :
     * @return springfox.documentation.service.ApiInfo
     * @Author: Ansen
     * @Date: 2022/1/9
     */
    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // API 标题
                .title("系统接口文档")
                // API描述
                .description("提供系统相关接口")
                // 联系人
                .contact("com.ansen")
                // 版本号
                .version("1.0.0.1")
                .build();
    }


}
