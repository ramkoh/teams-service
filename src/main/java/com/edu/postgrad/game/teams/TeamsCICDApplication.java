package com.edu.postgrad.game.teams;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan({"com.edu.postgrad.game.teams.dto"})
public class TeamsCICDApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TeamsCICDApplication.class, args);
	}

}

