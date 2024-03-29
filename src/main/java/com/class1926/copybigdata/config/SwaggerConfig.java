package com.class1926.copybigdata.config;//swagger2的配置文件，在项目的启动类的同级文件建立
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * EnableSwagger2 :启动swagger
 * ComponentScan ：扫描包
 * Configuration ： 配置注解
 * @author yongz
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.class1926.copybigdata.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("就业大数据分析平台--后端API")
                //创建人
                .contact(new Contact("后端项目组", "http://192.168.1.157:8080", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
 
 
}