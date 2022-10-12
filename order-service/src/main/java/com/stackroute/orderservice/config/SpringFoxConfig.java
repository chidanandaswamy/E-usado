package com.stackroute.orderservice.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@Configuration
@EnableSwagger2
public class SpringFoxConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.stackroute.controller")).build();
//    }


    // public ApiInfo apiInfo(){
    //     return new ApiInfoBuilder().title("Order Service")
    //             .description("REST API documentation for order service.")
    //             .version("1.0.0")
    //             .build();
    // }

    /** This URL is used for Swagger
     * http://localhost:8087/swagger-ui.html
     * */

}

