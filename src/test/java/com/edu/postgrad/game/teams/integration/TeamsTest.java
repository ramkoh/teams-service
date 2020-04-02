package com.edu.postgrad.game.teams.integration;

import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.dto.TeamBuilder;
import com.edu.postgrad.game.teams.service.TeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeamsTest {

    @Autowired
    TeamService teamService;

    @Test
    public void testCreateTeam(){
        Team team = new TeamBuilder().build();
        Assertions.assertNull(team.getId());
        teamService.saveTeam(team);
        Assertions.assertNotNull(team.getId());
    }
}
