package com.edu.postgrad.game.teams.rest;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.service.PlayerService;
import com.edu.postgrad.game.teams.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AddPlayerToTeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    PlayerService playerService;

    @GetMapping("/player/{id}/teams")
    public String showForm(final Model model, @PathVariable final Long id){
        Player player = playerService.findPlayer(id);
        model.addAttribute("player", player);
        Iterable<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("team", new Team());
        return "players/add-player-to-team";
    }

    @PostMapping("/addPlayerToTeam/{playerId}")
    public String addPlayerToTeam(Team team, final Model model, @PathVariable final Long playerId, HttpServletResponse response) {
        Team existingTeam = teamService.findById(team.getId());

        Player player = playerService.findPlayer(playerId);
        existingTeam.addPlayer(player);

        teamService.saveTeam(existingTeam);
        model.addAttribute("message", String.format("Player %s added to team %s successfully", player.getFirstName(),
                existingTeam.getName()));
        model.addAttribute("player", player);
        model.addAttribute("team", existingTeam);
        model.addAttribute("teams", teamService.getAllTeams());
        response.setStatus(HttpServletResponse.SC_OK);
        return "players/add-player-to-team";
    }

    @PostMapping("/test/addPlayerToTeam/{playerId}")
    public String addPlayerTest(@RequestBody Team team, Model model,  @PathVariable final Long playerId, HttpServletResponse response) {
        return addPlayerToTeam(team, model, playerId, response);
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

}
