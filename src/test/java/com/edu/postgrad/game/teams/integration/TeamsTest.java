package com.edu.postgrad.game.teams.integration;

import java.util.List;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.dto.TeamBuilder;
import com.edu.postgrad.game.teams.service.TeamService;
import gherkin.lexer.Pl;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeamsTest {

    @Autowired
    TeamService teamService;

    @Test
    public void testCreateTeam() {
        Team team = new TeamBuilder().build();

        //team instance is not saved to db yet, property-Id must be null
        Assertions.assertNull(team.getId());
        teamService.saveTeam(team);

        //team instance is saved to db,Id must not be null
        Assertions.assertNotNull(team.getId());
    }

    @Test
    public void testGetAllTeams() {
        final List<Team> teams = teamService.getAllTeams();

        assertNotNull(teams);
        assertThat(teams, (Every.everyItem(HasPropertyWithValue.hasProperty("id", Matchers.notNullValue()))));
        assertThat(teams, (Every.everyItem(HasPropertyWithValue.hasProperty("name", Matchers.notNullValue()))));
        assertThat(teams, (Every.everyItem(HasPropertyWithValue.hasProperty("code", Matchers.notNullValue()))));
    }

    @Test
    public void addPlayerToTeamTest() {
        Team team = new TeamBuilder().build();
        Long teamId = teamService.saveTeam(team);

        //By default, no players are created in team
        assertThat(team.getPlayers(), is(empty()));

        final Player player = new PlayerBuilder().build();
        team = teamService.findById(teamId);

        //Add player to team and save
        team.addPlayer(player);
        teamId = teamService.saveTeam(team);

        //Get team
        team = teamService.findById(teamId);

        //Players must not be null
        Assertions.assertNotNull(team.getPlayers());
        Assertions.assertEquals(1, team.getPlayers().size());
        Player actualPlayer = team.getPlayers().get(0);
        Assertions.assertEquals(player.getFirstName(), actualPlayer.getFirstName());
        Assertions.assertEquals(player.getLastName(), actualPlayer.getLastName());
    }

    @Test
    public void getPlayersOfTeam() {
        Team team = new TeamBuilder().build();
        final Player player = new PlayerBuilder().build();
        team.addPlayer(player);

        final Player player2 = new PlayerBuilder().withFirstName("Raman").withLastName("Kohli").build();
        team.addPlayer(player2);

        final Long teamId = teamService.saveTeam(team);

        final List<Player> actualPlayers = teamService.getPlayersOfTeam(teamId);

        Assertions.assertEquals(2, actualPlayers.size());
    }

}
