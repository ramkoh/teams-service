package com.edu.postgrad.game.teams.integration;

import java.time.LocalDate;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Position;
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
    public void createPlayerReturns(){
        final Player player  = getPlayerDto();
        assertNull(player.getId()) ;
        playerService.savePlayer(player);
        assertNotNull(player.getId());
    }

    private Player getPlayerDto(){
        Player player = new Player();
        player.setFirstName("Raman");
        player.setLastName("Kohli");
        player.setJerseyNumber(10);
        player.setPosition(Position.DEFENDER);
        player.setDob(LocalDate.of(1990,1,1));
        return player;
    }
}
