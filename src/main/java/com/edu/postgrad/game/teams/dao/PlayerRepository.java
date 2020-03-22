package com.edu.postgrad.game.teams.dao;

import java.util.List;

import com.edu.postgrad.game.teams.dto.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
