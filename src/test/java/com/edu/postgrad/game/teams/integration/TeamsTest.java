package com.edu.postgrad.game.teams.integration;

import java.util.List;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.dto.TeamBuilder;
import com.edu.postgrad.game.teams.service.TeamService;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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

    @Test
    public void testGetAllTeams(){
        final List<Team> teams = teamService.getAllTeams();
        assertNotNull(teams);
        assertThat(teams, (Every.everyItem(HasPropertyWithValue.hasProperty("id", Matchers.notNullValue()))));
        assertThat(teams, (Every.everyItem(HasPropertyWithValue.hasProperty("name", Matchers.notNullValue()))));
        assertThat(teams, (Every.everyItem(HasPropertyWithValue.hasProperty("code", Matchers.notNullValue()))));
    }
}
