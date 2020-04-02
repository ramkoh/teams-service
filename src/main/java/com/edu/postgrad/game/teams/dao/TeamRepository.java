package com.edu.postgrad.game.teams.dao;

import java.util.List;
import java.util.Optional;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t.players from Team t where t.id = ?1")
    List<Player> findPlayersById(Long teamId);

    Optional<Team> findTeamByName(String name);
}
