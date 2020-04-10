package com.edu.postgrad.game.teams.service;

import java.util.List;

import com.edu.postgrad.game.teams.dao.TeamRepository;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.exception.TeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Long saveTeam(final Team team){
        teamRepository.save(team);
        return  team.getId();
    }

    public List<Team> getAllTeams(){
        List<Team> teams = teamRepository.findAll();
        return teams;
    }

    public Team findById(Long teamId){
        return teamRepository.findById(teamId).orElseThrow(TeamException::new);
    }

    //For unit tests only
    public void setTeamRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

}
