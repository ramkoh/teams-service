package com.edu.postgrad.game.teams.rest;

import java.util.List;

import com.edu.postgrad.game.teams.dao.PlayerRepository;
import com.edu.postgrad.game.teams.dao.TeamRepository;
import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.exception.PlayerException;
import com.edu.postgrad.game.teams.exception.TeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/player/{id}/teams")
    public String getAllTeamsForPlayer(final Model model, @PathVariable final Long id){
        Player player = playerRepository.findById(id).get();
        model.addAttribute("player", player);
        Iterable<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        model.addAttribute("team", new Team());
        return "players/add-player-to-team";
    }

    @PostMapping("/addPlayerToTeam/{playerId}")
    public String addPlayerToTeam1(Team team, final Model model, @PathVariable final Long playerId) {
        Team existingTeam = teamRepository.findById(team.getId()).orElseThrow(TeamException::new);

        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerException(""));
        existingTeam.addPlayer(player);

        teamRepository.save(existingTeam);
        model.addAttribute("message", String.format("Player %s added to team %s successfully", player.getFirstName(),
                existingTeam.getName()));
        return "players/add-player-to-team";
    }
}
