package com.edu.postgrad.game.teams.unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.dto.TeamBuilder;
import com.edu.postgrad.game.teams.rest.TeamController;
import com.edu.postgrad.game.teams.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Ignore;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Ignore
public class TeamsControllerTest {
    private MockMvc mvc;

    TeamService teamService = mock(TeamService.class);
    @InjectMocks
    private TeamController teamController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        teamController.setPlayerService(teamService);
        mvc = MockMvcBuilders.standaloneSetup(teamController)
                .build();
    }

    @Test
    public void canCreatePlayer() throws Exception {
        Team team = new TeamBuilder()
                .build();
        String body = asJsonString(team);

        when(teamService.saveTeam(team)).thenReturn(1L);
        mvc.perform(
                post("/test/team").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(model().attribute("team", hasProperty("name", Matchers.is(team.getName()))))
                .andExpect(model().attribute("team", hasProperty("code", Matchers.is(team.getCode()))))
                .andExpect(view().name("teams/add-team"))
                .andExpect(status().isCreated());

    }

    @Test
    public void nullNameThrowsException() throws Exception {
        Team team = new TeamBuilder()
                .withName(null)
                .build();
        String body = asJsonString(team);
        mvc.perform(
                post("/team").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("team", "name", "NotNull"))
                .andExpect(view().name("teams/add-team"));

    }

    @Test
    public void nullCodeThrowsException() throws Exception {
        Team team = new TeamBuilder()
                .withCode(null)
                .build();
        String body = asJsonString(team);
        mvc.perform(
                post("/team").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("team", "code", "NotNull"))
                .andExpect(view().name("teams/add-team"));

    }

    @Test
    public void codeLessThanMinSizeThrowsException() throws Exception {
        Team team = new TeamBuilder()
                .withCode("AB")
                .build();
        String body = asJsonString(team);
        mvc.perform(
                post("/team").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("team", "code", "Size"))
                .andExpect(view().name("teams/add-team"));

    }

    @Test
    public void codeMoreThanMaxSizeThrowsException() throws Exception {
        Team team = new TeamBuilder()
                .withCode("ABCD")
                .build();
        String body = asJsonString(team);
        mvc.perform(
                post("/team").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("team", "code", "Size"))
                .andExpect(view().name("teams/add-team"));

    }

    @Test
    public void teamWithMoreThanMaxPlayersThrowsException() throws Exception {
        List<Player> players = new ArrayList<>();
        Collections.nCopies(12, 1)
                .stream().forEach(i -> players.add(new PlayerBuilder().build()));
        Team team = new TeamBuilder()
                .withPlayers(players)
                .build();
        String body = asJsonString(team);
        mvc.perform(
                post("/team").contentType(MediaType.APPLICATION_JSON)
                        .param("team", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("team", "players", "Size"))
                .andExpect(view().name("teams/add-team"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
