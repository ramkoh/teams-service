package com.edu.postgrad.game.teams.integration;


import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.service.PlayerService;
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
}
