package com.edu.postgrad.game.teams.unit;

import java.time.LocalDate;
import java.util.List;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.rest.PlayerController;
import com.edu.postgrad.game.teams.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


public class PlayerControllerTest {
    private MockMvc mvc;

    PlayerService playerService = mock(PlayerService.class);
    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        playerController.setPlayerService(playerService);
        mvc = MockMvcBuilders.standaloneSetup(playerController)
                .build();
    }

    @Test
    public void canCreatePlayer() throws Exception {
        Player player = new PlayerBuilder()
                .build();
        String body = asJsonString(player);
        System.out.print(body);
        when(playerService.savePlayer(player)).thenReturn(1L);
        mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(body))
                .andExpect(model().attribute("player", hasProperty("firstName", Matchers.is(player.getFirstName()))))
                .andExpect(model().attribute("player", hasProperty("lastName", Matchers.is(player.getLastName()))))
                .andExpect(model().attribute("player", hasProperty("dob", Matchers.is(player.getDob()))))
                .andExpect(model().attribute("player", hasProperty("jerseyNumber", Matchers.is(player.getJerseyNumber()))))
                .andExpect(model().attribute("player", hasProperty("position", Matchers.is(player.getPosition()))))
                .andExpect(view().name("players/add-player"))
                .andExpect(status().isCreated());

    }

    @Test
    public void nullFirstNameThrowsException() throws Exception {
        Player player = new PlayerBuilder()
                .withFirstName(null)
                .build();
        String body = asJsonString(player);
        when(playerService.savePlayer(player)).thenReturn(1L);
        mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("player", "firstName", "NotNull"))
                .andExpect(view().name("players/add-player"));

    }

    @Test
    public void nullLastNameThrowsException() throws Exception {
        Player player = new PlayerBuilder()
                .withLastName(null)
                .build();
        String body = asJsonString(player);
        when(playerService.savePlayer(player)).thenReturn(1L);
        mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("player", "lastName", "NotNull"))
                .andExpect(view().name("players/add-player"));

    }

    @Test
    public void firstNameSmallerThan5CharsThrowsException() throws Exception {
        Player player = new PlayerBuilder()
                .withFirstName("ABCD")
                .build();
        String body = asJsonString(player);
        when(playerService.savePlayer(player)).thenReturn(1L);
        mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("player", "firstName", "Size"))
                .andExpect(view().name("players/add-player"));

    }

    @Test
    public void lastNameSmallerThan5CharsThrowsException() throws Exception {
        Player player = new PlayerBuilder()
                .withLastName("ABCD")
                .build();
        String body = asJsonString(player);
        when(playerService.savePlayer(player)).thenReturn(1L);
        mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("player", "lastName", "Size"))
                .andExpect(view().name("players/add-player"));

    }

    @Test
    public void negativeJerseyNumberThrowsException() throws Exception {
        Player player = new PlayerBuilder()
                .withJerseyNumber(-10)
                .build();
        String body = asJsonString(player);
        when(playerService.savePlayer(player)).thenReturn(1L);
        mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("player", "jerseyNumber", "Positive"))
                .andExpect(view().name("players/add-player"));

    }

    @Test
    public void futureDOBThrowsException() throws Exception {
        Player player = new PlayerBuilder()
                .withDob(LocalDate.of(2021, 1, 1))
                .build();
        String body = asJsonString(player);
        when(playerService.savePlayer(player)).thenReturn(1L);
        mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("player", "dob", "Past"))
                .andExpect(view().name("players/add-player"));

    }

    @Test
    public void nullDOBThrowsException() throws Exception {
        Player player = new PlayerBuilder()
                .withDob(null)
                .build();
        String body = asJsonString(player);
        when(playerService.savePlayer(player)).thenReturn(1L);
        mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("player", "dob", "NotNull"))
                .andExpect(view().name("players/add-player"));

    }

    @Test
    public void canGetAllPlayers() throws Exception {

        List<Player> players = Lists.newArrayList(new PlayerBuilder().build());
        when(playerService.getAllPlayers()).thenReturn(players);

        mvc.perform(
                get("/players"))
                .andExpect(model().attribute("players",
                        Matchers.hasItem(hasProperty("firstName", Matchers.is(players.get(0).getFirstName())))))
                .andExpect(model().attribute("players",
                        Matchers.hasItem(hasProperty("lastName", Matchers.is(players.get(0).getLastName())))))
                .andExpect(model().attribute("players",
                        Matchers.hasItem(hasProperty("jerseyNumber", Matchers.is(players.get(0).getJerseyNumber())))))
                .andExpect(model().attribute("source", Matchers.is("players")))
                .andExpect(view().name("players/view-players"))
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
