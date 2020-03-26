package com.edu.postgrad.game.teams.unit;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.rest.PlayerController;
import com.edu.postgrad.game.teams.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class PlayerControllerStandaloneTest {
    private MockMvc mvc;

    PlayerService playerService = mock(PlayerService.class);
    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    public void setup() {

         MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        playerController.setPlayerService(playerService);
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(playerController)
                .setValidator(validator())
               // .setViewResolvers(viewResolver())
                    .build();
    }
    private LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(ThymeleafViewResolver.class);
        viewResolver.setPrefix("/templates");
        viewResolver.setSuffix(".html");

        return viewResolver;
    }
    @Test
    public void canCreateANewSuperHero() throws Exception {
        Player player = new PlayerBuilder()
                .build();
        String body =  asJsonString(player);
        System.out.print(body);
        MockHttpServletResponse response = mvc.perform(
                post("/player").contentType(MediaType.APPLICATION_JSON)
                        .param("player", body)
                        .content(
                   body
                )).andReturn().getResponse();

        assert response.getStatus() == HttpStatus.CREATED.value();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
