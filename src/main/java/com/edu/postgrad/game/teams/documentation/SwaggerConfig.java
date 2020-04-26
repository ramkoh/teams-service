package com.edu.postgrad.game.teams.documentation;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final Contact CUSTOM_CONTACT = new Contact("Raman", "http://my-url.com",
            "raman.kohli@student.ait.it");
    public static final ApiInfo APP_INFO = new ApiInfo("match-service",
            "This is documentation of match-service", "v1",
            "http://terms-of-service-url.com", CUSTOM_CONTACT,
            "License", "http://license-url.com", Lists.newArrayList());

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(APP_INFO);
    }
}
