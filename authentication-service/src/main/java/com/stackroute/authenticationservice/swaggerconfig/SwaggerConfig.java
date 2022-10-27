package com.stackroute.authenticationservice.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;



@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(myPaths()).build();
    }

    private Predicate<String> myPaths() {
        return regex("/api.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Simple API")
                .description("Simple API reference for developers")
                .termsOfServiceUrl("https://simple.com")
                .contact("azhar@gmail.com").license("Simple License")
                .licenseUrl("azhar@gmail.com").version("1.0").build();
    }

    /*public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }*/

}
