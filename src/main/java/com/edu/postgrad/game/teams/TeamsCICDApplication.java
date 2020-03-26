package com.edu.postgrad.game.teams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.edu.postgrad"})
public class TeamsCICDApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TeamsCICDApplication.class, args);
	}

}

