package com.edu.postgrad.game.teams.unit;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.dto.TeamBuilder;
import com.edu.postgrad.game.teams.exception.PlayerException;
import com.edu.postgrad.game.teams.exception.TeamException;
import com.edu.postgrad.game.teams.rest.AddPlayerToTeamController;
import com.edu.postgrad.game.teams.service.PlayerService;
import com.edu.postgrad.game.teams.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.gl.E;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddPlayerToTeamControllerTest {

    private MockMvc mvc;

    PlayerService playerService = mock(PlayerService.class);
    TeamService teamService = mock(TeamService.class);

    @InjectMocks
    private AddPlayerToTeamController addPlayerToTeamController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        addPlayerToTeamController.setPlayerService(playerService);
        addPlayerToTeamController.setTeamService(teamService);
        mvc = MockMvcBuilders.standaloneSetup(addPlayerToTeamController)
                .build();
    }

    @Test
    public void testAddPlayerToTeam() throws Exception {
        Team expectedTeam = new TeamBuilder().build();
        expectedTeam.setId(1L);
        String body = asJsonString(expectedTeam);

        Player expectedPlayer = new PlayerBuilder().build();

        when(teamService.findById(1L)).thenReturn(expectedTeam);
        when(playerService.findPlayer(1L)).thenReturn(expectedPlayer);
        when(teamService.saveTeam(expectedTeam)).thenReturn(1L);
        mvc.perform(
                post("/test/addPlayerToTeam/1").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(model().attribute("player", hasProperty("firstName", Matchers.is(expectedPlayer.getFirstName()))))
                .andExpect(model().attribute("player", hasProperty("lastName", Matchers.is(expectedPlayer.getLastName()))))
                .andExpect(model().attribute("team", hasProperty("name",  Matchers.is(expectedTeam.getName()))))
                .andExpect(status().isOk());

    }

    @Test
    public void unknownPlayerThrowsException() throws Exception{
        Team expectedTeam = new TeamBuilder().build();
        expectedTeam.setId(1L);
        String body = asJsonString(expectedTeam);

        when(teamService.findById(1L)).thenReturn(expectedTeam);
        when(playerService.findPlayer(1L)).thenThrow(PlayerException.class);
        mvc.perform(
                post("/test/addPlayerToTeam/1").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void unknownTeamThrowsException() throws Exception {
        Team expectedTeam = new TeamBuilder().build();
        expectedTeam.setId(1L);
        String body = asJsonString(expectedTeam);

        when(teamService.findById(1L)).thenThrow(TeamException.class);
        mvc.perform(
                post("/test/addPlayerToTeam/1").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getShowForm() throws Exception{
        mvc.perform( get("/player/1/teams") )
               .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
