package com.edu.postgrad.game.teams.rest;

import java.util.List;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.service.TeamService;
import io.swagger.annotations.ApiOperation;
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
public class TeamController {

    @Autowired
    TeamService teamService;

    @ApiOperation("Render page to add team")
    @GetMapping("/team")
    public String showAddTeamForm(final Model model) {
        model.addAttribute("team", new Team());
        return "teams/add-team";
    }

    @ApiOperation("Save team")
    @PostMapping(path = "team")
    public String addTeam( @Valid Team team, Errors errors, Model model, HttpServletResponse httpResponse) {
        if (errors.hasErrors()) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "teams/add-team";
        }
        teamService.saveTeam(team);
        model.addAttribute("team", team);
        model.addAttribute("message", String.format("Team %s created successfully", team.getName()));
        httpResponse.setStatus(HttpServletResponse.SC_CREATED);
        return "teams/add-team";
    }

    @ApiOperation("View all teams")
    @GetMapping("/teams")
    public String getAllTeams(final Model model) {
        Iterable<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        return "teams/view-teams";
    }

    @ApiOperation("View players of the team")
    @GetMapping("/team/{id}/players")
    public String getPlayers(@PathVariable Long id, Model model) {
        List<Player> players = teamService.getPlayersOfTeam(id);
        model.addAttribute("players", players);
        model.addAttribute("source", "teams");
        return "players/view-players";
    }

    @PostMapping(path = "test/team")
    public String addTestTeam(@RequestBody @Valid Team team, Errors errors, Model model, HttpServletResponse httpResponse) {
        return addTeam(team, errors, model, httpResponse);
    }
    public void setPlayerService(TeamService teamService) {
        this.teamService = teamService;
    }
}
