package com.edu.postgrad.game.teams;

import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@SpringBootApplication
@ComponentScan({"com.edu.postgrad"})
public class TeamsCICDApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TeamsCICDApplication.class, args);
	}

	/*@Bean
	public ThymeleafViewResolver viewResolver(SpringTemplateEngine templateEngine){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setContentType("application/json");
		viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		viewResolver.setOrder(1);
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}*/

}

