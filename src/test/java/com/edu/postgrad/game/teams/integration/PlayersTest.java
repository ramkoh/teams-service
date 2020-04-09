package com.edu.postgrad.game.teams.integration;


import java.util.List;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.service.PlayerService;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlayersTest {

    @Autowired
    PlayerService playerService;

    @Test
    public void testCreatePlayer(){
        final Player player  = new PlayerBuilder().build();
        assertNull(player.getId()) ;
        playerService.savePlayer(player);
        assertNotNull(player.getId());
    }

    @Test
    public void testGetAllPlayers(){
        final List<Player> players = playerService.getAllPlayers();
        assertNotNull(players);
        assertThat(players, (Every.everyItem(HasPropertyWithValue.hasProperty("id", Matchers.notNullValue()))));
        assertThat(players, (Every.everyItem(HasPropertyWithValue.hasProperty("firstName", Matchers.notNullValue()))));
        assertThat(players, (Every.everyItem(HasPropertyWithValue.hasProperty("lastName", Matchers.notNullValue()))));
        assertThat(players, (Every.everyItem(HasPropertyWithValue.hasProperty("dob", Matchers.notNullValue()))));
        assertThat(players, (Every.everyItem(HasPropertyWithValue.hasProperty("age", Matchers.notNullValue()))));
        assertThat(players, (Every.everyItem(HasPropertyWithValue.hasProperty("position", Matchers.notNullValue()))));
    }

}
