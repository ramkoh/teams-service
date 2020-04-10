package com.edu.postgrad.game.teams.rest;

import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class TeamController {


    @Autowired
    TeamService teamService;

    @GetMapping("/team")
    public String showAddTeamForm(final Model model) {
        model.addAttribute("team", new Team());
        return "teams/add-team";
    }


    @PostMapping(path = "test/team")
    public String addTestTeam(@RequestBody @Valid Team team, Errors errors, Model model, HttpServletResponse httpResponse) {
        return addTeam(team, errors, model, httpResponse);
    }


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

    @GetMapping("/teams")
    public String getAllTeams(final Model model) {
        Iterable<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        return "teams/view-teams";
    }

    public void setPlayerService(TeamService teamService) {
        this.teamService = teamService;
    }
}
