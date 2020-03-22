package com.edu.postgrad.game.teams.service;

import java.time.LocalDate;
import java.time.Period;

import com.edu.postgrad.game.teams.dao.PlayerRepository;
import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.exception.InvalidPlayerException;
import com.edu.postgrad.game.teams.exception.PlayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Long savePlayer(Player player){
        if(player == null){
            throw new PlayerException("Player cannot be null!");
        }
        if(player.getFirstName().equalsIgnoreCase(player.getLastName())){
            throw new InvalidPlayerException("First name cannot be same as last name.");
        }
        LocalDate now = LocalDate.now();
        final int ageOfPlayer = Period.between(player.getDob(), now).getYears();
        if(ageOfPlayer < 12){
            throw new InvalidPlayerException("Players younger than 12 are not allowed to play");
        }
        player = playerRepository.save(player);
        return player.getId();
    }

    public void setPlayerRepository(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
}
