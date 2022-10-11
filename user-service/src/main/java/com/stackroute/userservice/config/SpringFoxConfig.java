package com.stackroute.userservice.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.stackroute.user-service"))
//                .paths(PathSelectors.regex("/api/v1/.*"))
//                .build()
//                .apiInfo(apiInfo());
        return new Docket(DocumentationType.SWAGGER_2);

    }

//    private ApiInfo apiInfo() {
//        ApiInfo apiInfo = new ApiInfo(
//                "e-usado",
//                "An application to sell your used electronics",
//                "e-usado v1",
//                "Terms of service",
//                "eusado@gmail.com",
//                "License of API",
//                "https://swagger.io/docs/");
//        return apiInfo;
//    }
}